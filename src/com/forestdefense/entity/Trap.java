package com.forestdefense.entity;

import javafx.scene.paint.Color;
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
        double x = getX();
        double y = getY();
        double width = getWidth();
        double height = getHeight();
    
        gc.setFill(Color.web("#70513B"));
        gc.fillRect(x, y + height * 0.65, width, height * 0.35);
    
        gc.setFill(Color.web("#D7D9D8"));
    
        double spikeWidth = width / 4.0;
    
        for (int i = 0; i < 4; i++) {
            double startX = x + i * spikeWidth;
    
            gc.fillPolygon(
                    new double[]{
                            startX,
                            startX + spikeWidth / 2,
                            startX + spikeWidth
                    },
                    new double[]{
                            y + height * 0.65,
                            y,
                            y + height * 0.65
                    },
                    3
            );
        }
    }

    @Override
    public void update() {
        // 陷阱不移动
    }
}
