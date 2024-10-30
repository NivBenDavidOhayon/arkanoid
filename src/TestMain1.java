import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import core.Collidable;
import game.GameEnvironment;
import gameObjects.Ball;
import gameObjects.Block;
import geometry.Point;
import geometry.Rectangle;
public class TestMain1 {
    public void createBorders(GameEnvironment environment){
        Collidable rightBorder = new Block(new Rectangle(new Point(770, 0), 30, 600, Color.GRAY));
        environment.addCollidable(rightBorder);
        Collidable leftBorder = new Block(new Rectangle(new Point(0, 0), 30, 600, Color.GRAY));
        environment.addCollidable(leftBorder);
        Collidable UpperBorder = new Block(new Rectangle(new Point(0, 0), 800, 30, Color.GRAY));
        environment.addCollidable(UpperBorder);
        Collidable downBorder = new Block(new Rectangle(new Point(0, 570), 800, 30, Color.GRAY));
        environment.addCollidable(downBorder);
    }
    public void createBlocks(GameEnvironment environment){
        Collidable blockOne = new Block(new Rectangle(new Point(350, 200), 70, 20, Color.BLACK));
        environment.addCollidable(blockOne);
        Collidable blockTwo = new Block(new Rectangle(new Point(200, 200), 70, 20, Color.BLACK));
        environment.addCollidable(blockTwo);
        Collidable blockThree = new Block(new Rectangle(new Point(500, 400), 70, 20, Color.BLACK));
        environment.addCollidable(blockThree);
        Collidable blockFour = new Block(new Rectangle(new Point(400, 300), 70, 20, Color.BLACK));
        environment.addCollidable(blockFour);
    }
    private void drawBLocks(GameEnvironment environment, DrawSurface d){
        for (Collidable c: environment.getCollidableList()){
            c.getCollisionRectangle().fillRectangle(d);
        }
    }

    public static void drawAnimation(){
        TestMain1 t = new TestMain1();
        GameEnvironment environment = new GameEnvironment();
        Sleeper sleeper = new Sleeper();
        GUI gui = new GUI("animation", 800, 600);
        t.createBorders(environment);
        t.createBlocks(environment);
        Ball ballOne = new Ball(new Point(400, 300), 30, Color.GREEN, environment);
        ballOne.setVelocity(5, 7);
        Ball ballTwo = new Ball(new Point(150, 150), 30, Color.GREEN, environment);
        ballTwo.setVelocity(9, 13);
        while (true){
            Point beforeOne = ballOne.getCenter();
            Point beforeTwo = ballTwo.getCenter();
            DrawSurface d = gui.getDrawSurface();
            t.drawBLocks(environment, d);
            ballOne.moveOneStep();
            ballOne.drawOn(d);
            ballTwo.moveOneStep();
            ballTwo.drawOn(d);
            if (!ballOne.getCenter().equals(beforeOne) && !ballTwo.getCenter().equals(beforeTwo)){
                sleeper.sleepFor(50);
            }
            gui.show(d);
        }
    }
    public static void main(String[] args){
        drawAnimation();

    }
}
