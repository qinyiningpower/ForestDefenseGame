menuBar
        .getPauseButton()
        .setOnAction(event -> {

            gameManager
                    .pauseGame();

            gameManager
                    .getWaveManager()
                    .pauseTimer();

            mouseControl
                    .setEnabled(false);

            scorePanel
                    .updateStatus(
                            "Paused"
                    );

            menuBar
                    .updateButtonState(
                            gameManager
                                    .getCurrentState()
                    );
        });
