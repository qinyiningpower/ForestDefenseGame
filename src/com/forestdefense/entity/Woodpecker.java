package com.forestdefense.entity;

import com.forestdefense.base.AnimalType;
import com.forestdefense.base.GameConstant;

public class Woodpecker extends AttackAnimal {

    public Woodpecker(int row, int col) {

        // name：名字
        // type：动物类型
        // maxHp：生命值 100
        // cost：购买价格 125 果子
        // row、col：放置位置
        // imagePath：图片路径
        // damage：单次伤害 10
        // attackInterval：每 0.25 秒攻击一次
        // rangedAttack：false，表示近距离攻击
        super("Woodpecker", AnimalType.WOODPECKER,
              GameConstant.WOODPECKER_HP, GameConstant.WOODPECKER_COST,
              row, col, "image/woodpecker.png",
              GameConstant.WOODPECKER_DAMAGE,
              GameConstant.WOODPECKER_ATTACK_INTERVAL,
              false);
    }
}
