// 213394364 Niv Ben David Ohayone
package gameObjects;

import core.Collidable;
import core.CollisionInfo;
import core.Sprite;
import core.Velocity;
import game.GameLevel;
import game.GameEnvironment;
import geometry.Line;
import geometry.Point;
import biuoop.DrawSurface;

import java.awt.*;

/**
 * Ball class represents a ball object. The ball has properties such as a
 * center point, radius, color, velocity, and the start and end points of
 * the frame it is in. The class has two constructors - one that takes in a
 * Point object, a radius, and a color to initialize the ball, and another
 * that takes in separate x and y coordinates instead of a Point.
 * Both constructors set the velocity of the ball to 0, as well as the
 * start and end points of the frame.
 * The class has four methods to set the start and end points of the frame,
 * and four methods to get the ball's x coordinate, y coordinate,
 * size (radius), and color. The class also has a method called drawOn that
 * takes in a DrawSurface object and is responsible for drawing the ball on it
 */
public class Ball implements Sprite {
    /**
     * The constant MAX_HIT_TRIES.
     */
// The maximum times we  change the ball's velocity (above the ball is stuck).
    public static final int MAX_HIT_TRIES = 4;
    /**
     * The Restart.
     */
    static final int RESTART = 0;

    // fields
    private GameEnvironment environment;

    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;


    /**
     * The method (constructor) receives center point, radious and color and
     * inserts their values into center, r,color fields accordingly.
     * In addition, the method restart the velocity,frameStartX,
     * frameStartY,frameEndX, frameEndY fields with 0
     *
     * @param center Point type parameter - the center of the ball
     * @param r      Int type parameter - the radious of the ball
     * @param color  color parameter - the color of the ball
     */
// constructor
    public Ball(Point center, int r, java.awt.Color color) {
        //restart the fields with the arguments values
        this.center = center;
        this.r = r;
        this.color = color;
        //restart the other fields with 0
        this.velocity = new Velocity(RESTART, RESTART);

    }


    /**
     * The method (constructor) receives x and y values and turnes
     * them to point object, radius and color and
     * inserts their values into center, r,color fields accordingly.
     * In addition, the method restart the velocity,frameStartX,
     * frameStartY,frameEndX, frameEndY fields with 0
     *
     * @param x     Double type parameter - the X value of the center of the ball
     * @param y     Double type parameterthe Y value of the center of the ball
     * @param r     Int type parameter - the radious of the ball
     * @param color color parameter - the color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        //create a center point with the x,y arguments value
        this.center = new Point(x, y);
        //restart the fields with the arguments values
        this.r = r;
        this.color = color;
        //restart the other fields with 0
        this.velocity = new Velocity(RESTART, RESTART);
    }


    /**
     * the method getX return the x value of the center from the center field.
     *
     * @return the x value of the center of the ball in the field.
     */
    // accessors
    public int getX() {
        //return the x value of the center field
        return (int) this.center.getX();
    }

    /**
     * the method getY return the y value of the center from the center field.
     *
     * @return the y value of the center of the ball in the field.
     */
    public int getY() {
        //return the y value of the center field
        return (int) this.center.getY();
    }

    /**
     * the method getSize return the radious value from the r field.
     *
     * @return the r value of the ball in the field.
     */
    public int getSize() {
        //return the r field value
        return this.r;
    }

    /**
     * the method getColor return the color value from the color field.
     *
     * @return the color value of the ball in the field.
     */
    public java.awt.Color getColor() {
        //return the color field value
        return this.color;
    }

    /**
     * This method is responsible for drawing the ball on a given DrawSurface
     * It first sets the color of the surface to the color of the ball,
     * then fills a circle on the surface using the ball's center and size.
     * The center is represented by a Point object, which has an x and y.
     * The size represents the radius of the circle. Overall, this method is
     * used to visually display the ball in a graphics window.
     *
     * @param surface the DrawSurface parameter - to print on it
     */
    @Override
    public void drawOn(DrawSurface surface) {
        /*Draws and fills a circle on the received DrawSurface according
           to the color and center fields*/
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(),
                (int) this.center.getY(), this.getSize());
        surface.setColor(this.getColor());
        surface.fillCircle((int) this.center.getX(),
                (int) this.center.getY(), this.getSize());
    }


    /**
     * The method (constructor) a velocity value and inserts it into
     * the velocity field.
     *
     * @param v - the velocity value of the ball
     */
    public void setVelocity(Velocity v) {
        //restart the velocity value with the received argument
        this.velocity = v;
    }

    /**
     * The method (constructor) a dx and dy values, craete a new velocity
     * with those arguments and inserts it into the velocity field.
     *
     * @param dx the dx value of the velocity
     * @param dy the dy value of the velocity
     */
    public void setVelocity(double dx, double dy) {
        //create a new point with the received arguments
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * the method getVelocity return the velocity value from the velocity field.
     *
     * @return the velocity value of the ball in the field.
     */
    public Velocity getVelocity() {
        //return the velocity field value
        return this.velocity;
    }

    /**
     * The moveOneStep() method moves a ball object one step forward based on
     * its current velocity, and checks for collisions with other objects in
     * its environment. If a collision is detected, it updates the ball's
     * velocity based on the collision using the hit() method of the colliding
     * object. If the ball is stuck (4 times that it hit without move), it
     * turns the ball back (opposite velocity). The method also stops the
     * ball if its velocity is zero.
     */
    public void moveOneStep() {

        /*
         * Checks if the velocity is 0,
         * then the ball doesn't move.
         */
        if (this.getVelocity().getDx() == RESTART
                && this.getVelocity().getDy() == RESTART) {
            return;
        }

        // opposite is the opposite velocity of this ball's velocity.
        Velocity opposite = new Velocity(-this.velocity.getDx(),
                -this.velocity.getDy());

        /* shortTrajectory is a line from the center of the ball to it's next
         position (by it's velocity).
         */
        Line shortTrajectory = new Line(this.getCenter(),
                this.getVelocity().applyToPoint(this.getCenter()));

        /* shortClosestCollision gets the closest collision point and
         collidable object.
         */
        CollisionInfo shortClosestCollision =
                this.environment.getClosestCollision(shortTrajectory);

        /*
         * A loop that check every time if there is a collision,
         * then it change it's velocity by hit function,
         * and if the ball is stuck (4 times that it hit without move),
         * then it turns the ball back (opposite velocity).
         */
        int count = 0;
        while (shortClosestCollision != null) {

            /*
             * if the ball is stuck (4 times that it hit without move),
             * then it turns the ball back (opposite velocity).
             */
            if (count >= MAX_HIT_TRIES) {
                this.setVelocity(opposite);
                break;
            }

            /* shortCollisionBlock is the collision object that intersect
             with trajectory.
             */
            Collidable shortCollisionBlock
                    = shortClosestCollision.collisionObject();

            /*
             * if the ball is trying to move inside the collision object,
             *  then it change it velocity with hit function.
             */
            this.setVelocity(shortCollisionBlock.hit(this, shortClosestCollision
                    .collisionPoint(), this.getVelocity()));

            /* checks the trajectory and the collision again and counts this
             change.
             */
            shortTrajectory = new Line(this.getCenter(), this.getVelocity().
                    applyToPoint(this.getCenter()));
            shortClosestCollision = this.environment.getClosestCollision(
                    shortTrajectory);
            count++;
        }

        // Moves the ball finaly to the next step.
        this.setCenter(this.getVelocity().applyToPoint(this.getCenter()));
    }

    /**
     * the method return the current center of the ball.
     *
     * @return the center - the current center of the ball.
     */
    public Point getCenter() {
        //return the velocity field value
        return this.center;
    }

    /**
     * the method create a new ball with the center, radius, color and
     * environment parameters.
     *
     * @param center      the center of the ball
     * @param r           the radius of the ball
     * @param color       the color of the ball
     * @param environment the environment element
     */
    public Ball(Point center, int r, java.awt.Color color,
                GameEnvironment environment) {
        //create a center point with the x,y arguments value
        this.center = center;
        //restart the fields with the arguments values
        this.r = r;
        this.color = color;
        this.environment = environment;
    }

    /**
     * time passed method call the "move one step" method and "play" this step.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * the method Add to game add the element parameter to the game by
     * addSprite method.
     *
     * @param g the game element.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * the method sets the center of the ball to the center it got.
     *
     * @param center the new center of the ball.
     */
    public void setCenter(Point center) {
        //restart the frameStartY value with the received argument
        this.center = center;
    }

    /**
     * Sets game environment with the value that it got.
     *
     * @param g the game environment element.
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.environment = g;
    }

    /**
     * this method removes the ball to the game.
     *
     * @param g - the game that we remove the ball from
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

}





