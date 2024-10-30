// 213394364 Niv Ben David Ohayone

package levels;

import core.Sprite;
import core.Velocity;
import gameObjects.Block;
import geometry.Point;
import geometry.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 The "WideEasy" class implements the "LevelInformation" interface and
 represents a specific level called "Wide Easy". It provides information about
 the number of balls, their initial velocities, paddle speed and width, level
 name, background, blocks configuration, and the number of blocks to remove.
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        //create a list of the velocities
        List<Velocity> velList = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            // add each velocity to the list
            velList.add(Velocity.fromAngleAndSpeed(i*10-45,5));

        }
        return velList;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0),
                900, 900, Color.WHITE));
    }

    @Override
    public List<Block> blocks() {
        //create the blocks list
        List<Block> blockList = new ArrayList<>();
        Color chosenColor;
        for (int i = 0; i < 15; i++) {
            // Determine the color based on the index value
            if (i < 2) {
                chosenColor = Color.RED;
            } else if (i < 4) {
                chosenColor = Color.orange;
            } else if (i < 6) {
                chosenColor = Color.yellow;
            } else if (i < 9) {
                chosenColor = Color.green;
            } else if (i < 11) {
                chosenColor = Color.blue;
            } else if (i < 13) {
                chosenColor = Color.pink;
            } else {
                chosenColor = Color.CYAN;
            }
            // Create a block with the determined color and position it in the row
            Block block = new Block(new Rectangle(new Point(30 + i * 50, 300),
                    50, 30, chosenColor));
            // Add the block to the list
            blockList.add(block);
        }
        // Return the list of blocks
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

}
