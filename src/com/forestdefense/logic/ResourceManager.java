package com.forestdefense.logic;

import com.forestdefense.base.GameConstant;
import com.forestdefense.entity.Animal;
import com.forestdefense.entity.Fruit;

public class ResourceManager {

    // 玩家当前拥有的果子数量
    private int fruitCount;

    public ResourceManager() {

        // 玩家开局拥有 50 个果子
        this.fruitCount = GameConstant.INITIAL_FRUITS;
    }

    // 获取当前果子数量
    public int getFruitCount() {
        return fruitCount;
    }

    // 收集果子
    public void collectFruit(Fruit fruit) {

        // 果子已经被收集，不能重复收集
        if (!fruit.isActive()) {
            return;
        }

        // 增加玩家资源
        fruitCount += fruit.getAmount();

        // 果子消失
        fruit.collect();
    }

    // 判断果子是否足够购买动物
    public boolean canBuyAnimal(Animal animal) {

        return fruitCount >= animal.getCost();
    }

    // 购买动物
    public boolean buyAnimal(Animal animal) {

        // 果子不够
        if (!canBuyAnimal(animal)) {
            return false;
        }

        // 扣除果子
        fruitCount -= animal.getCost();

        return true;
    }
}
