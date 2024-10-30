// 213394364 Niv Ben David Ohayone

package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.SpriteCollection;
import levels.LevelInformation;

import java.awt.*;

/**
 *
 * The CountdownAnimation class implements the Animation interface and
 * represents a countdown animation. It performs a countdown from a given
 * number of seconds and displays the countdown on the screen until it reaches
 * zero, after which it stops and proceeds to the next level.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int startCount;

    private SpriteCollection gameScreen;
    private boolean stop;
    private LevelInformation level;
    private Sleeper sleeper = new Sleeper();
    private long initiationTime;

    /**
     * Constructor.
     *
     * @param numOfSeconds - the number of seconds until the animation will be over
     * @param countFrom    - the initial number of which the countdown will start from
     * @param gameScreen   - the sprites in the level//
     * @param level - the level which will be played after the countdown is over
     * @param level        the level
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen, LevelInformation level) {
        this.numOfSeconds = numOfSeconds * 1000;
        this.countFrom = countFrom;
        this.startCount = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.level = level;
        this.initiationTime = System.currentTimeMillis();

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.YELLOW);
        d.drawText(375, 350, Integer.toString(this.countFrom), 100);
        // Check if enough time has passed to decrease the countFrom value
        if (System.currentTimeMillis() - this.initiationTime >
                this.numOfSeconds / this.startCount) {
            // Update the initiation time
            this.initiationTime = System.currentTimeMillis();
            this.countFrom--;
        }
        // Check if countFrom has reached 0
        if (this.countFrom == 0) {
            this.stop = true;
        }

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}