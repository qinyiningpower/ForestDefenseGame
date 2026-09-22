package com.forestdefense.main;

import com.forestdefense.ui.stage.GameStage;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX application entry point.
 */
public final class Main extends Application { 

    private GameStage gameStage;

    @Override
    public void start(Stage primaryStage) {
        gameStage = new GameStage(primaryStage);
        gameStage.init();
    }

    @Override
    public void stop() {
        System.out.println("Forest Defense Game closed.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
