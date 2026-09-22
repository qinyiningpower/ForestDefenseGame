package com.forestdefense.logic;

import java.util.ArrayList;

import com.forestdefense.base.GameConstant;
import com.forestdefense.entity.Beast;
import com.forestdefense.entity.Snake;
import com.forestdefense.entity.Wolf;
import com.forestdefense.entity.Tiger;

public class WaveManager {

    // 当前关卡
    private int currentLevel;

    // 当前波次
    private int currentWave;

    // 总波次数
    private int maxWave;

    // 每波之间间隔
    private long waveInterval;

    // 上一次生成敌人的时间
    private long lastWaveTime;

    public WaveManager() {
        this.currentLevel = 1;
        this.currentWave = 0;
        this.maxWave = GameConstant.SIMPLE_WAVES;

        // GameConstant.WAVE_INTERVAL 单位是秒，这里转成毫秒
        this.waveInterval = (long) (GameConstant.WAVE_INTERVAL * 1000);

        this.lastWaveTime = System.currentTimeMillis();
    }

    // 设置当前关卡
    public void setLevel(int level) {
        this.currentLevel = level;
        this.currentWave = 0;
        this.lastWaveTime = System.currentTimeMillis();

        if (level == 1) {
            this.maxWave = GameConstant.SIMPLE_WAVES;
        } else if (level == 2) {
            this.maxWave = GameConstant.DIFFICULT_WAVES;
        }
    }

    // 判断是否可以生成下一波
    public boolean canGenerateWave() {
        long currentTime = System.currentTimeMillis();

        return currentWave < maxWave &&
               currentTime - lastWaveTime >= waveInterval;
    }

    // 生成下一波敌人
    public void generateNextWave(ArrayList<Beast> beasts) {

        if (!canGenerateWave()) {
            return;
        }

        currentWave++;

        if (currentLevel == 1) {
            generateLevelOneWave(beasts);
        } else if (currentLevel == 2) {
            generateLevelTwoWave(beasts);
        }

        lastWaveTime = System.currentTimeMillis();
    }

    // 第一关波次
    private void generateLevelOneWave(ArrayList<Beast> beasts) {

        // x = 9.0 表示敌人从地图最右边出现

        if (currentWave == 1) {
            beasts.add(new Snake(0, 9.0));
            beasts.add(new Snake(1, 9.0));
        }

        else if (currentWave == 2) {
            beasts.add(new Snake(0, 9.0));
            beasts.add(new Snake(2, 9.0));
            beasts.add(new Wolf(1, 9.0));
        }

        else if (currentWave == 3) {
            beasts.add(new Snake(0, 9.0));
            beasts.add(new Wolf(1, 9.0));
            beasts.add(new Wolf(2, 9.0));
        }

        else if (currentWave == 4) {
            beasts.add(new Wolf(0, 9.0));
            beasts.add(new Tiger(1, 9.0));
            beasts.add(new Snake(2, 9.0));
        }

        else if (currentWave == 5) {
            beasts.add(new Tiger(0, 9.0));
            beasts.add(new Wolf(1, 9.0));
            beasts.add(new Tiger(2, 9.0));
        }
    }

    // 第二关波次
    private void generateLevelTwoWave(ArrayList<Beast> beasts) {

        // 第二关更难：敌人数量更多，老虎出现更早

        if (currentWave == 1) {
            beasts.add(new Snake(0, 9.0));
            beasts.add(new Snake(1, 9.0));
            beasts.add(new Snake(2, 9.0));
        }

        else if (currentWave == 2) {
            beasts.add(new Snake(0, 9.0));
            beasts.add(new Wolf(1, 9.0));
            beasts.add(new Wolf(2, 9.0));
        }

        else if (currentWave == 3) {
            beasts.add(new Wolf(0, 9.0));
            beasts.add(new Wolf(1, 9.0));
            beasts.add(new Tiger(2, 9.0));
        }

        else if (currentWave == 4) {
            beasts.add(new Tiger(0, 9.0));
            beasts.add(new Wolf(1, 9.0));
            beasts.add(new Tiger(2, 9.0));
        }

        else if (currentWave == 5) {
            beasts.add(new Tiger(0, 9.0));
            beasts.add(new Tiger(1, 9.0));
            beasts.add(new Wolf(2, 9.0));
        }

        else if (currentWave == 6) {
            beasts.add(new Tiger(0, 9.0));
            beasts.add(new Tiger(1, 9.0));
            beasts.add(new Tiger(2, 9.0));
        }
    }

    // 判断所有波次是否结束
    public boolean isAllWavesFinished() {
        return currentWave >= maxWave;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getCurrentWave() {
        return currentWave;
    }

    public int getMaxWave() {
        return maxWave;
    }
}
