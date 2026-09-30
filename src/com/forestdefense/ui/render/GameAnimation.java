package com.forestdefense.ui.render;

import java.util.ArrayList;

import com.forestdefense.base.GameConstant;
import com.forestdefense.base.GameState;
import com.forestdefense.base.GameUtil;
import com.forestdefense.control.GameManager;
import com.forestdefense.control.MouseControl;
import com.forestdefense.entity.Animal;
import com.forestdefense.entity.AttackAnimal;
import com.forestdefense.entity.Beast;
import com.forestdefense.entity.Bullet;
import com.forestdefense.entity.Fruit;
import com.forestdefense.entity.ResourceAnimal;
import com.forestdefense.logic.AttackManager;
import com.forestdefense.logic.CollisionManager;
import com.forestdefense.logic.GameWorld;
import com.forestdefense.ui.stage.AnimalButtonPanel;
import com.forestdefense.ui.stage.MenuBarUtil;
import com.forestdefense.ui.stage.ScorePanel;

import javafx.animation.AnimationTimer;

/**
 * Runs the main game loop.
 *
 * Updates waves, attacks, movement, collisions, game state
 * and the JavaFX interface at a fixed frame rate.
 */
public final class GameAnimation extends AnimationTimer {

    private static final long FRAME_INTERVAL_NANOS =
            (long) (1_000_000_000 / GameConstant.FRAME_RATE);

    private final GameManager gameManager;
    private final GameCanvas gameCanvas;
    private final ScorePanel scorePanel;
    private final AnimalButtonPanel animalPanel;
    private final MenuBarUtil menuBar;
    private final MouseControl mouseControl;

    private final AttackManager attackManager;
    private final CollisionManager collisionManager;

    private long lastFrameTime;
    private int killCount;

    public GameAnimation(
            GameManager gameManager,
            GameCanvas gameCanvas,
            ScorePanel scorePanel,
            AnimalButtonPanel animalPanel,
            MenuBarUtil menuBar,
            MouseControl mouseControl) {

        this.gameManager = gameManager;
        this.gameCanvas = gameCanvas;
        this.scorePanel = scorePanel;
        this.animalPanel = animalPanel;
        this.menuBar = menuBar;
        this.mouseControl = mouseControl;

        attackManager = new AttackManager();
        collisionManager = new CollisionManager();

        lastFrameTime = 0;
        killCount = 0;
    }

    @Override
    public void handle(long now) {
        if (gameManager.getCurrentState() != GameState.RUNNING) {
            lastFrameTime = now;
            return;
        }

        if (lastFrameTime != 0
                && now - lastFrameTime < FRAME_INTERVAL_NANOS) {
            return;
        }

        updateGame();
        lastFrameTime = now;
    }

    /**
     * Performs one complete game update.
     */
    private void updateGame() {
        GameWorld world = gameManager.getGameWorld();

        gameManager.getWaveManager().generateNextWave(
                world.getBeasts()
        );

        int livingBeastsBeforeUpdate =
                countLivingBeasts(world.getBeasts());

        produceFruits(world);
        updateAnimalAttacks(world);
        updateBeasts(world);
        updateBullets(world);

        collisionManager.checkBulletHitBeast(
                world.getBullets(),
                world.getBeasts()
        );

        gameManager.getGameRule().checkTrap(
                world.getBeasts(),
                world.getTraps()
        );

        int livingBeastsAfterUpdate =
                countLivingBeasts(world.getBeasts());

        killCount += Math.max(
                0,
                livingBeastsBeforeUpdate - livingBeastsAfterUpdate
        );

        boolean playerLost =
                gameManager.getGameRule().checkLose(
                        world.getBeasts()
                );

        boolean playerWon =
                gameManager.getGameRule().checkWin(
                        gameManager.getWaveManager()
                                .isAllWavesFinished(),
                        world.getBeasts()
                );

        gameManager.getGameRule().removeDeadObjects(
                world.getAnimals(),
                world.getBeasts(),
                world.getBullets(),
                world.getFruits(),
                world.getTraps()
        );

        if (playerLost) {
            world.getEgg().setAlive(false);
            finishGame(false);
        } else if (playerWon) {
            finishGame(true);
        }

        refreshInterface(world);
    }

    /**
     * Lets resource animals periodically produce fruits.
     */
    private void produceFruits(GameWorld world) {
        for (Animal animal : world.getAnimals()) {
            if (!(animal instanceof ResourceAnimal)) {
                continue;
            }

            ResourceAnimal producer = (ResourceAnimal) animal;

            if (!producer.isAlive() || !producer.canProduce()) {
                continue;
            }

            Fruit fruit = new Fruit(
                    producer.getProduceAmount(),
                    producer.getRow(),
                    producer.getCol()
            );

            fruit.setX(
                    animal.getX()
                    + GameConstant.CELL_SIZE
                    - GameConstant.FRUIT_WIDTH
                    - 6
            );

            fruit.setY(animal.getY() + 6);

            world.getFruits().add(fruit);
            producer.updateProduceTime();
        }
    }

    /**
     * Runs ranged and close-range animal attacks.
     */
    private void updateAnimalAttacks(GameWorld world) {
        ArrayList<AttackAnimal> attackers = new ArrayList<>();

        for (Animal animal : world.getAnimals()) {
            if (animal instanceof AttackAnimal) {
                attackers.add((AttackAnimal) animal);
            }
        }

        attackManager.animalAttack(
                attackers,
                world.getBeasts(),
                world.getBullets()
        );
    }

    /**
     * Moves enemies until they encounter an animal, then attacks it.
     */
    private void updateBeasts(GameWorld world) {
        for (Beast beast : world.getBeasts()) {
            if (!beast.isAlive()) {
                continue;
            }

            Animal collidedAnimal =
                    collisionManager.getCollidedAnimal(
                            beast,
                            world.getAnimals()
                    );

            if (collidedAnimal == null) {
                beast.update();
            }
        }

        attackManager.beastAttack(
                world.getBeasts(),
                world.getAnimals()
        );
    }

    /**
     * Moves active bullets and removes bullets outside the board.
     */
    private void updateBullets(GameWorld world) {
        for (Bullet bullet : world.getBullets()) {
            if (!bullet.isActive()) {
                continue;
            }

            bullet.update();

            if (GameUtil.isOutOfRightBoundary(bullet.getX())) {
                bullet.setActive(false);
            }
        }
    }

    private int countLivingBeasts(ArrayList<Beast> beasts) {
        int count = 0;

        for (Beast beast : beasts) {
            if (beast.isAlive()) {
                count++;
            }
        }

        return count;
    }

    private void finishGame(boolean playerWon) {
        gameManager.gameOver(playerWon);
        mouseControl.setEnabled(false);

        if (playerWon) {
            scorePanel.updateStatus("Victory");
        } else {
            scorePanel.updateStatus("Defeat");
        }

        menuBar.updateButtonState(
                gameManager.getCurrentState()
        );
    }

    /**
     * Redraws the world and synchronizes the information panels.
     */
    private void refreshInterface(GameWorld world) {
        scorePanel.updateWave(
                gameManager.getWaveManager().getCurrentWave(),
                gameManager.getWaveManager().getMaxWave()
        );

        scorePanel.updateKills(killCount);

        scorePanel.updateEggStatus(
                world.getEgg().isAlive()
        );

        animalPanel.updateButtonsAvailability(
                gameManager.getResourceManager().getFruitCount()
        );

        gameCanvas.redraw(world);
    }

    /**
     * Clears loop statistics when a new game starts.
     */
    public void reset() {
        killCount = 0;
        lastFrameTime = 0;
    }

    public int getKillCount() {
        return killCount;
    }
}
