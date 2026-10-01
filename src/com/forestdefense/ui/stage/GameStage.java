package com.forestdefense.ui.stage;

import com.forestdefense.base.GameConstant;
import com.forestdefense.base.GameState;
import com.forestdefense.control.GameManager;
import com.forestdefense.control.MouseControl;
import com.forestdefense.ui.render.GameAnimation;
import com.forestdefense.ui.render.GameCanvas;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Builds and manages the main JavaFX game window.
 */
public final class GameStage {

    private final Stage stage;

    private Scene scene;
    private GameCanvas gameCanvas;
    private AnimalButtonPanel animalPanel;
    private MouseControl mouseControl;
    private ScorePanel scorePanel;
    private MenuBarUtil menuBar;
    private GameAnimation gameAnimation;

    public GameStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates and displays the initial game interface.
     */
    public void init() {

        GameManager gameManager =
                GameManager.getInstance();

        Label title =
                new Label(
                        "Forest Defense Game"
                );

        title.setStyle(
                "-fx-font-size: 32px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-text-fill: #F4E9C9;"
        );

        Label subtitle =
                new Label(
                        "Protect the nest • Build defenses • Survive every wave"
                );

        subtitle.setStyle(
                "-fx-font-size: 15px;"
                        + "-fx-text-fill: #BFD6C7;"
        );

        scorePanel =
                new ScorePanel();

        scorePanel.updateFruits(
                gameManager
                        .getResourceManager()
                        .getFruitCount()
        );

        scorePanel.updateWave(
                gameManager
                        .getWaveManager()
                        .getCurrentWave(),
                gameManager
                        .getWaveManager()
                        .getMaxWave()
        );

        scorePanel.updateKills(0);

        scorePanel.updateStatus(
                "Ready"
        );

        scorePanel.updateEggStatus(
                gameManager
                        .getGameWorld()
                        .getEgg()
                        .isAlive()
        );

        animalPanel =
                new AnimalButtonPanel();

        animalPanel.updateButtonsAvailability(
                gameManager
                        .getResourceManager()
                        .getFruitCount()
        );

        gameCanvas =
                new GameCanvas();

        gameCanvas.redraw(
                gameManager.getGameWorld()
        );

        mouseControl =
                new MouseControl(
                        gameManager,
                        gameCanvas,
                        animalPanel,
                        scorePanel
                );

        menuBar =
                new MenuBarUtil();

        gameAnimation =
                new GameAnimation(
                        gameManager,
                        gameCanvas,
                        scorePanel,
                        animalPanel,
                        menuBar,
                        mouseControl
                );

        configureMenuActions(
                gameManager
        );

        gameAnimation.start();

        StackPane canvasContainer =
                new StackPane(
                        gameCanvas
                );

        canvasContainer.setPadding(
                new Insets(15)
        );

        VBox root =
                new VBox(
                        10,
                        title,
                        subtitle,
                        menuBar.getPanel(),
                        scorePanel.getPanel(),
                        animalPanel.getPanel(),
                        canvasContainer
                );

        root.setAlignment(
                Pos.CENTER
        );

        root.setPadding(
                new Insets(25)
        );

        root.setStyle(
                "-fx-background-color: #102F29;"
        );

        scene =
                new Scene(
                        root,
                        GameConstant.WINDOW_WIDTH,
                        GameConstant.WINDOW_HEIGHT
                );

        stage.setTitle(
                "Forest Defense Game"
        );

        stage.setScene(
                scene
        );

        stage.setResizable(
                false
        );

        stage.setOnCloseRequest(
                event ->
                        gameAnimation.stop()
        );

        stage.centerOnScreen();

        stage.show();
    }

    /**
     * Connects the control buttons to the game lifecycle.
     */
    private void configureMenuActions(
            GameManager gameManager) {

        // Start / Resume
        menuBar
                .getStartButton()
                .setOnAction(event -> {

                    GameState currentState =
                            gameManager
                                    .getCurrentState();

                    /*
                     * 如果当前是暂停状态：
                     * 恢复游戏，并恢复波次计时。
                     */
                    if (currentState
                            == GameState.PAUSED) {

                        gameManager
                                .resumeGame();

                        gameManager
                                .getWaveManager()
                                .resumeTimer();
                    }

                    /*
                     * 如果当前是 Ready：
                     * 真正开始游戏，并启动波次计时。
                     */
                    else if (currentState
                            == GameState.READY) {

                        gameManager
                                .startGame();

                        gameManager
                                .getWaveManager()
                                .startWaveTimer();
                    }

                    mouseControl
                            .setEnabled(true);

                    scorePanel
                            .updateStatus(
                                    "Running"
                            );

                    menuBar
                            .updateButtonState(
                                    gameManager
                                            .getCurrentState()
                            );
                });

        // Pause
        menuBar
                .getPauseButton()
                .setOnAction(event -> {

                    gameManager
                            .pauseGame();

                    gameManager
                            .getWaveManager()
                            .pauseTimer();

                    mouseControl
                            .setEnabled(false);

                    scorePanel
                            .updateStatus(
                                    "Paused"
                            );

                    menuBar
                            .updateButtonState(
                                    gameManager
                                            .getCurrentState()
                            );
                });

        // Restart
        menuBar
                .getRestartButton()
                .setOnAction(event -> {

                    gameManager
                            .resetGame();

                    gameAnimation
                            .reset();

                    animalPanel
                            .clearSelection();

                    animalPanel
                            .updateButtonsAvailability(
                                    gameManager
                                            .getResourceManager()
                                            .getFruitCount()
                            );

                    scorePanel
                            .updateFruits(
                                    gameManager
                                            .getResourceManager()
                                            .getFruitCount()
                            );

                    scorePanel
                            .updateWave(
                                    gameManager
                                            .getWaveManager()
                                            .getCurrentWave(),
                                    gameManager
                                            .getWaveManager()
                                            .getMaxWave()
                            );

                    scorePanel
                            .updateKills(0);

                    scorePanel
                            .updateStatus(
                                    "Ready"
                            );

                    scorePanel
                            .updateEggStatus(
                                    gameManager
                                            .getGameWorld()
                                            .getEgg()
                                            .isAlive()
                            );

                    gameCanvas
                            .redraw(
                                    gameManager
                                            .getGameWorld()
                            );

                    mouseControl
                            .setEnabled(true);

                    menuBar
                            .updateButtonState(
                                    gameManager
                                            .getCurrentState()
                            );
                });

        // Exit
        menuBar
                .getExitButton()
                .setOnAction(event -> {

                    gameAnimation.stop();

                    Platform.exit();
                });
    }

    public Scene getScene() {
        return scene;
    }

    public GameCanvas getGameCanvas() {
        return gameCanvas;
    }

    public AnimalButtonPanel getAnimalPanel() {
        return animalPanel;
    }

    public MouseControl getMouseControl() {
        return mouseControl;
    }

    public ScorePanel getScorePanel() {
        return scorePanel;
    }

    public MenuBarUtil getMenuBar() {
        return menuBar;
    }

    public GameAnimation getGameAnimation() {
        return gameAnimation;
    }
}
