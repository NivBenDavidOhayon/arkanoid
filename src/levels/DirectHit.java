// 213394364 Niv Ben David Ohayone

package levels;

import core.Sprite;
import core.Velocity;
import gameObjects.Block;
import geometry.Point;
import geometry.Rectangle;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * The DirectHit class implements the LevelInformation interface and represents
 * a specific level called "Direct Hit". It defines the properties of the level,
 * such as the number of balls, initial ball velocities, paddle speed, paddle
 * width, background sprite, blocks configuration, and the number of
 * blocks to remove.
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        // Create a new linked list to store velocities
        List<Velocity> velList = new LinkedList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            //add any velocity to the list
            velList.add(new Velocity(0, 4));
        }
        return velList;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 90;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point
                (0, 0), 900, 900, Color.BLACK));
    }

    @Override
    public List<Block> blocks() {
        // Create a new linked list to store blocks
        List<Block> blockList = new LinkedList<>();
        // Add a new block to the list
        blockList.add(new Block(new Rectangle(
                new Point(370, 120), 30, 30, Color.RED)));
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
