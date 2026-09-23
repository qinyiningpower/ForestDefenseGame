package com.forestdefense.ui.render;

import com.forestdefense.entity.Animal;
import com.forestdefense.entity.Beast;
import com.forestdefense.entity.Bullet;
import com.forestdefense.entity.Fruit;
import com.forestdefense.entity.Trap;
import com.forestdefense.logic.GameWorld;

import javafx.scene.canvas.GraphicsContext;

/**
 * Draws all active game objects in a consistent rendering order.
 */
public final class Renderer {

    /**
     * Draws the complete game world.
     *
     * Background elements are drawn first, followed by moving
     * entities such as animals, beasts and bullets.
     */
    public void render(GraphicsContext gc, GameWorld world) {
        if (world == null) {
            return;
        }

        drawEgg(gc, world);
        drawTraps(gc, world);
        drawFruits(gc, world);
        drawAnimals(gc, world);
        drawBeasts(gc, world);
        drawBullets(gc, world);
    }

    private void drawEgg(GraphicsContext gc, GameWorld world) {
        if (world.getEgg() != null
                && world.getEgg().isAlive()) {
            world.getEgg().draw(gc);
        }
    }

    private void drawTraps(GraphicsContext gc, GameWorld world) {
        for (Trap trap : world.getTraps()) {
            if (trap.isActive()) {
                trap.draw(gc);
            }
        }
    }

    private void drawFruits(GraphicsContext gc, GameWorld world) {
        for (Fruit fruit : world.getFruits()) {
            if (fruit.isActive()) {
                fruit.draw(gc);
            }
        }
    }

    private void drawAnimals(GraphicsContext gc, GameWorld world) {
        for (Animal animal : world.getAnimals()) {
            if (animal.isAlive()) {
                animal.draw(gc);
            }
        }
    }

    private void drawBeasts(GraphicsContext gc, GameWorld world) {
        for (Beast beast : world.getBeasts()) {
            if (beast.isAlive()) {
                beast.draw(gc);
            }
        }
    }

    private void drawBullets(GraphicsContext gc, GameWorld world) {
        for (Bullet bullet : world.getBullets()) {
            if (bullet.isActive()) {
                bullet.draw(gc);
            }
        }
    }
}
