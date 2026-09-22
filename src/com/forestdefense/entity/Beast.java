package com.forestdefense.entity;

import javafx.scene.paint.Color;
import com.forestdefense.base.EnemyType;
import com.forestdefense.base.GameConstant;
import com.forestdefense.base.GameObject;
import com.forestdefense.base.GameUtil;

import javafx.scene.canvas.GraphicsContext;

public class Beast extends GameObject {

    // 敌人名字
    protected String name;

    // 敌人类型，例如 SNAKE、WOLF、TIGER
    protected EnemyType type;

    // 当前生命值
    protected int hp;

    // 最大生命值
    protected int maxHp;

    // 移动速度
    // 蛇快，狼中等，老虎慢
    protected double speed;

    // 单次攻击伤害
    protected int attackDamage;

    // 攻击间隔，单位是秒
    protected double attackInterval;

    // 上一次攻击时间
    protected long lastAttackTime;

    // 图片路径
    protected String imagePath;

    public Beast() {
        super();
        this.lastAttackTime = 0;
    }

    // 带参数的构造方法
    // name：敌人名字
    // type：敌人类型
    // maxHp：最大生命值
    // speed：移动速度
    // attackDamage：单次攻击伤害
    // attackInterval：攻击间隔
    // row：敌人出现在哪一行
    // x：敌人当前横向位置
    // imagePath：图片路径
    public Beast(String name, EnemyType type, int maxHp, double speed,
                 int attackDamage, double attackInterval,
                 int row, double x, String imagePath) {

        // 敌人使用 row + x。
        // x 是横向编号，需要转换成屏幕坐标。
        super(GameUtil.colToScreenX((int) x),
              GameUtil.rowToScreenY(row),
              GameConstant.CELL_SIZE,
              GameConstant.CELL_SIZE,
              row,
              (int) x);

        this.name = name;
        this.type = type;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.speed = speed;
        this.attackDamage = attackDamage;
        this.attackInterval = attackInterval;
        this.imagePath = imagePath;

        this.lastAttackTime = 0;
    }

    // 敌人向左移动
    public void move() {
        setX(getX() - speed);
    }

    // 敌人受到伤害
    public void takeDamage(int damage) {
        hp -= damage;

        if (hp <= 0) {
            hp = 0;
            setAlive(false);
        }
    }

    // 判断是否可以攻击动物
    public boolean canAttack() {
        long currentTime = System.currentTimeMillis();

        // attackInterval 是秒，所以乘 1000 变成毫秒
        return currentTime - lastAttackTime >= attackInterval * 1000;
    }

    // 攻击后更新攻击时间
    public void updateAttackTime() {
        lastAttackTime = System.currentTimeMillis();
    }

    // 判断敌人是否死亡
    public boolean isDead() {
        return !isAlive();
    }

    @Override
    public void draw(GraphicsContext gc) {
        Color bodyColor;
    
        if (type == EnemyType.SNAKE) {
            bodyColor = Color.web("#6BAA45");
        } else if (type == EnemyType.WOLF) {
            bodyColor = Color.web("#66727A");
        } else {
            bodyColor = Color.web("#D06B32");
        }
    
        double padding = 10;
    
        gc.setFill(bodyColor);
        gc.fillOval(
                getX() + padding,
                getY() + padding,
                getWidth() - padding * 2,
                getHeight() - padding * 2
        );
    
        gc.setStroke(Color.web("#3B2923"));
        gc.setLineWidth(2);
        gc.strokeOval(
                getX() + padding,
                getY() + padding,
                getWidth() - padding * 2,
                getHeight() - padding * 2
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
    
        gc.setFill(Color.web("#E2574C"));
        gc.fillRect(
                getX() + 10,
                getY() + 4,
                (getWidth() - 20) * healthRatio,
                5
        );
    }

    // 每帧更新方法
    // 默认每帧向左移动
    @Override
    public void update() {
        move();
    }

    // Getter

    public String getName() {
        return name;
    }

    public EnemyType getType() {
        return type;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public double getSpeed() {
        return speed;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public double getAttackInterval() {
        return attackInterval;
    }

    public long getLastAttackTime() {
        return lastAttackTime;
    }

    public String getImagePath() {
        return imagePath;
    }
}
