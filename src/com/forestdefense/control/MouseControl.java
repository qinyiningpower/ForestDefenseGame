package com.forestdefense.control;

import java.util.Iterator;

import com.forestdefense.base.AnimalType;
import com.forestdefense.base.GameUtil;
import com.forestdefense.entity.Fruit;
import com.forestdefense.ui.render.GameCanvas;
import com.forestdefense.ui.stage.AnimalButtonPanel;
import com.forestdefense.ui.stage.ScorePanel;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

/**
 * Handles mouse interaction with the game board.
 *
 * Supports animal placement, fruit collection and cursor feedback.
 */
public final class MouseControl {

    private final GameManager gameManager;
    private final GameCanvas canvas;
    private final AnimalButtonPanel animalPanel;
    private final ScorePanel scorePanel;

    private int hoverRow = -1;
    private int hoverCol = -1;
    private boolean enabled = true;

    public MouseControl(
            GameManager gameManager,
            GameCanvas canvas,
            AnimalButtonPanel animalPanel) {

        this(
                gameManager,
                canvas,
                animalPanel,
                null
        );
    }

    public MouseControl(
            GameManager gameManager,
            GameCanvas canvas,
            AnimalButtonPanel animalPanel,
            ScorePanel scorePanel) {

        this.gameManager = gameManager;
        this.canvas = canvas;
        this.animalPanel = animalPanel;
        this.scorePanel = scorePanel;

        initializeMouseListeners();
    }

    private void initializeMouseListeners() {
        canvas.setOnMouseClicked(this::handleMouseClick);
        canvas.setOnMouseMoved(this::handleMouseMove);
        canvas.setOnMouseExited(this::handleMouseExit);
    }

    /**
     * Collects a fruit or attempts to place the selected animal.
     */
    private void handleMouseClick(MouseEvent event) {
        if (!enabled) {
            return;
        }

        double mouseX = event.getX();
        double mouseY = event.getY();

        if (collectFruitAt(mouseX, mouseY)) {
            return;
        }

        if (!GameUtil.isInGridArea(mouseX, mouseY)) {
            return;
        }

        int row = GameUtil.screenYToRow(mouseY);
        int col = GameUtil.screenXToCol(mouseX);

        AnimalType selectedType =
                animalPanel.getSelectedType();

        boolean placed = gameManager.placeAnimal(
                selectedType,
                row,
                col
        );

        if (!placed) {
            return;
        }

        refreshResourceInterface();

        canvas.redraw(
                gameManager.getGameWorld()
        );
    }

    /**
     * Collects the active fruit under the mouse pointer.
     */
    private boolean collectFruitAt(
            double mouseX,
            double mouseY) {

        Iterator<Fruit> iterator =
                gameManager
                        .getGameWorld()
                        .getFruits()
                        .iterator();

        while (iterator.hasNext()) {
            Fruit fruit = iterator.next();

            if (!fruit.isActive()) {
                continue;
            }

            if (!containsPoint(fruit, mouseX, mouseY)) {
                continue;
            }

            gameManager
                    .getResourceManager()
                    .collectFruit(fruit);

            iterator.remove();

            refreshResourceInterface();

            canvas.redraw(
                    gameManager.getGameWorld()
            );

            return true;
        }

        return false;
    }

    private boolean containsPoint(
            Fruit fruit,
            double mouseX,
            double mouseY) {

        return mouseX >= fruit.getX()
                && mouseX <= fruit.getX() + fruit.getWidth()
                && mouseY >= fruit.getY()
                && mouseY <= fruit.getY() + fruit.getHeight();
    }

    /**
     * Updates fruit count and animal purchasing availability.
     */
    private void refreshResourceInterface() {
        int remainingFruits =
                gameManager
                        .getResourceManager()
                        .getFruitCount();

        animalPanel.updateButtonsAvailability(
                remainingFruits
        );

        if (scorePanel != null) {
            scorePanel.updateFruits(
                    remainingFruits
            );
        }
    }

    /**
     * Tracks the current cell and displays an interactive cursor.
     */
    private void handleMouseMove(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();

        if (isFruitAt(mouseX, mouseY)) {
            hoverRow = -1;
            hoverCol = -1;
            canvas.setCursor(Cursor.HAND);
            return;
        }

        if (!GameUtil.isInGridArea(mouseX, mouseY)) {
            hoverRow = -1;
            hoverCol = -1;
            canvas.setCursor(Cursor.DEFAULT);
            return;
        }

        hoverRow = GameUtil.screenYToRow(mouseY);
        hoverCol = GameUtil.screenXToCol(mouseX);

        if (animalPanel.getSelectedType() != null
                && hoverCol > 0) {
            canvas.setCursor(Cursor.HAND);
        } else {
            canvas.setCursor(Cursor.DEFAULT);
        }
    }

    private boolean isFruitAt(
            double mouseX,
            double mouseY) {

        for (Fruit fruit :
                gameManager.getGameWorld().getFruits()) {

            if (fruit.isActive()
                    && containsPoint(fruit, mouseX, mouseY)) {
                return true;
            }
        }

        return false;
    }

    private void handleMouseExit(MouseEvent event) {
        hoverRow = -1;
        hoverCol = -1;
        canvas.setCursor(Cursor.DEFAULT);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        canvas.setMouseTransparent(!enabled);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getHoverRow() {
        return hoverRow;
    }

    public int getHoverCol() {
        return hoverCol;
    }
}
