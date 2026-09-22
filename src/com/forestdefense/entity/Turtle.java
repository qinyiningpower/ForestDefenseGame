package com.forestdefense.entity;

import com.forestdefense.base.AnimalType;
import com.forestdefense.base.GameConstant;

public class Turtle extends DefenseAnimal {

    public Turtle(int row, int col) {

        // 乌龟：生命值 300，购买价格 75 果子
        super("Turtle", AnimalType.TURTLE,
              GameConstant.TURTLE_HP, GameConstant.TURTLE_COST,
              row, col, "image/turtle.png",
              GameConstant.TURTLE_DEFENSE);
    }
}
