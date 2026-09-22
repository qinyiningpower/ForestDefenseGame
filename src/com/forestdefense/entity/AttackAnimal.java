package com.forestdefense.entity;

import com.forestdefense.base.AnimalType;

public class AttackAnimal extends Animal {

    // 单次攻击伤害
    // 刺猬 = 20，啄木鸟 = 10，狐狸 = 40
    protected int damage;

    // 攻击间隔，单位是秒
    // 刺猬 = 0.75，啄木鸟 = 0.25，狐狸 = 1.5
    protected double attackInterval;

    // 上一次攻击的时间，用来判断现在能不能再次攻击
    protected long lastAttackTime;

    // 是否是远程攻击
    // 刺猬和狐狸是 true，啄木鸟是 false
    protected boolean rangedAttack;

    public AttackAnimal() {
        super();

        // 默认还没有攻击过
        this.lastAttackTime = 0;
    }

    // 带参数的构造方法
    // name：动物名字
    // type：动物类型
    // maxHp：最大生命值
    // cost：购买需要的果子数量
    // row、col：动物放置在地图上的行和列
    // imagePath：图片路径
    // damage：单次攻击伤害
    // attackInterval：攻击间隔
    // rangedAttack：是否远程攻击
    public AttackAnimal(String name, AnimalType type, int maxHp, int cost,
                        int row, int col, String imagePath,
                        int damage, double attackInterval,
                        boolean rangedAttack) {

        // 调用 Animal 父类的构造方法
        // 这里已经把购买价格 cost 存进 Animal 里面了
        super(name, type, maxHp, cost, row, col, imagePath);

        this.damage = damage;
        this.attackInterval = attackInterval;
        this.rangedAttack = rangedAttack;

        // 初始时还没有攻击过
        this.lastAttackTime = 0;
    }

    // 判断当前是否可以攻击
    public boolean canAttack() {
        long currentTime = System.currentTimeMillis();

        // attackInterval 是秒，所以要乘 1000 变成毫秒
        return currentTime - lastAttackTime >= attackInterval * 1000;
    }

    // 攻击后更新攻击时间
    public void updateAttackTime() {
        lastAttackTime = System.currentTimeMillis();
    }

    // 获取单次攻击伤害
    public int getDamage() {
        return damage;
    }

    // 获取攻击间隔
    public double getAttackInterval() {
        return attackInterval;
    }

    // 判断是不是远程攻击
    public boolean isRangedAttack() {
        return rangedAttack;
    }

    // 获取上次攻击时间
    public long getLastAttackTime() {
        return lastAttackTime;
    }

    // 设置单次攻击伤害
    public void setDamage(int damage) {
        this.damage = damage;
    }

    // 设置攻击间隔
    public void setAttackInterval(double attackInterval) {
        this.attackInterval = attackInterval;
    }

    // 设置是否远程攻击
    public void setRangedAttack(boolean rangedAttack) {
        this.rangedAttack = rangedAttack;
    }
}
