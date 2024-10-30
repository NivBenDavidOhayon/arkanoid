// 213394364 Niv Ben David Ohayone

package screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import animation.Animation;

/**
 *
 * The "PauseScreen" class implements the "Animation" interface and represents
 * a screen that is displayed when the game is paused. It provides methods
 * for drawing the pause message on a DrawSurface and determining whether
 * the animation should stop. The pause screen is controlled by the keyboard
 * sensor and can be continued by pressing the space key.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor. Instantiates a new Pause screen.
     *
     * @param k the k
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        // write a message on the screen
        d.drawText(10, d.getHeight() / 2,
                "paused -- press space to continue", 32);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}