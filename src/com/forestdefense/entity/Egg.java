package com.forestdefense.entity;

import com.forestdefense.base.GameConstant;
import com.forestdefense.base.GameObject;

import javafx.scene.canvas.GraphicsContext;

public class Egg extends GameObject {

    public Egg() {

        // 鸟蛋放在地图最左侧
        super(0, 0,
              GameConstant.EGG_WIDTH,
              GameConstant.EGG_HEIGHT);
    }

    @Override
    public void draw(GraphicsContext gc) {
        // TODO: UI成员后续绘制鸟蛋
    }

    @Override
    public void update() {
        // 鸟蛋不移动，也不主动更新
    }
}
