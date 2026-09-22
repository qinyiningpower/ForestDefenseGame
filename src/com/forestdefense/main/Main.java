package com.forestdefense.main;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 森林鸟蛋保卫战 - 游戏主入口
 * JavaFX应用程序启动类
 * 
 * 注意：当前为占位版本，待其他模块完成后解除注释
 */
public class Main extends Application {
    
    // private static GameManager gameManager;
    // private static GameStage gameStage;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Forest Bird Egg Defense Game");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);
        primaryStage.show();
        
        System.out.println("Forest Bird Egg Defense Battle framework has started!");
        System.out.println("当前为占位版本，等待各模块开发完成...");
        
        // ========== 以下代码待其他模块完成后解除注释 ==========
        /*
        // 1. 创建游戏管理器（单例）
        gameManager = GameManager.getInstance();
        
        // 2. 创建主窗口
        gameStage = new GameStage(primaryStage);
        gameStage.init();
        
        // 3. 初始化游戏
        gameManager.initGame();
        
        // 4. 开始游戏循环（由GameAnimation驱动）
        gameManager.startGameLoop();
        */
    }
    
    @Override
    public void stop() {
        System.out.println("Exit the game.");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}