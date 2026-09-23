package com.forestdefense.ui.stage;

import com.forestdefense.base.AnimalType;

import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

/**
 * Displays the available defensive animals and manages selection.
 */
public final class AnimalButtonPanel {

    private final HBox panel;
    private final ToggleGroup toggleGroup;

    private final ToggleButton squirrelButton;
    private final ToggleButton hedgehogButton;
    private final ToggleButton woodpeckerButton;
    private final ToggleButton foxButton;
    private final ToggleButton turtleButton;

    private AnimalType selectedType;

    public AnimalButtonPanel() {
        toggleGroup = new ToggleGroup();

        squirrelButton = createButton(AnimalType.SQUIRREL);
        hedgehogButton = createButton(AnimalType.HEDGEHOG);
        woodpeckerButton = createButton(AnimalType.WOODPECKER);
        foxButton = createButton(AnimalType.FOX);
        turtleButton = createButton(AnimalType.TURTLE);

        panel = new HBox(
                10,
                squirrelButton,
                hedgehogButton,
                woodpeckerButton,
                foxButton,
                turtleButton
        );

        panel.setAlignment(Pos.CENTER);
        panel.setStyle(
                "-fx-padding: 12;"
                + "-fx-background-color: #173F35;"
                + "-fx-background-radius: 14;"
        );

        toggleGroup.selectedToggleProperty().addListener(
                (observable, previousToggle, selectedToggle) -> {
                    if (selectedToggle == null) {
                        selectedType = null;
                    } else {
                        selectedType = (AnimalType)
                                selectedToggle.getUserData();
                    }
                }
        );
    }

    private ToggleButton createButton(AnimalType type) {
        String name = formatName(type.getDisplayName());

        ToggleButton button = new ToggleButton(
                name + "\n" + type.getCost() + " fruits"
        );

        button.setUserData(type);
        button.setToggleGroup(toggleGroup);
        button.setPrefWidth(120);
        button.setPrefHeight(58);

        button.setStyle(
                "-fx-font-size: 13px;"
                + "-fx-font-weight: bold;"
                + "-fx-text-fill: #173F35;"
                + "-fx-background-color: #E8F1DC;"
                + "-fx-background-radius: 10;"
                + "-fx-cursor: hand;"
        );

        return button;
    }

    private String formatName(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }

        return name.substring(0, 1).toUpperCase()
                + name.substring(1);
    }

    public HBox getPanel() {
        return panel;
    }

    public AnimalType getSelectedType() {
        return selectedType;
    }

    public void clearSelection() {
        toggleGroup.selectToggle(null);
        selectedType = null;
    }

    /**
     * Disables animals that the player cannot currently afford.
     */
    public void updateButtonsAvailability(int fruits) {
        updateButtonAvailability(
                squirrelButton,
                AnimalType.SQUIRREL,
                fruits
        );

        updateButtonAvailability(
                hedgehogButton,
                AnimalType.HEDGEHOG,
                fruits
        );

        updateButtonAvailability(
                woodpeckerButton,
                AnimalType.WOODPECKER,
                fruits
        );

        updateButtonAvailability(
                foxButton,
                AnimalType.FOX,
                fruits
        );

        updateButtonAvailability(
                turtleButton,
                AnimalType.TURTLE,
                fruits
        );

        if (toggleGroup.getSelectedToggle() != null
                && ((ToggleButton) toggleGroup.getSelectedToggle())
                .isDisabled()) {
            clearSelection();
        }
    }

    private void updateButtonAvailability(
            ToggleButton button,
            AnimalType type,
            int fruits) {

        button.setDisable(fruits < type.getCost());
    }
}
