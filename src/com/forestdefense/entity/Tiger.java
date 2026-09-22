package com.forestdefense.entity;

import com.forestdefense.base.EnemyType;
import com.forestdefense.base.GameConstant;

public class Tiger extends Beast {

    public Tiger(int row, double x) {

        // name：敌人名字
        // type：敌人类型
        // maxHp：生命值 100
        // speed：移动速度，老虎速度最慢
        // attackDamage：每次攻击造成 30 点伤害
        // attackInterval：每 0.5 秒攻击一次
        // row：老虎出现的行
        // x：老虎出现的横向位置
        // imagePath：图片路径
        super("Tiger", EnemyType.TIGER,
              GameConstant.TIGER_HP,
              EnemyType.TIGER.getActualSpeed(),
              GameConstant.TIGER_DAMAGE,
              GameConstant.TIGER_ATTACK_INTERVAL,
              row, x,
              "image/tiger.png");
    }
}
