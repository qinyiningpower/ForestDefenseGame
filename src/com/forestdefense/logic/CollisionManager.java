package com.forestdefense.logic;

import java.util.ArrayList;

import com.forestdefense.base.GameUtil;
import com.forestdefense.entity.Animal;
import com.forestdefense.entity.Beast;
import com.forestdefense.entity.Bullet;

public class CollisionManager {

    public CollisionManager() {
    }

    // 判断子弹是否碰到敌人
    public void checkBulletHitBeast(ArrayList<Bullet> bullets,
                                    ArrayList<Beast> beasts) {

        for (Bullet bullet : bullets) {

            if (!bullet.isActive()) {
                continue;
            }

            for (Beast beast : beasts) {

                if (!beast.isAlive()) {
                    continue;
                }

                // 必须在同一行
                if (bullet.getRow() != beast.getRow()) {
                    continue;
                }

                // 使用 GameObject 里的矩形碰撞检测
                if (bullet.intersects(beast)) {

                    // 敌人扣血
                    beast.takeDamage(bullet.getDamage());

                    // 子弹消失
                    bullet.setActive(false);

                    break;
                }
            }
        }
    }

    // 判断敌人是否碰到动物
    public Animal getCollidedAnimal(Beast beast,
                                    ArrayList<Animal> animals) {

        for (Animal animal : animals) {

            if (!animal.isAlive()) {
                continue;
            }

            // 必须在同一行
            if (animal.getRow() != beast.getRow()) {
                continue;
            }

            // 使用 GameObject 里的矩形碰撞检测
            if (beast.intersects(animal)) {
                return animal;
            }
        }

        return null;
    }

    // 判断敌人是否到达鸟蛋位置
    public boolean isBeastReachEgg(Beast beast) {

        // 敌人超出左边界，表示已经到达鸟蛋区域
        return GameUtil.isOutOfLeftBoundary(beast.getX());
    }

    // 判断敌人是否到达森林陷阱位置
    public boolean isBeastReachTrap(Beast beast) {

        // 陷阱位置在地图最左侧附近
        return beast.getCol() <= 1;
    }
}
