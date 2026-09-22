package com.forestdefense.base;

/**
 * 猛兽类型枚举
 */
public enum EnemyType {
    SNAKE("snake", GameConstant.SNAKE_HP, GameConstant.SNAKE_BASE_SPEED, GameConstant.SNAKE_DAMAGE),
    WOLF("wolf", GameConstant.WOLF_HP, GameConstant.WOLF_BASE_SPEED, GameConstant.WOLF_DAMAGE),
    TIGER("tiger", GameConstant.TIGER_HP, GameConstant.TIGER_BASE_SPEED, GameConstant.TIGER_DAMAGE);
    
    private final String displayName;
    private final int hp;
    private final double baseSpeed;
    private final int damage;
    
    EnemyType(String displayName, int hp, double baseSpeed, int damage) {
        this.displayName = displayName;
        this.hp = hp;
        this.baseSpeed = baseSpeed;
        this.damage = damage;
    }
    
    public String getDisplayName() { return displayName; }
    public int getHp() { return hp; }
    public double getBaseSpeed() { return baseSpeed; }
    public int getDamage() { return damage; }
    
    /**
     * 获取实际速度（基础速度 × 整体加速倍数）
     */
    public double getActualSpeed() {
        return baseSpeed * GameConstant.BEAST_SPEED_MULTIPLIER;
    }
}