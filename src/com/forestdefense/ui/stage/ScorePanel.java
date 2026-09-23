package com.forestdefense.ui.stage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Displays the current game statistics and status.
 */
public final class ScorePanel {

    private final HBox panel;

    private final Label fruitLabel;
    private final Label waveLabel;
    private final Label killLabel;
    private final Label statusLabel;
    private final Label eggStatusLabel;

    public ScorePanel() {
        fruitLabel = createLabel("Fruits: 0");
        waveLabel = createLabel("Wave: 0 / 0");
        killLabel = createLabel("Kills: 0");
        statusLabel = createLabel("Status: Ready");
        eggStatusLabel = createLabel("Egg: Safe");

        panel = new HBox(
                18,
                fruitLabel,
                waveLabel,
                killLabel,
                statusLabel,
                eggStatusLabel
        );

        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(10, 16, 10, 16));

        panel.setStyle(
                "-fx-background-color: #244F45;"
                + "-fx-background-radius: 12;"
                + "-fx-border-color: #4F7A68;"
                + "-fx-border-radius: 12;"
        );
    }

    private Label createLabel(String text) {
        Label label = new Label(text);

        label.setStyle(
                "-fx-font-size: 14px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: #F4E9C9;"
        );

        return label;
    }

    public HBox getPanel() {
        return panel;
    }

    public void updateFruits(int amount) {
        fruitLabel.setText("Fruits: " + amount);

        if (amount < 50) {
            fruitLabel.setStyle(
                    "-fx-font-size: 14px;"
                    + "-fx-font-weight: bold;"
                    + "-fx-text-fill: #FF9A8D;"
            );
        } else {
            fruitLabel.setStyle(
                    "-fx-font-size: 14px;"
                    + "-fx-font-weight: bold;"
                    + "-fx-text-fill: #F4E9C9;"
            );
        }
    }

    public void updateWave(int wave, int totalWaves) {
        waveLabel.setText(
                "Wave: " + wave + " / " + totalWaves
        );
    }

    public void updateKills(int kills) {
        killLabel.setText("Kills: " + kills);
    }

    public void updateStatus(String status) {
        statusLabel.setText("Status: " + status);
    }

    public void updateEggStatus(boolean alive) {
        if (alive) {
            eggStatusLabel.setText("Egg: Safe");
            eggStatusLabel.setStyle(
                    "-fx-font-size: 14px;"
                    + "-fx-font-weight: bold;"
                    + "-fx-text-fill: #9DDAA4;"
            );
        } else {
            eggStatusLabel.setText("Egg: Destroyed");
            eggStatusLabel.setStyle(
                    "-fx-font-size: 14px;"
                    + "-fx-font-weight: bold;"
                    + "-fx-text-fill: #FF7F73;"
            );
        }
    }
}
