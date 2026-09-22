package com.forestdefense.entity;

import com.forestdefense.base.EnemyType;
import com.forestdefense.base.GameConstant;

public class Wolf extends Beast {

    public Wolf(int row, double x) {

        // name：敌人名字
        // type：敌人类型
        // maxHp：生命值 70
        // speed：移动速度，中等速度
        // attackDamage：每次攻击造成 20 点伤害
        // attackInterval：每 0.75 秒攻击一次
        // row：野狼出现的行
        // x：野狼出现的横向位置
        // imagePath：图片路径
        super("Wolf", EnemyType.WOLF,
              GameConstant.WOLF_HP,
              EnemyType.WOLF.getActualSpeed(),
              GameConstant.WOLF_DAMAGE,
              GameConstant.WOLF_ATTACK_INTERVAL,
              row, x,
              "image/wolf.png");
    }
}
