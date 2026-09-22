package com.forestdefense.entity;

import com.forestdefense.base.EnemyType;
import com.forestdefense.base.GameConstant;

public class Snake extends Beast {

    public Snake(int row, double x) {

        // name：敌人名字
        // type：敌人类型
        // maxHp：生命值 40
        // speed：移动速度，蛇速度最快
        // attackDamage：每次攻击造成 10 点伤害
        // attackInterval：每 1 秒攻击一次
        // row：蛇出现的行
        // x：蛇出现的横向位置
        // imagePath：图片路径
        super("Snake", EnemyType.SNAKE,
              GameConstant.SNAKE_HP,
              EnemyType.SNAKE.getActualSpeed(),
              GameConstant.SNAKE_DAMAGE,
              GameConstant.SNAKE_ATTACK_INTERVAL,
              row, x,
              "image/snake.png");
    }
}
