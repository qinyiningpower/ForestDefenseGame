package com.forestdefense.base;

/**
 * 方向枚举
 */
public enum Direction {
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, -1),
    DOWN(0, 1),
    NONE(0, 0);
    
    private final int dx;
    private final int dy;
    
    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
    
    public int getDx() { return dx; }
    public int getDy() { return dy; }
    
    /**
     * 根据dx, dy获取对应方向
     */
    public static Direction fromDelta(int dx, int dy) {
        if (dx == -1 && dy == 0) return LEFT;
        if (dx == 1 && dy == 0) return RIGHT;
        if (dx == 0 && dy == -1) return UP;
        if (dx == 0 && dy == 1) return DOWN;
        return NONE;
    }
}