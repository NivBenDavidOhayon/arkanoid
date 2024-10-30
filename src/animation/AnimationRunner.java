// 213394364 Niv Ben David Ohayone

package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 *
 * The AnimationRunner class manages the execution of animations on a
 * graphical user interface (GUI). It provides a method to run an animation
 * by performing frames at a specified rate and updating the GUI display
 * accordingly.
 */
public class AnimationRunner {
    /**
     * The Width.
     */
    static final int WIDTH = 800;
    /**
     * The Height.
     */
    static final int HEIGHT = 600;
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor.
     */
    public AnimationRunner() {
        this.gui = new GUI("Arkanoid", WIDTH, HEIGHT);
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * Gets gui.
     *
     * @return the gui
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * runs the animation on the screen.
     *
     * @param animation - the animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            // Get the drawing surface from the GUI
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);// Perform one frame of the animation

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            // If there is time left to sleep
            if (milliSecondLeftToSleep > 0) {
                // Sleep for the remaining time
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}