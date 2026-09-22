package com.forestdefense.base;

// * 游戏状态枚举
// * 控制游戏流程：准备 → 运行 → 暂停 → 胜利/失败
// */
public enum GameState {
    READY("Game is loading..."),
    RUNNING("Game in progress"),
    PAUSED("Game is paused"),
    WIN("Victory! Successfully protected the bird egg!"),
    LOSE("Lose! The bird egg got destroyed!");
    
    private final String displayName;
    
    GameState(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * 判断游戏是否结束
     */
    public boolean isGameOver() {
        return this == WIN || this == LOSE;
    }
    
    /**
     * 判断游戏是否可操作（运行中）
     */
    public boolean isPlayable() {
        return this == RUNNING;
    }
}