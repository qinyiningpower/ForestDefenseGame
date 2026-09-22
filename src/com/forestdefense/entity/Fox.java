package com.forestdefense.entity;

import com.forestdefense.base.AnimalType;
import com.forestdefense.base.GameConstant;

public class Fox extends AttackAnimal {

    public Fox(int row, int col) {

        // name：名字
        // type：动物类型
        // maxHp：生命值 100
        // cost：购买价格 175 果子
        // row、col：放置位置
        // imagePath：图片路径
        // damage：单次伤害 40
        // attackInterval：每 1.5 秒攻击一次
        // rangedAttack：true，表示远程攻击
        super("Fox", AnimalType.FOX,
              GameConstant.FOX_HP, GameConstant.FOX_COST,
              row, col, "image/fox.png",
              GameConstant.FOX_DAMAGE,
              GameConstant.FOX_ATTACK_INTERVAL,
              true);
    }
}
