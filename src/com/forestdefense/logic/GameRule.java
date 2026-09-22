package com.forestdefense.logic;

import java.util.ArrayList;

import com.forestdefense.entity.Animal;
import com.forestdefense.entity.Beast;
import com.forestdefense.entity.Bullet;
import com.forestdefense.entity.Fruit;
import com.forestdefense.entity.Trap;

public class GameRule {

    private CollisionManager collisionManager;

    public GameRule() {
        this.collisionManager = new CollisionManager();
    }

    // 检查森林陷阱是否触发
    // 当敌人到达陷阱位置，并且该行陷阱还没有使用过时，
    // 陷阱会清除这一整条路上的所有敌人。
    public void checkTrap(ArrayList<Beast> beasts,
                          ArrayList<Trap> traps) {

        for (Trap trap : traps) {

            // 陷阱已经使用过，就跳过
            if (!trap.isActive()) {
                continue;
            }

            for (Beast beast : beasts) {

                // 死亡敌人不用判断
                if (!beast.isAlive()) {
                    continue;
                }

                // 陷阱和敌人必须在同一行
                if (beast.getRow() != trap.getRow()) {
                    continue;
                }

                // 敌人到达陷阱位置，触发陷阱
                if (collisionManager.isBeastReachTrap(beast)) {

                    // 清除该行所有敌人
                    clearBeastsInRow(beasts, trap.getRow());

                    // 陷阱只能使用一次
                    trap.trigger();

                    break;
                }
            }
        }
    }

    // 清除某一行的所有敌人
    private void clearBeastsInRow(ArrayList<Beast> beasts, int row) {

        for (Beast beast : beasts) {

            if (beast.getRow() == row) {

                // 直接扣掉当前全部生命值，使敌人死亡
                beast.takeDamage(beast.getHp());
            }
        }
    }

    // 判断游戏是否失败
    // 如果敌人到达鸟蛋位置，说明游戏失败。
    // 正常流程里应该先调用 checkTrap()，
    // 所以第一次突破会先触发陷阱；
    // 如果陷阱已经用过，再次突破才会失败。
    public boolean checkLose(ArrayList<Beast> beasts) {

        for (Beast beast : beasts) {

            if (!beast.isAlive()) {
                continue;
            }

            if (collisionManager.isBeastReachEgg(beast)) {
                return true;
            }
        }

        return false;
    }

    // 判断游戏是否胜利
    // 所有波次已经生成完，并且场上没有活着的敌人，就胜利。
    public boolean checkWin(boolean allWavesFinished,
                            ArrayList<Beast> beasts) {

        if (!allWavesFinished) {
            return false;
        }

        for (Beast beast : beasts) {

            if (beast.isAlive()) {
                return false;
            }
        }

        return true;
    }

    // 删除已经死亡或失效的对象
    // 动物 hp = 0 后删除
    // 敌人 hp = 0 后删除
    // 子弹命中后删除
    // 果子被收集后删除
    // 陷阱触发后删除
    public void removeDeadObjects(ArrayList<Animal> animals,
                                  ArrayList<Beast> beasts,
                                  ArrayList<Bullet> bullets,
                                  ArrayList<Fruit> fruits,
                                  ArrayList<Trap> traps) {

        animals.removeIf(animal -> !animal.isAlive());
        beasts.removeIf(beast -> !beast.isAlive());
        bullets.removeIf(bullet -> !bullet.isActive());
        fruits.removeIf(fruit -> !fruit.isActive());
        traps.removeIf(trap -> !trap.isActive());
    }
}
