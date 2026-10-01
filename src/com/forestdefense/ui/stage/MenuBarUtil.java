package com.forestdefense.ui.stage;

import com.forestdefense.base.GameState;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Displays the main game control buttons.
 */
public final class MenuBarUtil {

    private final HBox panel;

    private final Button startButton;
    private final Button pauseButton;
    private final Button restartButton;
    private final Button level2Button;
    private final Button exitButton;

    public MenuBarUtil() {

        startButton =
                createButton(
                        "Start",
                        "#5FA86A"
                );

        pauseButton =
                createButton(
                        "Pause",
                        "#D8A43A"
                );

        restartButton =
                createButton(
                        "Restart",
                        "#4E88B7"
                );

        level2Button =
                createButton(
                        "Level 2",
                        "#8A6BBE"
                );

        exitButton =
                createButton(
                        "Exit",
                        "#B8564F"
                );

        panel =
                new HBox(
                        12,
                        startButton,
                        pauseButton,
                        restartButton,
                        level2Button,
                        exitButton
                );

        panel.setAlignment(
                Pos.CENTER
        );

        panel.setStyle(
                "-fx-padding: 10;"
                        + "-fx-background-color: #173F35;"
                        + "-fx-background-radius: 12;"
        );

        updateButtonState(
                GameState.READY
        );
    }

    private Button createButton(
            String text,
            String backgroundColor) {

        Button button =
                new Button(text);

        button.setPrefWidth(
                105
        );

        button.setPrefHeight(
                38
        );

        button.setStyle(
                "-fx-font-size: 14px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-text-fill: white;"
                        + "-fx-background-color: "
                        + backgroundColor
                        + ";"
                        + "-fx-background-radius: 8;"
                        + "-fx-cursor: hand;"
        );

        return button;
    }

    public HBox getPanel() {
        return panel;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getPauseButton() {
        return pauseButton;
    }

    public Button getRestartButton() {
        return restartButton;
    }

    public Button getLevel2Button() {
        return level2Button;
    }

    public Button getExitButton() {
        return exitButton;
    }

    /**
     * Updates button availability based on the current game state.
     */
    public void updateButtonState(
            GameState state) {

        boolean running =
                state == GameState.RUNNING;

        boolean paused =
                state == GameState.PAUSED;

        boolean gameOver =
                state.isGameOver();

        startButton.setDisable(
                running || gameOver
        );

        pauseButton.setDisable(
                !running
        );

        restartButton.setDisable(
                false
        );

        /*
         * Level 2 只允许在游戏没有运行时点击。
         * Running 和 Paused 时暂时禁用，
         * 避免中途直接切关。
         */
        level2Button.setDisable(
                running || paused
        );

        exitButton.setDisable(
                false
        );

        if (paused) {

            startButton.setText(
                    "Resume"
            );

        } else {

            startButton.setText(
                    "Start"
            );
        }
    }
}
