package com.forestdefense.entity;

import com.forestdefense.base.AnimalType;

public class DefenseAnimal extends Animal {

    // 防御值，目前主要给乌龟使用
    protected int defenseValue;

    public DefenseAnimal() {
        super();
    }

    // 带参数的构造方法
    // name：动物名字
    // type：动物类型
    // maxHp：最大生命值
    // cost：购买需要的果子数量
    // row、col：动物放置在地图上的行和列
    // imagePath：图片路径
    // defenseValue：防御值
    public DefenseAnimal(String name, AnimalType type, int maxHp, int cost,
                         int row, int col, String imagePath,
                         int defenseValue) {

        // 调用 Animal 父类构造方法
        super(name, type, maxHp, cost, row, col, imagePath);

        this.defenseValue = defenseValue;
    }

    public int getDefenseValue() {
        return defenseValue;
    }
}
