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

    // 每波之间的时间间隔，单位：毫秒
    private long waveInterval;

    // 上一次生成波次的时间
    private long lastWaveTime;

    public WaveManager() {
        this.currentLevel = 1;
        this.currentWave = 0;
        this.maxWave = GameConstant.SIMPLE_WAVES;

        // GameConstant.WAVE_INTERVAL 单位是秒，这里转换成毫秒
        this.waveInterval =
                (long) (GameConstant.WAVE_INTERVAL * 1000);

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

    // 判断当前是否可以生成下一波敌人
    public boolean canGenerateWave() {

        long currentTime = System.currentTimeMillis();

        return currentWave < maxWave
                && currentTime - lastWaveTime >= waveInterval;
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

        /*
         * GameConstant.GRID_COLS 表示网格右侧边界。
         * 敌人会从棋盘最右边进入游戏区域。
         */
        double spawnX = GameConstant.GRID_COLS;

        if (currentWave == 1) {

            beasts.add(new Snake(0, spawnX));
            beasts.add(new Snake(1, spawnX));
        }

        else if (currentWave == 2) {

            beasts.add(new Snake(0, spawnX));
            beasts.add(new Snake(2, spawnX));
            beasts.add(new Wolf(1, spawnX));
        }

        else if (currentWave == 3) {

            beasts.add(new Snake(0, spawnX));
            beasts.add(new Wolf(1, spawnX));
            beasts.add(new Wolf(2, spawnX));
        }

        else if (currentWave == 4) {

            beasts.add(new Wolf(0, spawnX));
            beasts.add(new Tiger(1, spawnX));
            beasts.add(new Snake(2, spawnX));
        }

        else if (currentWave == 5) {

            beasts.add(new Tiger(0, spawnX));
            beasts.add(new Wolf(1, spawnX));
            beasts.add(new Tiger(2, spawnX));
        }
    }

    // 第二关波次
    private void generateLevelTwoWave(ArrayList<Beast> beasts) {

        /*
         * 第二关难度更高：
         * 敌人数量更多，并且老虎更早出现。
         */
        double spawnX = GameConstant.GRID_COLS;

        if (currentWave == 1) {

            beasts.add(new Snake(0, spawnX));
            beasts.add(new Snake(1, spawnX));
            beasts.add(new Snake(2, spawnX));
        }

        else if (currentWave == 2) {

            beasts.add(new Snake(0, spawnX));
            beasts.add(new Wolf(1, spawnX));
            beasts.add(new Wolf(2, spawnX));
        }

        else if (currentWave == 3) {

            beasts.add(new Wolf(0, spawnX));
            beasts.add(new Wolf(1, spawnX));
            beasts.add(new Tiger(2, spawnX));
        }

        else if (currentWave == 4) {

            beasts.add(new Tiger(0, spawnX));
            beasts.add(new Wolf(1, spawnX));
            beasts.add(new Tiger(2, spawnX));
        }

        else if (currentWave == 5) {

            beasts.add(new Tiger(0, spawnX));
            beasts.add(new Tiger(1, spawnX));
            beasts.add(new Wolf(2, spawnX));
        }

        else if (currentWave == 6) {

            beasts.add(new Tiger(0, spawnX));
            beasts.add(new Tiger(1, spawnX));
            beasts.add(new Tiger(2, spawnX));
        }
    }

    // 判断所有波次是否已经生成完成
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
