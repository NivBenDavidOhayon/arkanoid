import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import game.GameEnvironment;
import gameObjects.Ball;
import gameObjects.Block;
import geometry.Point;
import geometry.Rectangle;

public class TestPart1 {
    @SuppressWarnings("checkstyle:WhitespaceAfter")
    public static void main(String[] args) {
        GUI gui = new GUI("Test Part 1 - ass3", 300, 300);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        //create balls
        //check movement on X
        Ball ball = new Ball(180, 60, 5,
                java.awt.Color.BLACK);
        ball.setVelocity(-5, 0);
        //check movement on Y
        Ball ball2 = new Ball(60, 180, 5,
                Color.red);
        ball2.setVelocity(0, 5);
        //check movement diagonal
        Ball ball3 = new Ball(150, 150, 5,
                Color.MAGENTA);
        ball3.setVelocity(5, 5);
        //check free movement
        Ball ball4 = new Ball(40, 160, 5,
                Color.blue);
        ball4.setVelocity(7,5);
        GameEnvironment gameEnvironment = new GameEnvironment();

        //for check with borders and one block
        Block[] blocks = new Block[7];

        //for check with borders and 3 blocks (check meeting point in conner
        // and in the middle of the screen)
//        Block[] blocks = new Block[7];
        //left border
        blocks[0] = new Block(new Rectangle(new Point(0, 0), 10, 200,
                Color.gray));
        //top border
        blocks[1] = new Block(new Rectangle(new Point(0, 0), 200, 10,
                Color.gray));
        //right border
        blocks[2] = new Block(new Rectangle(new Point(200, 0), 10,
                200,Color.gray));
        //down border
        blocks[3] = new Block(new Rectangle(new Point(0, 200), 200,
                10,Color.gray));
        //seen on screen
        blocks[4] = new Block(new Rectangle(new Point(50, 50), 50, 50,Color.gray));
        //add for check with 3 blocks
        blocks[5] = new Block(new Rectangle(new Point(50, 100), 50, 50,Color.gray));
        blocks[6] = new Block(new Rectangle(new Point(100, 50), 50, 50,Color.gray));
        //add to game environment
        for (int i = 0; i < blocks.length; i++) {
            gameEnvironment.addCollidable(blocks[i]);
        }
        //set every ball the obstetricals
        ball.setGameEnvironment(gameEnvironment);
        ball2.setGameEnvironment(gameEnvironment);
        ball3.setGameEnvironment(gameEnvironment);
        ball4.setGameEnvironment(gameEnvironment);

        //draw animation
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            //draw blocks
            for (int i = 0; i < blocks.length; i++) {
                blocks[i].drawOn(d);
            }
            //draw balls
            ball.moveOneStep();
            ball.drawOn(d);
            ball2.moveOneStep();
            ball2.drawOn(d);
            ball3.moveOneStep();
            ball3.drawOn(d);
            ball4.moveOneStep();
            ball4.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}

