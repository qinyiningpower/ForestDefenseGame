package com.forestdefense.ui.stage;
import com.forestdefense.control.MouseControl;
import com.forestdefense.base.GameConstant;
import com.forestdefense.control.GameManager;
import com.forestdefense.ui.render.GameCanvas;

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

    public GameStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates and displays the initial game interface.
     */
    public void init() {
        GameManager gameManager = GameManager.getInstance();

        Label title = new Label("Forest Defense Game");
        title.setStyle(
                "-fx-font-size: 32px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: #F4E9C9;"
        );

        Label subtitle = new Label(
                "Protect the nest • Build defenses • Survive every wave"
        );
        subtitle.setStyle(
                "-fx-font-size: 15px;"
                + "-fx-text-fill: #BFD6C7;"
        );

        animalPanel = new AnimalButtonPanel();

        animalPanel.updateButtonsAvailability(
                gameManager.getResourceManager().getFruitCount()
        );

        gameCanvas = new GameCanvas();

        gameCanvas.redraw(
                gameManager.getGameWorld()
        );

        StackPane canvasContainer = new StackPane(gameCanvas);
        canvasContainer.setPadding(new Insets(15));

        VBox root = new VBox(
                10,
                title,
                subtitle,
                animalPanel.getPanel(),
                canvasContainer
        );

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #102F29;");

        scene = new Scene(
                root,
                GameConstant.WINDOW_WIDTH,
                GameConstant.WINDOW_HEIGHT
        );

        stage.setTitle("Forest Defense Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
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
}
