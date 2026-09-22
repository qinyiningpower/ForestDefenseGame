package com.forestdefense.entity;

import javafx.scene.paint.Color;
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
        gc.setFill(Color.web("#FFF8DD"));
        gc.fillOval(getX(), getY(), getWidth(), getHeight());
    
        gc.setStroke(Color.web("#D8C99E"));
        gc.setLineWidth(2);
        gc.strokeOval(getX(), getY(), getWidth(), getHeight());
    
        gc.setFill(Color.rgb(255, 255, 255, 0.65));
        gc.fillOval(
                getX() + getWidth() * 0.25,
                getY() + getHeight() * 0.20,
                getWidth() * 0.20,
                getHeight() * 0.30
        );
    }

    @Override
    public void update() {
        // 鸟蛋不移动，也不主动更新
    }
}
