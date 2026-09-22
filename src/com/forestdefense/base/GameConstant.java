package com.forestdefense.base;

//游戏全局常量配置
//所有数值参数集中管理，便于平衡性调整
 
public final class GameConstant {
    
    private GameConstant() {} // 防止实例化
    
    // ========== 窗口 & 网格 ==========
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 800;
    public static final int GRID_ROWS = 3;          // 3条横向森林小路
    public static final int GRID_COLS = 7;          // 7列格子
    public static final int CELL_SIZE = 80;         // 每格80x80像素
    public static final int GRID_OFFSET_X = 50;     // 左侧偏移（鸟巢区域）
    public static final int GRID_OFFSET_Y = 50;     // 顶部偏移
    
    // ========== 初始资源 ==========
    public static final int INITIAL_FRUITS = 50;    // 初始果子数量
    
    // ========== 动物（防御方）配置 ==========
    // 松鼠 - 采集类
    public static final int SQUIRREL_COST = 50;
    public static final int SQUIRREL_HP = 100;
    public static final int SQUIRREL_FRUIT_INTERVAL = 4;  // 秒
    public static final int SQUIRREL_FRUIT_AMOUNT = 25;
    
    // 刺猬 - 攻击类
    public static final int HEDGEHOG_COST = 100;
    public static final int HEDGEHOG_HP = 100;
    public static final double HEDGEHOG_ATTACK_INTERVAL = 0.75;  // 秒
    public static final int HEDGEHOG_DAMAGE = 20;
    public static final double HEDGEHOG_RANGE = 300;            // 攻击范围
    
    // 啄木鸟 - 攻击类
    public static final int WOODPECKER_COST = 125;
    public static final int WOODPECKER_HP = 100;
    public static final double WOODPECKER_ATTACK_INTERVAL = 0.25; // 秒
    public static final int WOODPECKER_DAMAGE = 10;
    public static final double WOODPECKER_RANGE = 350;
    
    // 狐狸 - 攻击类
    public static final int FOX_COST = 175;
    public static final int FOX_HP = 100;
    public static final double FOX_ATTACK_INTERVAL = 1.5;  // 秒
    public static final int FOX_DAMAGE = 40;
    public static final double FOX_RANGE = 250;
    
    // 乌龟 - 防御类
    public static final int TURTLE_COST = 75;
    public static final int TURTLE_HP = 300;
    public static final int TURTLE_DEFENSE = 50;          // 减伤值
    
    // ========== 猛兽（进攻方）配置 ==========
    public static final double BEAST_SPEED_MULTIPLIER = 2.0;  // 整体加速2倍
    
    // 蛇
    public static final int SNAKE_HP = 40;
    public static final double SNAKE_BASE_SPEED = 0.8;    // 速度快
    public static final int SNAKE_DAMAGE = 10;
    public static final double SNAKE_ATTACK_INTERVAL = 1.0;
    public static final int SNAKE_SCORE = 10;             // 击杀得分
    
    // 野狼
    public static final int WOLF_HP = 70;
    public static final double WOLF_BASE_SPEED = 0.6;     // 速度中等
    public static final int WOLF_DAMAGE = 20;
    public static final double WOLF_ATTACK_INTERVAL = 0.75;
    public static final int WOLF_SCORE = 15;
    
    // 老虎
    public static final int TIGER_HP = 100;
    public static final double TIGER_BASE_SPEED = 0.35;   // 速度慢
    public static final int TIGER_DAMAGE = 30;
    public static final double TIGER_ATTACK_INTERVAL = 0.5;
    public static final int TIGER_SCORE = 20;
    
    // ========== 波次配置 ==========
    public static final int SIMPLE_WAVES = 5;               // 简单模式波次
    public static final int DIFFICULT_WAVES = 6;          // 困难模式波次
    public static final double WAVE_INTERVAL = 15.0;      // 每波间隔15秒
    public static final int BEASTS_PER_WAVE_BASE = 3;     // 基础每波数量
    public static final int BEASTS_PER_WAVE_INCREMENT = 1; // 每波增加数量
    
    // ========== 子弹配置 ==========
    public static final double BULLET_SPEED = 5.0;
    public static final int BULLET_WIDTH = 10;
    public static final int BULLET_HEIGHT = 10;
    
    // ========== 果子配置 ==========
    public static final int FRUIT_WIDTH = 24;
    public static final int FRUIT_HEIGHT = 24;
    public static final double FRUIT_FALL_SPEED = 1.5;
    public static final int FRUIT_LIFETIME = 10;          // 10秒后消失
    
    // ========== 陷阱配置 ==========
    public static final int TRAP_WIDTH = 60;
    public static final int TRAP_HEIGHT = 40;
    
    // ========== 鸟蛋配置 ==========
    public static final int EGG_WIDTH = 40;
    public static final int EGG_HEIGHT = 50;
    public static final int EGG_HP = 1;                   // 1血，碰一下就碎
    
    // ========== 游戏帧率 ==========
    public static final double FRAME_RATE = 60.0;
    public static final double FRAME_INTERVAL = 1.0 / FRAME_RATE;
}