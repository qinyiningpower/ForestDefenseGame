package com.forestdefense.entity;

import com.forestdefense.base.AnimalType;
import com.forestdefense.base.GameConstant;

public class Squirrel extends ResourceAnimal {

    public Squirrel(int row, int col) {

        // name：名字
        // type：动物类型
        // maxHp：生命值 100
        // cost：购买价格 50 果子
        // row、col：放置位置
        // imagePath：图片路径
        // produceAmount：每次生产 25 果子
        // produceInterval：每 4 秒生产一次
        super("Squirrel", AnimalType.SQUIRREL,
              GameConstant.SQUIRREL_HP, GameConstant.SQUIRREL_COST,
              row, col, "image/squirrel.png",
              GameConstant.SQUIRREL_FRUIT_AMOUNT,
              GameConstant.SQUIRREL_FRUIT_INTERVAL);
    }
}
