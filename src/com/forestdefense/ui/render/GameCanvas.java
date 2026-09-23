package com.forestdefense.ui.render;

import com.forestdefense.base.GameConstant;
import com.forestdefense.logic.GameWorld;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Main game canvas.
 *
 * Draws the board background and delegates entity rendering
 * to the Renderer.
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

    private final Renderer renderer;

    public GameCanvas() {
        super(CANVAS_WIDTH, CANVAS_HEIGHT);
        renderer = new Renderer();
        redraw(null);
    }

    /**
     * Redraws the board and all active game entities.
     */
    public void redraw(GameWorld world) {
        GraphicsContext gc = getGraphicsContext2D();

        gc.clearRect(0, 0, getWidth(), getHeight());

        drawBackground(gc);
        drawGrid(gc);
        drawNest(gc);
        drawSpawnArea(gc);

        renderer.render(gc, world);
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

    /**
     * Draws the nest behind the Egg entity.
     */
    private void drawNest(GraphicsContext gc) {
        double centerY =
                GameConstant.GRID_OFFSET_Y
                + GameConstant.GRID_ROWS
                * GameConstant.CELL_SIZE / 2.0;

        gc.setFill(Color.web("#6B442D"));
        gc.fillOval(2, centerY - 34, 46, 68);

        gc.setFill(Color.web("#9A6A45"));
        gc.fillOval(7, centerY - 29, 36, 58);
    }

    private void drawSpawnArea(GraphicsContext gc) {
        double spawnX =
                GameConstant.GRID_OFFSET_X
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
