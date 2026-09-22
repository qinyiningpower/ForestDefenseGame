package com.forestdefense.entity;

import com.forestdefense.base.AnimalType;

public class ResourceAnimal extends Animal {

    // 每次生产多少果子
    // 松鼠每次生产 25 个果子
    protected int produceAmount;

    // 生产间隔，单位是秒
    // 松鼠每 4 秒生产一次果子
    protected double produceInterval;

    // 上一次生产果子的时间
    protected long lastProduceTime;

    public ResourceAnimal() {
        super();

        // 默认还没有生产过果子
        this.lastProduceTime = 0;
    }

    // 带参数的构造方法
    // name：动物名字
    // type：动物类型
    // maxHp：最大生命值
    // cost：购买需要的果子数量
    // row、col：动物放置在地图上的行和列
    // imagePath：图片路径
    // produceAmount：每次生产多少果子
    // produceInterval：生产间隔
    public ResourceAnimal(String name, AnimalType type, int maxHp, int cost,
                          int row, int col, String imagePath,
                          int produceAmount, double produceInterval) {

        // 调用 Animal 父类构造方法
        // cost 已经在 Animal 里面保存了
        super(name, type, maxHp, cost, row, col, imagePath);

        this.produceAmount = produceAmount;
        this.produceInterval = produceInterval;

        // 初始时还没有生产过果子
        this.lastProduceTime = 0;
    }

    // 判断当前是否可以生产果子
    public boolean canProduce() {
        long currentTime = System.currentTimeMillis();

        // produceInterval 是秒，所以乘 1000 变成毫秒
        return currentTime - lastProduceTime >= produceInterval * 1000;
    }

    // 生产果子后更新时间
    public void updateProduceTime() {
        lastProduceTime = System.currentTimeMillis();
    }

    // 获取每次生产的果子数量
    public int getProduceAmount() {
        return produceAmount;
    }

    // 获取生产间隔
    public double getProduceInterval() {
        return produceInterval;
    }

    // 获取上一次生产果子的时间
    public long getLastProduceTime() {
        return lastProduceTime;
    }

    // 设置每次生产的果子数量
    public void setProduceAmount(int produceAmount) {
        this.produceAmount = produceAmount;
    }

    // 设置生产间隔
    public void setProduceInterval(double produceInterval) {
        this.produceInterval = produceInterval;
    }
}
