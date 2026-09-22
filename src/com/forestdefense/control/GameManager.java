package com.forestdefense.control;

import com.forestdefense.base.GameState;
import com.forestdefense.logic.GameRule;
import com.forestdefense.logic.WaveManager;
import com.forestdefense.logic.ResourceManager;
import com.forestdefense.ui.render.GameCanvas;
import com.forestdefense.ui.render.GameAnimation;

/**
 * 游戏总调度器（单例）
 * 整合所有模块，统一调度游戏流程
 * 
 * @author 成员5 负责实现
 */
public class GameManager {
    
    private static GameManager instance;
    
    private GameState currentState;
    private GameCanvas gameCanvas;
    private GameAnimation gameAnimation;
    private GameRule gameRule;
    private WaveManager waveManager;
    private ResourceManager resourceManager;
    private MouseControl mouseControl;
    
    private GameManager() {
        // TODO: 由成员5实现
        // - 初始化所有Manager
        //Initialize all the managers
        this.resourceManager = new ResourceManager();
        this.waveManager = new WaveManager();
        this.gameRule = new GameRule();
        this.gameAnimation = new GameAnimation();
        this.mouseControl = new MouseControl();
        // - 设置初始状态为READY
        // Set the inital state to READY
        this.currentState = GameState.READY;
    }
    
    public static GameManager getInstance() {
        // TODO: 由成员5实现 - 单例模式（双重检查锁或饿汉式）
        return instance;
    }
    
    /**
     * 初始化游戏
     * 重置所有数据，创建初始资源
     */
    public void initGame() {
        // TODO: 由成员5实现
        // 1. 重置ResourceManager（初始50果子）
        // 2. 重置WaveManager
        // 3. 重置所有实体列表
        // 4. 在鸟巢位置创建Egg
        // 5. 设置状态为READY
    }
    
    /**
     * 开始游戏
     * 从READY状态切换到RUNNING
     */
    public void startGame() {
        // TODO: 由成员5实现
        // - 状态改为RUNNING
        // - 启动GameAnimation
        // - 通知各模块开始工作
    }
    
    /**
     * 暂停游戏
     */
    public void pauseGame() {
        // TODO: 由成员5实现
        // - 状态改为PAUSED
        // - 暂停GameAnimation
    }
    
    /**
     * 恢复游戏
     */
    public void resumeGame() {
        // TODO: 由成员5实现
        // - 状态改为RUNNING
        // - 恢复GameAnimation
    }
    
    /**
     * 重置游戏
     */
    public void resetGame() {
        // TODO: 由成员5实现
        // - 清空所有实体
        // - 重置所有数值
        // - 回到READY状态
    }
    
    /**
     * 开始游戏循环（帧刷新）
     */
    public void startGameLoop() {
        // TODO: 由成员5实现
        // - 创建并启动AnimationTimer
    }
    
    /**
     * 停止游戏循环
     */
    public void stopGameLoop() {
        // TODO: 由成员5实现
        // - 停止AnimationTimer
    }
    
    /**
     * 获取当前游戏状态
     */
    public GameState getCurrentState() {
        // TODO: 由成员5实现
        return currentState;
    }
    
    /**
     * 游戏结束处理
     * @param isWin true=胜利, false=失败
     */
    public void gameOver(boolean isWin) {
        // TODO: 由成员5实现
        // - 设置对应状态（WIN/LOSE）
        // - 停止游戏循环
        // - 显示结束提示
    }
}
