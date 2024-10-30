// 213394364 Niv Ben David Ohayone

package screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import animation.Animation;

/**
 *
 * The "KeyPressStoppableAnimation" class represents an animation that can
 * be stopped by pressing a specified key on the keyboard. It wraps another
 * animation and checks if the key is pressed to determine whether to stop
 * the animation or not.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;


    /**
     * Constructor.
     *
     * @param sensor    - the keyboard of the user
     * @param key       - the name of the button which will stop the animation
     * @param animation - the animation that will be presented
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        animation.doOneFrame(d);
        //if the key is pressed
        if (this.sensor.isPressed(key)) {
            if (isAlreadyPressed) {
                return;
            }
            this.stop = true;
        } else {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
