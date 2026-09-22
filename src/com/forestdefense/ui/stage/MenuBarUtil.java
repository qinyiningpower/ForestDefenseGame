package com.forestdefense.ui.stage;

import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;

/**
 * 菜单栏工具类
 * 管理游戏控制按钮：开始、暂停、重启、退出
 * 
 * @author 成员2 负责实现
 */
public class MenuBarUtil {
    
    private MenuBar menuBar;
    private HBox buttonContainer;
    private Button startBtn;
    private Button pauseBtn;
    private Button restartBtn;
    private Button exitBtn;
    
    public MenuBarUtil() {
        // TODO: 由成员2实现
        // - 创建所有按钮
        // - 设置按钮样式（字体、颜色、背景）
        // - 绑定点击事件
        // - 布局排版
    }
    
    /**
     * 创建并返回菜单栏
     */
    public MenuBar createMenuBar() {
        // TODO: 由成员2实现
        // - 将按钮放入HBox
        // - 创建MenuBar并添加
        return menuBar;
    }
    
    public Button getStartBtn() {
        // TODO: 由成员2实现
        return startBtn;
    }
    
    public Button getPauseBtn() {
        // TODO: 由成员2实现
        return pauseBtn;
    }
    
    public Button getRestartBtn() {
        // TODO: 由成员2实现
        return restartBtn;
    }
    
    public Button getExitBtn() {
        // TODO: 由成员2实现
        return exitBtn;
    }
    
    /**
     * 根据游戏状态更新按钮启用/禁用
     * @param isRunning 游戏是否运行中
     */
    public void updateButtonState(boolean isRunning) {
        // TODO: 由成员2实现
        // - 开始按钮：运行中禁用
        // - 暂停按钮：运行中启用，暂停时禁用
        // - 重启按钮：始终启用
        // - 退出按钮：始终启用
    }
}