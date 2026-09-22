package com.forestdefense.base;

/**
 * 动物类型枚举
 * 用于区分不同动物种类，便于工厂创建和UI展示
 */
public enum AnimalType {
    SQUIRREL("squirrel", "ResourceType", GameConstant.SQUIRREL_COST),
    HEDGEHOG("hedgehog", "AttackType", GameConstant.HEDGEHOG_COST),
    WOODPECKER("woodpecker", "AttackType", GameConstant.WOODPECKER_COST),
    FOX("fox", "AttackType", GameConstant.FOX_COST),
    TURTLE("turtle", "DefenseType", GameConstant.TURTLE_COST);
    
    private final String displayName;
    private final String category;
    private final int cost;
    
    AnimalType(String displayName, String category, int cost) {
        this.displayName = displayName;
        this.category = category;
        this.cost = cost;
    }
    
    public String getDisplayName() { return displayName; }
    public String getCategory() { return category; }
    public int getCost() { return cost; }
    
    /**
     * 判断是否为攻击类动物
     */
    public boolean isAttackType() {
        return this == HEDGEHOG || this == WOODPECKER || this == FOX;
    }
    
    /**
     * 判断是否为采集类动物
     */
    public boolean isResourceType() {
        return this == SQUIRREL;
    }
    
    /**
     * 判断是否为防御类动物
     */
    public boolean isDefenseType() {
        return this == TURTLE;
    }
}