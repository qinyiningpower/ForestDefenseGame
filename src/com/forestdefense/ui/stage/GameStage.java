package com.forestdefense.ui.stage;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 游戏主窗口
 * 封装Stage、Scene，管理所有UI控件
 * 
 * @author 成员2 负责实现
 */
public class GameStage {
    
    private Stage stage;
    private Scene scene;
    private MenuBarUtil menuBar;
    private ScorePanel scorePanel;
    private AnimalButtonPanel animalPanel;
    private com.forestdefense.ui.render.GameCanvas gameCanvas;
    
    public GameStage(Stage stage) {
        this.stage = stage;
        // TODO: 由成员2实现 - 初始化各组件
    }
    
    /**
     * 初始化窗口
     * 设置标题、大小、居中，创建Scene并添加所有UI组件
     */
    public void init() {
        // TODO: 由成员2实现
        // 1. stage.setTitle("🌳 森林鸟蛋保卫战")
        // 2. stage.setWidth(1200); stage.setHeight(800)
        // 3. 创建VBox作为根布局
        // 4. 添加menuBar、scorePanel、animalPanel、canvas
        // 5. scene = new Scene(root)
        // 6. stage.setScene(scene)
        // 7. stage.setResizable(false)
        // 8. stage.centerOnScreen()
    }
    
    public Scene getScene() {
        // TODO: 由成员2实现
        return scene;
    }
    
    public MenuBarUtil getMenuBar() {
        // TODO: 由成员2实现
        return menuBar;
    }
    
    public ScorePanel getScorePanel() {
        // TODO: 由成员2实现
        return scorePanel;
    }
    
    public AnimalButtonPanel getAnimalPanel() {
        // TODO: 由成员2实现
        return animalPanel;
    }
    
    public com.forestdefense.ui.render.GameCanvas getGameCanvas() {
        // TODO: 由成员2实现
        return gameCanvas;
    }
}