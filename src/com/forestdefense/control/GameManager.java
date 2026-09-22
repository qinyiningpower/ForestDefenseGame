package com.forestdefense.control;

import com.forestdefense.base.GameState;
import com.forestdefense.logic.GameRule;
import com.forestdefense.logic.ResourceManager;
import com.forestdefense.logic.WaveManager;

/**
 * Central coordinator for the game lifecycle.
 *
 * Manages game state and provides access to the core gameplay managers.
 * Rendering and input components will be connected separately.
 */
public final class GameManager {

    private static final GameManager INSTANCE = new GameManager();

    private GameState currentState;
    private GameRule gameRule;
    private WaveManager waveManager;
    private ResourceManager resourceManager;

    private GameManager() {
        initializeGameData();
    }

    /**
     * Returns the single GameManager instance.
     */
    public static GameManager getInstance() {
        return INSTANCE;
    }

    /**
     * Initializes all gameplay managers and restores the READY state.
     */
    public void initGame() {
        initializeGameData();
    }

    private void initializeGameData() {
        gameRule = new GameRule();
        waveManager = new WaveManager();
        resourceManager = new ResourceManager();
        currentState = GameState.READY;
    }

    /**
     * Starts a new game or continues from the ready state.
     */
    public void startGame() {
        if (currentState == GameState.READY) {
            currentState = GameState.RUNNING;
        }
    }

    /**
     * Pauses the currently running game.
     */
    public void pauseGame() {
        if (currentState == GameState.RUNNING) {
            currentState = GameState.PAUSED;
        }
    }

    /**
     * Resumes a paused game.
     */
    public void resumeGame() {
        if (currentState == GameState.PAUSED) {
            currentState = GameState.RUNNING;
        }
    }

    /**
     * Restores all managers and returns the game to its initial state.
     */
    public void resetGame() {
        initializeGameData();
    }

    /**
     * Changes the state to WIN or LOSE.
     */
    public void gameOver(boolean isWin) {
        currentState = isWin ? GameState.WIN : GameState.LOSE;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public GameRule getGameRule() {
        return gameRule;
    }

    public WaveManager getWaveManager() {
        return waveManager;
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }
}
