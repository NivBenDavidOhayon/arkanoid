// 213394364 Niv Ben David Ohayone

package levels;

import core.Sprite;
import core.Velocity;
import gameObjects.Block;
import geometry.Point;
import geometry.Rectangle;

import java.awt.*;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

/**
 * The "Green3" class represents a specific game level with multiple rows of
 * colored blocks. It provides information such as the number of balls, their
 * velocities, paddle properties, level name, background sprite, and block
 * configuration.
 */
public class Green3 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        //create a list of velocities
        List<Velocity> velList = new LinkedList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            //add the velocity to the list for each ball
            velList.add(Velocity.fromAngleAndSpeed(Math.pow(-1,i)*45,6));
        }
        return velList;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 110;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        //create green background
        return new Block(new Rectangle(new Point(0,0),900,
                900,new Color(0,100,0)));
    }


    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        // Generate blocks for the first row
        for (int i = 0; i < 6; i++) {
            // Create a block and add it to the list
            Block block = new Block(new Rectangle(new Point
                    (720 - i * 50, 230), 50, 20, Color.WHITE));
            blocks.add(block);
        }
        // Generate blocks for the second row
        for (int i = 0; i < 7; i++) {
            // Create a block and add it to the list
            Block block = new Block(new Rectangle(new Point
                    (720 - i * 50, 210), 50, 20, Color.BLUE));
            blocks.add(block);
        }
        // Generate blocks for the third row
        for (int i = 0; i < 8; i++) {
            // Create a block and add it to the list
            Block block = new Block(new Rectangle(new Point
                    (720 - i * 50, 190), 50, 20, Color.YELLOW));
            blocks.add(block);
        }
        // Generate blocks for the fourth row
        for (int i = 0; i < 9; i++) {
            // Create a block and add it to the list
            Block block = new Block(new Rectangle(new Point
                    (720 - i * 50, 170), 50, 20, Color.RED));
            blocks.add(block);
        }
        // Generate blocks for the fifth row
        for (int i = 0; i < 10; i++) {
            // Create a block and add it to the list
            Block block = new Block(new Rectangle(new Point
                    (720 - i * 50, 150), 50, 20, Color.GRAY));
            blocks.add(block);
        }
        return blocks;
    }


    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}