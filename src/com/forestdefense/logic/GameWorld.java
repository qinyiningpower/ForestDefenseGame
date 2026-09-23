package com.forestdefense.logic;

import java.util.ArrayList;

import com.forestdefense.base.GameConstant;
import com.forestdefense.base.GameUtil;
import com.forestdefense.entity.Animal;
import com.forestdefense.entity.Beast;
import com.forestdefense.entity.Bullet;
import com.forestdefense.entity.Egg;
import com.forestdefense.entity.Fruit;
import com.forestdefense.entity.Trap;

/**
 * Stores all entities that currently exist in the game.
 */
public final class GameWorld {

    private final ArrayList<Animal> animals = new ArrayList<>();
    private final ArrayList<Beast> beasts = new ArrayList<>();
    private final ArrayList<Bullet> bullets = new ArrayList<>();
    private final ArrayList<Fruit> fruits = new ArrayList<>();
    private final ArrayList<Trap> traps = new ArrayList<>();

    private Egg egg;

    public GameWorld() {
        reset();
    }

    /**
     * Restores the game world to its initial state.
     */
    public void reset() {
        animals.clear();
        beasts.clear();
        bullets.clear();
        fruits.clear();
        traps.clear();

        createEgg();
        createInitialTraps();
    }

    private void createEgg() {
        egg = new Egg();

        double gridHeight =
                GameConstant.GRID_ROWS * GameConstant.CELL_SIZE;

        egg.setX(8);
        egg.setY(
                GameConstant.GRID_OFFSET_Y
                + (gridHeight - GameConstant.EGG_HEIGHT) / 2.0
        );
    }

    private void createInitialTraps() {
        for (int row = 0; row < GameConstant.GRID_ROWS; row++) {
            Trap trap = new Trap(row, 0);

            trap.setX(
                    GameUtil.colToScreenX(0)
                    + (GameConstant.CELL_SIZE
                    - GameConstant.TRAP_WIDTH) / 2.0
            );

            trap.setY(
                    GameUtil.rowToScreenY(row)
                    + (GameConstant.CELL_SIZE
                    - GameConstant.TRAP_HEIGHT) / 2.0
            );

            traps.add(trap);
        }
    }

    /**
     * Checks whether an animal already occupies a grid cell.
     */
    public boolean isCellOccupied(int row, int col) {
        for (Animal animal : animals) {
            if (animal.isAlive()
                    && animal.getRow() == row
                    && animal.getCol() == col) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public ArrayList<Beast> getBeasts() {
        return beasts;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    public ArrayList<Trap> getTraps() {
        return traps;
    }

    public Egg getEgg() {
        return egg;
    }
}
