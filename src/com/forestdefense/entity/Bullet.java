package com.forestdefense.entity;

import com.forestdefense.base.GameConstant;
import com.forestdefense.base.GameObject;
import com.forestdefense.base.GameUtil;

import javafx.scene.canvas.GraphicsContext;

public class Bullet extends GameObject {

    // 子弹伤害
    protected int damage;

    // 子弹飞行速度
    protected double speed;

    public Bullet() {
        super();
    }

    // 带参数构造方法
    public Bullet(int damage, double speed,
                  int row, double x) {

        // 子弹使用 row + x。
        // x 是横向编号，需要转换成屏幕坐标。
        super(GameUtil.colToScreenX((int) x),
              GameUtil.rowToScreenY(row),
              GameConstant.BULLET_WIDTH,
              GameConstant.BULLET_HEIGHT,
              row,
              (int) x);

        this.damage = damage;
        this.speed = speed;
    }

    // 子弹向右移动
    public void move() {
        setX(getX() + speed);
    }

    @Override
    public void draw(GraphicsContext gc) {
        // TODO: UI 绘图时可在这里绘制子弹
    }

    @Override
    public void update() {
        move();
    }

    // Getter

    public int getDamage() {
        return damage;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isActive() {
        return isAlive();
    }

    // Setter

    public void setActive(boolean active) {
        setAlive(active);
    }
}
