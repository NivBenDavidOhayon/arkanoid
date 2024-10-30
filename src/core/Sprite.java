// 213394364 Niv Ben David Ohayone
package core;
import biuoop.DrawSurface;

/**
 * The "Sprite" interface defines the methods that any object that can be
 * drawn to a "DrawSurface" and can change over time should implement. The
 * "drawOn" method is responsible for drawing the sprite on a "DrawSurface",
 * while the "timePassed" method is responsible for updating the sprite's
 * state as time passes.
 */
public interface Sprite {
    /**
     * Draw on method draw the sprite to the screen.
     *
     * @param d the DrawSurface element to draw on it.
     */
    void drawOn(DrawSurface d);

    /**
     * time passed method notify the sprite that time has passed.
     */
    void timePassed();
}