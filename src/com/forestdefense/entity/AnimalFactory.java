package com.forestdefense.entity;

import com.forestdefense.base.AnimalType;

/**
 * Creates defensive animals based on their selected type.
 *
 * Centralizing entity construction keeps input and gameplay
 * controllers independent from concrete animal classes.
 */
public final class AnimalFactory {

    private AnimalFactory() {
        // Utility class
    }

    /**
     * Creates an animal at the selected grid position.
     *
     * @param type selected animal type
     * @param row grid row
     * @param col grid column
     * @return newly created animal
     */
    public static Animal create(
            AnimalType type,
            int row,
            int col) {

        if (type == null) {
            throw new IllegalArgumentException(
                    "Animal type cannot be null."
            );
        }

        switch (type) {
            case SQUIRREL:
                return new Squirrel(row, col);

            case HEDGEHOG:
                return new Hedgehog(row, col);

            case WOODPECKER:
                return new Woodpecker(row, col);

            case FOX:
                return new Fox(row, col);

            case TURTLE:
                return new Turtle(row, col);

            default:
                throw new IllegalArgumentException(
                        "Unsupported animal type: " + type
                );
        }
    }
}
