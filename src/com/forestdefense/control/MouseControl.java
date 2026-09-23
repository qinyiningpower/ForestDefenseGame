package com.forestdefense.control;

import com.forestdefense.base.AnimalType;
import com.forestdefense.base.GameUtil;
import com.forestdefense.ui.render.GameCanvas;
import com.forestdefense.ui.stage.AnimalButtonPanel;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

/**
 * Handles mouse interaction with the game board.
 *
 * Converts canvas coordinates into grid positions and delegates
 * animal placement to GameManager.
 */
public final class MouseControl {

    private final GameManager gameManager;
    private final GameCanvas canvas;
    private final AnimalButtonPanel animalPanel;

    private int hoverRow = -1;
    private int hoverCol = -1;
    private boolean enabled = true;

    public MouseControl(
            GameManager gameManager,
            GameCanvas canvas,
            AnimalButtonPanel animalPanel) {

        this.gameManager = gameManager;
        this.canvas = canvas;
        this.animalPanel = animalPanel;

        initializeMouseListeners();
    }

    private void initializeMouseListeners() {
        canvas.setOnMouseClicked(this::handleMouseClick);
        canvas.setOnMouseMoved(this::handleMouseMove);
        canvas.setOnMouseExited(this::handleMouseExit);
    }

    /**
     * Attempts to place the selected animal in the clicked cell.
     */
    private void handleMouseClick(MouseEvent event) {
        if (!enabled) {
            return;
        }

        double mouseX = event.getX();
        double mouseY = event.getY();

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

        animalPanel.updateButtonsAvailability(
                gameManager
                        .getResourceManager()
                        .getFruitCount()
        );

        canvas.redraw(
                gameManager.getGameWorld()
        );
    }

    /**
     * Tracks the grid cell currently under the cursor.
     */
    private void handleMouseMove(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();

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
