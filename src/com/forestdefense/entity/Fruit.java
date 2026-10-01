package com.forestdefense.entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import com.forestdefense.base.GameConstant;
import com.forestdefense.base.GameObject;
import com.forestdefense.base.GameUtil;

public class Fruit extends GameObject {

    // 果子的数量
    // 松鼠每次生产 25 个果子
    protected int amount;

    // 果子生成的时间
    protected long createTime;

    public Fruit() {
        super();

        this.amount =
                GameConstant.SQUIRREL_FRUIT_AMOUNT;

        this.createTime =
                System.currentTimeMillis();
    }

    public Fruit(int amount,
                 int row,
                 int col) {

        super(
                GameUtil.colToScreenX(col),
                GameUtil.rowToScreenY(row),
                GameConstant.FRUIT_WIDTH,
                GameConstant.FRUIT_HEIGHT,
                row,
                col
        );

        this.amount = amount;

        // 记录果子生成时间
        this.createTime =
                System.currentTimeMillis();
    }

    // 收集果子
    public void collect() {
        setAlive(false);
    }

    // 判断果子是否超过存在时间
    public boolean isExpired() {

        long currentTime =
                System.currentTimeMillis();

        long lifetime =
                GameConstant.FRUIT_LIFETIME * 1000L;

        return currentTime - createTime
                >= lifetime;
    }

    public int getAmount() {
        return amount;
    }

    public long getCreateTime() {
        return createTime;
    }

    public boolean isActive() {
        return isAlive();
    }

    public void setActive(boolean active) {
        setAlive(active);
    }

    @Override
    public void draw(GraphicsContext gc) {

        gc.setFill(
                Color.web("#E84C3D")
        );

        gc.fillOval(
                getX(),
                getY(),
                getWidth(),
                getHeight()
        );

        gc.setFill(
                Color.web("#5A8737")
        );

        gc.fillOval(
                getX() + getWidth() / 2,
                getY() - 4,
                8,
                6
        );

        gc.setStroke(
                Color.web("#8C2F27")
        );

        gc.setLineWidth(1.5);

        gc.strokeOval(
                getX(),
                getY(),
                getWidth(),
                getHeight()
        );
    }

    @Override
    public void update() {

        // 超过生命周期后自动消失
        if (isExpired()) {
            setAlive(false);
        }
    }
}
