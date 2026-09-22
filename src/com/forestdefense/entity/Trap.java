package com.forestdefense.entity;

import com.forestdefense.base.GameConstant;
import com.forestdefense.base.GameObject;
import com.forestdefense.base.GameUtil;

import javafx.scene.canvas.GraphicsContext;

public class Trap extends GameObject {

    public Trap() {
        super();
    }

    public Trap(int row, double x) {

        super(GameUtil.colToScreenX((int) x),
              GameUtil.rowToScreenY(row),
              GameConstant.TRAP_WIDTH,
              GameConstant.TRAP_HEIGHT,
              row,
              (int) x);
    }

    // 触发陷阱
    public void trigger() {
        setAlive(false);
    }

    public boolean isActive() {
        return isAlive();
    }

    public void setActive(boolean active) {
        setAlive(active);
    }

    @Override
    public void draw(GraphicsContext gc) {
        // TODO: UI成员后续绘制陷阱
    }

    @Override
    public void update() {
        // 陷阱不移动
    }
}
