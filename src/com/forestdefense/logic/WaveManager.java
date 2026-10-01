package com.forestdefense.logic;

import java.util.ArrayList;

import com.forestdefense.base.GameConstant;
import com.forestdefense.entity.Beast;
import com.forestdefense.entity.Snake;
import com.forestdefense.entity.Tiger;
import com.forestdefense.entity.Wolf;

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

    // 波次计时是否已经开始
    private boolean timerStarted;

    // 是否处于暂停状态
    private boolean timerPaused;

    // 开始暂停的时间
    private long pauseStartTime;

    public WaveManager() {

        this.currentLevel = 1;
        this.currentWave = 0;
        this.maxWave = GameConstant.SIMPLE_WAVES;

        this.waveInterval =
                (long) (GameConstant.WAVE_INTERVAL * 1000);

        this.lastWaveTime = 0;
        this.timerStarted = false;
        this.timerPaused = false;
        this.pauseStartTime = 0;
    }

    // 设置当前关卡
    public void setLevel(int level) {

        this.currentLevel = level;
        this.currentWave = 0;

        this.lastWaveTime = 0;
        this.timerStarted = false;
        this.timerPaused = false;
        this.pauseStartTime = 0;

        if (level == 1) {

            this.maxWave =
                    GameConstant.SIMPLE_WAVES;

        } else if (level == 2) {

            this.maxWave =
                    GameConstant.DIFFICULT_WAVES;
        }
    }

    /**
     * 玩家第一次点击 Start 时启动波次计时。
     */
    public void startWaveTimer() {

        if (timerStarted) {
            return;
        }

        lastWaveTime =
                System.currentTimeMillis();

        timerStarted = true;
        timerPaused = false;
    }

    /**
     * 游戏暂停时暂停波次计时。
     */
    public void pauseTimer() {

        if (!timerStarted || timerPaused) {
            return;
        }

        pauseStartTime =
                System.currentTimeMillis();

        timerPaused = true;
    }

    /**
     * 游戏恢复时补偿暂停时间，
     * 使暂停期间不计入波次间隔。
     */
    public void resumeTimer() {

        if (!timerStarted || !timerPaused) {
            return;
        }

        long currentTime =
                System.currentTimeMillis();

        long pausedDuration =
                currentTime - pauseStartTime;

        /*
         * 把 lastWaveTime 往后推相同时间，
         * 相当于暂停期间游戏时间没有流逝。
         */
        lastWaveTime += pausedDuration;

        timerPaused = false;
        pauseStartTime = 0;
    }

    // 判断当前是否可以生成下一波敌人
    public boolean canGenerateWave() {

        if (!timerStarted || timerPaused) {
            return false;
        }

        long currentTime =
                System.currentTimeMillis();

        return currentWave < maxWave
                && currentTime - lastWaveTime
                >= waveInterval;
    }

    // 生成下一波敌人
    public void generateNextWave(
            ArrayList<Beast> beasts) {

        if (!canGenerateWave()) {
            return;
        }

        currentWave++;

        if (currentLevel == 1) {

            generateLevelOneWave(beasts);

        } else if (currentLevel == 2) {

            generateLevelTwoWave(beasts);
        }

        lastWaveTime =
                System.currentTimeMillis();
    }

    // 第一关波次
    private void generateLevelOneWave(
            ArrayList<Beast> beasts) {

        double spawnX =
                GameConstant.GRID_COLS;

        if (currentWave == 1) {

            beasts.add(
                    new Snake(0, spawnX)
            );

            beasts.add(
                    new Snake(1, spawnX)
            );
        }

        else if (currentWave == 2) {

            beasts.add(
                    new Snake(0, spawnX)
            );

            beasts.add(
                    new Snake(2, spawnX)
            );

            beasts.add(
                    new Wolf(1, spawnX)
            );
        }

        else if (currentWave == 3) {

            beasts.add(
                    new Snake(0, spawnX)
            );

            beasts.add(
                    new Wolf(1, spawnX)
            );

            beasts.add(
                    new Wolf(2, spawnX)
            );
        }

        else if (currentWave == 4) {

            beasts.add(
                    new Wolf(0, spawnX)
            );

            beasts.add(
                    new Tiger(1, spawnX)
            );

            beasts.add(
                    new Snake(2, spawnX)
            );
        }

        else if (currentWave == 5) {

            beasts.add(
                    new Tiger(0, spawnX)
            );

            beasts.add(
                    new Wolf(1, spawnX)
            );

            beasts.add(
                    new Tiger(2, spawnX)
            );
        }
    }

    // 第二关波次
    private void generateLevelTwoWave(
            ArrayList<Beast> beasts) {

        double spawnX =
                GameConstant.GRID_COLS;

        if (currentWave == 1) {

            beasts.add(
                    new Snake(0, spawnX)
            );

            beasts.add(
                    new Snake(1, spawnX)
            );

            beasts.add(
                    new Snake(2, spawnX)
            );
        }

        else if (currentWave == 2) {

            beasts.add(
                    new Snake(0, spawnX)
            );

            beasts.add(
                    new Wolf(1, spawnX)
            );

            beasts.add(
                    new Wolf(2, spawnX)
            );
        }

        else if (currentWave == 3) {

            beasts.add(
                    new Wolf(0, spawnX)
            );

            beasts.add(
                    new Wolf(1, spawnX)
            );

            beasts.add(
                    new Tiger(2, spawnX)
            );
        }

        else if (currentWave == 4) {

            beasts.add(
                    new Tiger(0, spawnX)
            );

            beasts.add(
                    new Wolf(1, spawnX)
            );

            beasts.add(
                    new Tiger(2, spawnX)
            );
        }

        else if (currentWave == 5) {

            beasts.add(
                    new Tiger(0, spawnX)
            );

            beasts.add(
                    new Tiger(1, spawnX)
            );

            beasts.add(
                    new Wolf(2, spawnX)
            );
        }

        else if (currentWave == 6) {

            beasts.add(
                    new Tiger(0, spawnX)
            );

            beasts.add(
                    new Tiger(1, spawnX)
            );

            beasts.add(
                    new Tiger(2, spawnX)
            );
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
