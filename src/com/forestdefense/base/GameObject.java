package com.forestdefense.base;

import javafx.scene.canvas.GraphicsContext;

/**
 * 游戏物体抽象父类
 * 所有游戏实体（动物、猛兽、子弹、果子、陷阱、鸟蛋）均继承此类
 * 体现OOP核心：抽象类、封装、继承、多态
 */

public abstract class GameObject {
    
    // ========== 封装属性（私有 + getter/setter） ==========
    private double x;          // X坐标
    private double y;          // Y坐标
    private double width;      // 宽度
    private double height;     // 高度
    private int row;           // 所在网格行
    private int col;           // 所在网格列
    private boolean alive;     // 存活状态
    
    // ========== 构造方法 ==========
    public GameObject() {
        this.alive = true;
    }
    
    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.alive = true;
    }
    
    public GameObject(double x, double y, double width, double height, int row, int col) {
        this(x, y, width, height);
        this.row = row;
        this.col = col;
    }
    
    // ========== 抽象方法（多态核心） ==========
    /**
     * 绘制自身 - 子类必须重写实现各自绘制逻辑
     */
    public abstract void draw(GraphicsContext gc);
    
    /**
     * 每帧更新状态 - 子类重写实现移动/攻击/扣血等逻辑
     */
    public abstract void update();
    
    // ========== Getter & Setter（封装） ==========
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    
    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }
    
    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }
    
    public int getRow() { return row; }
    public void setRow(int row) { this.row = row; }
    
    public int getCol() { return col; }
    public void setCol(int col) { this.col = col; }
    
    public boolean isAlive() { return alive; }
    public void setAlive(boolean alive) { this.alive = alive; }
    
    /**
     * 判断是否与另一个物体发生碰撞（矩形碰撞检测）
     */
    public boolean intersects(GameObject other) {
        return this.x < other.x + other.width &&
               this.x + this.width > other.x &&
               this.y < other.y + other.height &&
               this.y + this.height > other.y;
    }
}