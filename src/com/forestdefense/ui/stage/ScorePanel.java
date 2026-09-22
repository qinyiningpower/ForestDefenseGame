package com.forestdefense.ui.stage;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * 计分面板
 * 展示：果子数量、波次、击杀数、游戏状态提示
 * 
 * @author 成员2 负责实现
 */
public class ScorePanel {
    
    private HBox panel;
    private Label fruitLabel;      // 果子数量
    private Label waveLabel;       // 当前波次
    private Label killLabel;       // 击杀数
    private Label statusLabel;     // 游戏状态提示
    private Label eggStatusLabel;  // 鸟蛋状态
    
    public ScorePanel() {
        // TODO: 由成员2实现
        // - 创建所有Label
        // - 设置样式（字体大小、颜色、边框）
        // - 布局排版（使用HBox水平排列）
    }
    
    /**
     * 获取面板对象
     */
    public HBox getPanel() {
        // TODO: 由成员2实现
        return panel;
    }
    
    /**
     * 更新果子数量显示
     * @param amount 当前果子总数
     */
    public void updateFruits(int amount) {
        // TODO: 由成员2实现
        // - 更新fruitLabel文本
        // - 数量不足50时变红提示
    }
    
    /**
     * 更新波次显示
     * @param wave 当前波次
     * @param totalWaves 总波次
     */
    public void updateWave(int wave, int totalWaves) {
        // TODO: 由成员2实现
        // - 显示 "第 X / Y 波"
    }
    
    /**
     * 更新击杀数显示
     * @param kills 累计击杀数
     */
    public void updateKills(int kills) {
        // TODO: 由成员2实现
        // - 更新killLabel文本
    }
    
    /**
     * 更新游戏状态提示
     * @param status 状态文本（如"运行中"、"已暂停"）
     */
    public void updateStatus(String status) {
        // TODO: 由成员2实现
        // - 更新statusLabel文本
        // - 不同状态显示不同颜色
    }
    
    /**
     * 更新鸟蛋状态
     * @param isAlive 鸟蛋是否存活
     */
    public void updateEggStatus(boolean isAlive) {
        // TODO: 由成员2实现
        // - 显示 "🥚 安全" 或 "💀 已破碎"
    }
}