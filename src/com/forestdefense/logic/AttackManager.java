package com.forestdefense.logic;

import java.util.ArrayList;

import com.forestdefense.base.GameConstant;
import com.forestdefense.entity.Animal;
import com.forestdefense.entity.AttackAnimal;
import com.forestdefense.entity.Beast;
import com.forestdefense.entity.Bullet;

public class AttackManager {

    private CollisionManager collisionManager;

    public AttackManager() {
        this.collisionManager = new CollisionManager();
    }

    // 处理攻击动物的攻击
    // 刺猬和狐狸是远程攻击，会生成 Bullet
    // 啄木鸟是近距离攻击，会直接扣敌人血量
    public void animalAttack(ArrayList<AttackAnimal> animals,
                             ArrayList<Beast> beasts,
                             ArrayList<Bullet> bullets) {

        for (AttackAnimal animal : animals) {

            // 动物死亡就不能攻击
            if (!animal.isAlive()) {
                continue;
            }

            // 没到攻击时间就不能攻击
            if (!animal.canAttack()) {
                continue;
            }

            // 找同一行、在动物右边最近的敌人
            // 这是目标选择，不是碰撞判断，所以不放在 CollisionManager
            Beast target = findTarget(animal, beasts);

            if (target == null) {
                continue;
            }

            // 远程攻击：生成子弹
            if (animal.isRangedAttack()) {
                Bullet bullet = new Bullet(
                        animal.getDamage(),
                        GameConstant.BULLET_SPEED,
                        animal.getRow(),
                        animal.getCol()
                );

                bullets.add(bullet);
            }

            // 近距离攻击：直接扣血
            else {
                target.takeDamage(animal.getDamage());
            }

            // 更新攻击时间
            animal.updateAttackTime();
        }
    }

    // 处理敌人攻击动物
    public void beastAttack(ArrayList<Beast> beasts,
                            ArrayList<Animal> animals) {

        for (Beast beast : beasts) {

            if (!beast.isAlive()) {
                continue;
            }

            if (!beast.canAttack()) {
                continue;
            }

            // 使用 CollisionManager 判断敌人是否碰到动物
            Animal target = collisionManager.getCollidedAnimal(beast, animals);

            if (target == null) {
                continue;
            }

            // 敌人攻击动物，扣动物 hp
            target.takeDamage(beast.getAttackDamage());

            // 更新敌人攻击时间
            beast.updateAttackTime();
        }
    }

    // 找攻击动物同一行右边最近的敌人
    private Beast findTarget(AttackAnimal animal,
                             ArrayList<Beast> beasts) {

        Beast target = null;
        double minX = Double.MAX_VALUE;

        for (Beast beast : beasts) {

            if (!beast.isAlive()) {
                continue;
            }

            if (beast.getRow() != animal.getRow()) {
                continue;
            }

            // 敌人在动物右边才可以被攻击
            if (beast.getX() < animal.getX()) {
                continue;
            }

            // 找最近的敌人
            if (beast.getX() < minX) {
                minX = beast.getX();
                target = beast;
            }
        }

        return target;
    }
}
