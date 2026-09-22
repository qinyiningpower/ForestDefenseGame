package com.forestdefense.entity;

import javafx.scene.paint.Color;
import com.forestdefense.base.AnimalType;
import com.forestdefense.base.GameConstant;
import com.forestdefense.base.GameObject;
import com.forestdefense.base.GameUtil;

import javafx.scene.canvas.GraphicsContext;

public class Animal extends GameObject {

    // 动物名字
    protected String name;

    // 动物类型，例如 SQUIRREL、HEDGEHOG、TURTLE
    protected AnimalType type;

    // 当前生命值
    protected int hp;

    // 最大生命值
    protected int maxHp;

    // 购买需要的果子数量
    protected int cost;

    // 图片路径，给 UI 绘图使用
    protected String imagePath;

    public Animal() {
        super();
    }

    // 带参数构造方法
    // name：动物名字
    // type：动物类型
    // maxHp：最大生命值
    // cost：购买价格
    // row、col：动物所在网格位置
    // imagePath：图片路径
    public Animal(String name, AnimalType type, int maxHp, int cost,
                  int row, int col, String imagePath) {

        // 把网格位置转换成屏幕坐标，并传给 GameObject
        super(GameUtil.colToScreenX(col),
              GameUtil.rowToScreenY(row),
              GameConstant.CELL_SIZE,
              GameConstant.CELL_SIZE,
              row,
              col);

        this.name = name;
        this.type = type;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.cost = cost;
        this.imagePath = imagePath;
    }

    // 动物受到伤害
    public void takeDamage(int damage) {
        hp -= damage;

        if (hp <= 0) {
            hp = 0;
            setAlive(false);
        }
    }

    // 判断动物是否死亡
    public boolean isDead() {
        return !isAlive();
    }

    // 绘制方法
    // 具体图片绘制可以之后交给 UI 成员补
    @Override
    public void draw(GraphicsContext gc) {
        Color bodyColor;
    
        if (type == AnimalType.SQUIRREL) {
            bodyColor = Color.web("#D99058");
        } else if (type == AnimalType.HEDGEHOG) {
            bodyColor = Color.web("#795548");
        } else if (type == AnimalType.WOODPECKER) {
            bodyColor = Color.web("#D94C4C");
        } else if (type == AnimalType.FOX) {
            bodyColor = Color.web("#E87532");
        } else {
            bodyColor = Color.web("#4FA3A5");
        }
    
        double padding = 8;
    
        gc.setFill(bodyColor);
        gc.fillRoundRect(
                getX() + padding,
                getY() + padding,
                getWidth() - padding * 2,
                getHeight() - padding * 2,
                18,
                18
        );
    
        gc.setFill(Color.WHITE);
        gc.fillText(
                String.valueOf(type.name().charAt(0)),
                getX() + getWidth() / 2 - 4,
                getY() + getHeight() / 2 + 5
        );
    
        double healthRatio = (double) hp / maxHp;
    
        gc.setFill(Color.web("#472F2F"));
        gc.fillRect(getX() + 10, getY() + 4, getWidth() - 20, 5);
    
        gc.setFill(Color.web("#63C174"));
        gc.fillRect(
                getX() + 10,
                getY() + 4,
                (getWidth() - 20) * healthRatio,
                5
        );
    }

    // 每帧更新方法
    // 普通动物默认不移动，具体攻击/生产逻辑由子类或 Manager 处理
    @Override
    public void update() {
        // 默认不做额外更新
    }

    public String getName() {
        return name;
    }

    public AnimalType getType() {
        return type;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getCost() {
        return cost;
    }

    public String getImagePath() {
        return imagePath;
    }
}
