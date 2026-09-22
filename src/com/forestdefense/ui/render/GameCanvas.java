package com.forestdefense.ui.render;

import com.forestdefense.base.GameConstant;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Main game canvas.
 *
 * Responsible for drawing the forest background, defense grid,
 * nest area and enemy spawn area.
 */
public final class GameCanvas extends Canvas {

    private static final double CANVAS_WIDTH =
            GameConstant.GRID_OFFSET_X
            + GameConstant.GRID_COLS * GameConstant.CELL_SIZE
            + 50;

    private static final double CANVAS_HEIGHT =
            GameConstant.GRID_OFFSET_Y
            + GameConstant.GRID_ROWS * GameConstant.CELL_SIZE
            + 50;

    public GameCanvas() {
        super(CANVAS_WIDTH, CANVAS_HEIGHT);
        redraw();
    }

    /**
     * Redraws the complete game scene.
     */
    public void redraw() {
        GraphicsContext gc = getGraphicsContext2D();

        drawBackground(gc);
        drawGrid(gc);
        drawNest(gc);
        drawSpawnArea(gc);
    }

    private void drawBackground(GraphicsContext gc) {
        gc.setFill(Color.web("#173F35"));
        gc.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight(),
                24,
                24
        );
    }

    private void drawGrid(GraphicsContext gc) {
        double startX = GameConstant.GRID_OFFSET_X;
        double startY = GameConstant.GRID_OFFSET_Y;
        double cellSize = GameConstant.CELL_SIZE;

        for (int row = 0; row < GameConstant.GRID_ROWS; row++) {
            for (int col = 0; col < GameConstant.GRID_COLS; col++) {

                double x = startX + col * cellSize;
                double y = startY + row * cellSize;

                if ((row + col) % 2 == 0) {
                    gc.setFill(Color.web("#B8D8A7"));
                } else {
                    gc.setFill(Color.web("#A7CD95"));
                }

                gc.fillRect(x, y, cellSize, cellSize);

                gc.setStroke(Color.web("#729C67"));
                gc.setLineWidth(1.5);
                gc.strokeRect(x, y, cellSize, cellSize);
            }
        }
    }

    private void drawNest(GraphicsContext gc) {
        double centerY = GameConstant.GRID_OFFSET_Y
                + GameConstant.GRID_ROWS
                * GameConstant.CELL_SIZE / 2.0;

        gc.setFill(Color.web("#8B5E3C"));
        gc.fillOval(9, centerY - 30, 34, 60);

        gc.setFill(Color.web("#FFF6D8"));
        gc.fillOval(17, centerY - 18, 18, 36);

        gc.setStroke(Color.web("#D8C89A"));
        gc.setLineWidth(2);
        gc.strokeOval(17, centerY - 18, 18, 36);
    }

    private void drawSpawnArea(GraphicsContext gc) {
        double spawnX = GameConstant.GRID_OFFSET_X
                + GameConstant.GRID_COLS
                * GameConstant.CELL_SIZE;

        double gridHeight =
                GameConstant.GRID_ROWS * GameConstant.CELL_SIZE;

        gc.setFill(Color.web("#244F45"));
        gc.fillRect(
                spawnX,
                GameConstant.GRID_OFFSET_Y,
                24,
                gridHeight
        );

        gc.setStroke(Color.web("#7FB69A"));
        gc.setLineWidth(2);
        gc.strokeLine(
                spawnX,
                GameConstant.GRID_OFFSET_Y,
                spawnX,
                GameConstant.GRID_OFFSET_Y + gridHeight
        );
    }
}
