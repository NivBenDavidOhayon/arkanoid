// 213394364 Niv Ben David Ohayone
package game;

import core.Velocity;
import levels.LevelIndicator;
import levels.LevelInformation;
import animation.*;
import removers.BallRemover;
import removers.BlockRemover;
import score.Counter;
import score.ScoreIndicator;
import score.ScoreTrackingListener;
import biuoop.KeyboardSensor;
import core.Collidable;
import core.Sprite;
import gameObjects.Ball;
import gameObjects.Block;
import gameObjects.Paddle;
import geometry.Point;
import geometry.Rectangle;
import biuoop.GUI;

import java.awt.Color;
import java.util.List;

import biuoop.DrawSurface;
import screens.KeyPressStoppableAnimation;
import screens.PauseScreen;


/**
 * Game level class implements a game that involves creating blocks, a ball and a
 * paddle. The code uses the GUI and Sleeper to control game timing and
 * updates sprites accordingly.
 */
public class GameLevel implements Animation {
    /**
     * The Width.
     */
    static final int WIDTH = 800;
    /**
     * The Height.
     */
    static final int HEIGHT = 600;
    /**
     * The Screen.
     */


    private SpriteCollection sprites;
    private biuoop.KeyboardSensor keyboard;
    private GameEnvironment environment;

    private Counter counterBlocks;
    private Counter counterBalls;
    private Counter counterScore;
    private AnimationRunner runner;
    private boolean running;
    private GUI gui;
    private LevelInformation levelInformation;


    /**
     * the method instantiates a new Game by create new SpriteCollection and
     * GameEnvironment elements.
     *
     * @param levelInformation the level information
     * @param score            the score
     * @param animationRunner  the animation runner
     * @param keyboard         the keyboard
     * @param gui              the gui
     */
    public GameLevel(LevelInformation levelInformation,Counter score,
                     AnimationRunner animationRunner, KeyboardSensor keyboard, GUI gui) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.counterBlocks = new Counter(0);
        this.counterBalls = new Counter(0);
        this.counterScore = score;
        this.running = true;
        this.runner = animationRunner;
        this.gui = gui;
        this.keyboard = keyboard;
        this.levelInformation = levelInformation;
    }

    /**
     * the method Get sprite collection return the sprites field.
     *
     * @return the sprite collection from the field.
     */
    public SpriteCollection getSpriteCollection() {
        return this.sprites;
    }

    /**
     * the method Get game environment return the environment field.
     *
     * @return the game environment from the field.
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        //if there are no blocks - end the game
        if (counterBlocks.getValue() == 0) {
            this.counterScore.increase(100);
            this.running = false;
        }
        // if there are no balls - end the game
        if (counterBalls.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation
                    (this.keyboard,this.keyboard.SPACE_KEY,
                            new PauseScreen(this.keyboard)));
        }

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * the method Add collidable add a collidable object to environment field.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * the method Add sprite add a sprite object to the sprites field.
     *
     * @param s the sprite object
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove collidable from the list.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite from the list.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }


    /**
     * the method initialize a new game: create the  Background,Blocks and Balls
     * (and Paddle) and add them to the game.
     */
    public void initialize() {
        //create the game elements
        createBackground();
        createBlocks();
        createBalls();
        createFrame();
        //create the score indicator
        ScoreIndicator scoreBoard = new ScoreIndicator(this.counterScore);
        scoreBoard.addToGame(this);
        LevelIndicator levelIndicator = new LevelIndicator(
                this.levelInformation.levelName());
        levelIndicator.addToGame(this);
    }

    /**
     * The run method initializes the game and starts the main game animation.
     */
    public void run() {
        createPaddle();
        this.runner.run(new CountdownAnimation(2, 3,
                sprites,this.levelInformation));
        this.running = true;
        // use runner to run the current animation which is one turn of the game
        this.runner.run(this);
    }


    /**
     * Create blocks and add them to the game
     */
    public void createBlocks(){
        // Create a BlockRemover instance
        BlockRemover blockRemover=new BlockRemover(this,counterBlocks);
        // Increase the block counter
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.counterScore);
        // Get all the blocks for the level
        this.counterBlocks.increase(this.levelInformation.numberOfBlocksToRemove());
        List<Block> allBlocks = this.levelInformation.blocks();
        for (Block block : allBlocks){
            // Add the BlockRemover as a hit listener to the block
            block.addHitListener(blockRemover);
            // Add the ScoreTrackingListener as a hit listener to the block
            block.addHitListener(scoreTrackingListener);
            // Add the block to the game
            block.addToGame(this);
        }
    }

    /**
     * Create balls and add them to the game
     */
    public void createBalls(){
        List<Velocity> ballsVelocities = this.levelInformation.initialBallVelocities();
        this.counterBalls.increase(this.levelInformation.numberOfBalls());
         for (int i=0; i<this.levelInformation.numberOfBalls(); i++){
            // Create a new ball
            Ball b = new Ball(new Point(385,520),5,
                    Color.WHITE,this.environment);
             // Set the velocity of the ball
            b.setVelocity(ballsVelocities.get(i));
            b.addToGame(this);
        }
    }

    /**
     * Create background.
     */
    public void createBackground(){
        this.sprites.addSprite(this.levelInformation.getBackground());
    }

    /**
     * Create the paddle of the game.
     */
    public void createPaddle(){
        Paddle paddle = new Paddle(this.keyboard,Color.YELLOW,
                this.levelInformation.paddleSpeed(),this.levelInformation.paddleWidth());
        paddle.addToGame(this);
    }

    /**
     * Create the frame of the game screen.
     */
    public void createFrame(){
        // creates the four blocks around the frame and add them to game.
        Block rightBorder = new Block(new Rectangle(new Point(770, 0),
                30, HEIGHT, Color.GRAY));
        rightBorder.addToGame(this);
        Block leftBorder = new Block(new Rectangle(new Point(0, 0),
                30, HEIGHT, Color.GRAY));
        leftBorder.addToGame(this);
        Block upperBorder = new Block(new Rectangle(new Point(0, 0),
                WIDTH, 50, Color.GRAY));
        upperBorder.addToGame(this);

        Block downBorder = new Block(new Rectangle(new Point(0, 650),
                WIDTH, 30, Color.GRAY));
        downBorder.addToGame(this);
        BallRemover ballRemover = new BallRemover(this,counterBalls);
        downBorder.addHitListener(ballRemover);
    }

    /**
     * Gets balls counter.
     *
     * @return the amount of balls currently are in this level
     */
    public Counter getBallsCounter() {
        return counterBalls;
    }

    /**
     * Gets blocks counter.
     *
     * @return the amount of blocks currently are in this level
     */
    public Counter getBlocksCounter() {
        return counterBlocks;
    }

}