// 213394364 Niv Ben David Ohayone

package levels;

import core.Sprite;
import core.Velocity;
import gameObjects.Block;

import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int
     */
    int numberOfBalls();

    /**
     * return the initial velocity of each ball
     *
     * @return the list with the initial velocity of each bal
     */
    List<Velocity> initialBallVelocities();

    /**
     * return the paddle speed.
     *
     * @return the speed of the paddle in the level
     */
    int paddleSpeed();

    /**
     * return the paddle width.
     *
     * @return the width of the paddle in the level
     */
    int paddleWidth();

    /**
     * The level name will be displayed at the top of the screen.
     *
     * @return the string of the level name
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level
     *
     * @return the background
     */
    Sprite getBackground();

    /**
     * Return the Blocks that make up this level, each block contains,
     * its size, color and location.
     *
     * @return the list of blocks
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered
     * to be "cleared".
     *
     * @return the number of blocks
     */
    int numberOfBlocksToRemove();
}