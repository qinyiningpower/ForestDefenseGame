package com.forestdefense.entity;

import com.forestdefense.base.AnimalType;
import com.forestdefense.base.GameConstant;

public class Hedgehog extends AttackAnimal {

    public Hedgehog(int row, int col) {

        // name：名字
        // type：动物类型
        // maxHp：生命值 100
        // cost：购买价格 100 果子
        // row、col：放置位置
        // imagePath：图片路径
        // damage：单次伤害 20
        // attackInterval：每 0.75 秒攻击一次
        // rangedAttack：true，表示远程攻击
        super("Hedgehog", AnimalType.HEDGEHOG,
              GameConstant.HEDGEHOG_HP, GameConstant.HEDGEHOG_COST,
              row, col, "image/hedgehog.png",
              GameConstant.HEDGEHOG_DAMAGE,
              GameConstant.HEDGEHOG_ATTACK_INTERVAL,
              true);
    }
}
