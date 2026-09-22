package com.forestdefense.entity;

import com.forestdefense.base.GameConstant;
import com.forestdefense.base.GameObject;
import com.forestdefense.base.GameUtil;

import javafx.scene.canvas.GraphicsContext;

public class Fruit extends GameObject {

    // 果子的数量
    // 松鼠每次生产 25 个果子
    protected int amount;

    public Fruit() {
        super();
        this.amount = GameConstant.SQUIRREL_FRUIT_AMOUNT;
    }

    public Fruit(int amount, int row, int col) {

        super(GameUtil.colToScreenX(col),
              GameUtil.rowToScreenY(row),
              GameConstant.FRUIT_WIDTH,
              GameConstant.FRUIT_HEIGHT,
              row,
              col);

        this.amount = amount;
    }

    // 收集果子
    public void collect() {
        setAlive(false);
    }

    public int getAmount() {
        return amount;
    }

    public boolean isActive() {
        return isAlive();
    }

    public void setActive(boolean active) {
        setAlive(active);
    }

    @Override
    public void draw(GraphicsContext gc) {
        // TODO: UI成员后续绘制果子
    }

    @Override
    public void update() {
        // 果子默认不移动
    }
}
