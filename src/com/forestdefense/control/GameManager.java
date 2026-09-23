package com.forestdefense.control;

import com.forestdefense.base.GameState;
import com.forestdefense.logic.GameRule;
import com.forestdefense.logic.GameWorld;
import com.forestdefense.logic.ResourceManager;
import com.forestdefense.logic.WaveManager;

/**
 * Central coordinator for the game lifecycle.
 *
 * Manages the game state, world data and core gameplay managers.
 */
public final class GameManager {

    private static final GameManager INSTANCE = new GameManager();

    private GameState currentState;
    private GameWorld gameWorld;
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
     * Initializes the game world and all gameplay managers.
     */
    public void initGame() {
        initializeGameData();
    }

    private void initializeGameData() {
        gameWorld = new GameWorld();
        gameRule = new GameRule();
        waveManager = new WaveManager();
        resourceManager = new ResourceManager();
        currentState = GameState.READY;
    }

    /**
     * Starts the game from the ready state.
     */
    public void startGame() {
        if (currentState == GameState.READY) {
            currentState = GameState.RUNNING;
        }
    }

    /**
     * Pauses a running game.
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
     * Restores the world and gameplay managers to their initial state.
     */
    public void resetGame() {
        initializeGameData();
    }

    /**
     * Changes the game state to WIN or LOSE.
     */
    public void gameOver(boolean isWin) {
        currentState = isWin ? GameState.WIN : GameState.LOSE;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public GameWorld getGameWorld() {
        return gameWorld;
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
