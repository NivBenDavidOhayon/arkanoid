// 213394364 Niv Ben David Ohayone
package gameObjects;

import core.Collidable;
import core.Sprite;
import core.Velocity;
import game.GameLevel;
import listeners.HitListener;
import listeners.HitNotifier;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Block implements the Collidable and Sprite interfaces, which
 * define methods for objects that can collide with a Ball and be drawn on
 * a DrawSurface, respectively.The Block class represents a rectangular block
 * in a 2D environment. It has a Rectangle object that defines the block's
 * dimensions and position, and implements the Collidable interface to
 * handle collisions with Ball objects. When a collision occurs between a
 * Ball and a Block, the Block calculates the point of collision and reflects
 * the Ball's velocity accordingly, as described in the hit method.
 * The Block class also implements the Sprite interface to enable it to be
 * drawn on a DrawSurface. It provides a drawOn method that takes a
 * DrawSurface object and uses its drawRectangle method to draw the block
 * on the surface.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    /**
     * The Epsilon.
     */
    static final double EPSILON = 0.00001;
    /**
     * The Zero velocity.
     */
    static final int ZERO_VELOCITY = 0;

    private List<HitListener> hitListeners;
    private Rectangle rectangle;

    /**
     * the method return the rectangle from the field.
     *
     * @return rectangle - the rectangle object from the field.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        //return the rectangle object
        return this.rectangle;
    }
    /**
     * the method create a new hitListeners list.
     */


    /**
     * checks whether the collision occurred on the top or
     * bottom side of the rectangle . If the collision occurred on the top
     * or bottom side, the code reflects the ball's velocity vertically by
     * negating its DX.If the collision occurred on the left or right side,
     * the code reflects the ball's velocity horizontally by negating its DX.
     * Finally, the new velocity of the ball after the collision is returned.
     *
     * @param collisionPoint  the point where the ball collides with
     *                        the rectangle
     * @param currentVelocity the velocity of the ball before the collision.
     * @return the new velocity after all calculation
     */



    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        //calculate and save all the points of the rectangle.
        Point topLeft = rectangle.getUpperLeft();
        Point topRight = new Point(rectangle.getUpperLeft().getX()
                + rectangle.getWidth(),
                rectangle.getUpperLeft().getY());
        Point bottomRight = new Point(rectangle.getUpperLeft().getX()
                + rectangle.getWidth(),
                rectangle.getUpperLeft().getY() + rectangle.getHeight());
        Point bottomLeft = new Point(rectangle.getUpperLeft().getX(),
                rectangle.getUpperLeft().getY() + rectangle.getHeight());

        /* check if touch bottom or top and check if the velocity is positive
           or negative respectively*/
        if ((topLeft.getX() - EPSILON <= collisionPoint.getX()
                && topRight.getX() + EPSILON >= collisionPoint.getX()
                && (treshold(topLeft.getY(), collisionPoint.getY())
                && currentVelocity.getDy() > ZERO_VELOCITY))) {
            //change the DY of the velocity to the opposite
            currentVelocity = new Velocity(currentVelocity.getDx(),
                    -currentVelocity.getDy());
        } else if (((treshold(bottomLeft.getY(), collisionPoint.getY()))
                && currentVelocity.getDy() < ZERO_VELOCITY)) {
            currentVelocity = new Velocity(currentVelocity.getDx(),
                    -currentVelocity.getDy());
        }

        /* check if touch left or right and check if the velocity is positive
        or negative respectively*/
        if ((topRight.getY() - EPSILON <= collisionPoint.getY()
                && bottomRight.getY() + EPSILON >= collisionPoint.getY()
                && (treshold(topRight.getX(), collisionPoint.getX())
                && currentVelocity.getDx() < ZERO_VELOCITY))) {
            //change the DX of the velocity to the opposite
            currentVelocity = new Velocity(-currentVelocity.getDx(),
                    currentVelocity.getDy());
        } else if (((treshold(bottomLeft.getX(), collisionPoint.getX()))
                && currentVelocity.getDx() > ZERO_VELOCITY)) {
            currentVelocity = new Velocity(-currentVelocity.getDx(),
                    currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }


    /**
     * the method update the rectangle with the value that it got.
     *
     * @param r the new rectangle to update.
     */
    public Block(Rectangle r) {
        this.hitListeners = new ArrayList<HitListener>();
        this.rectangle = r;
    }

    /**
     * drawOn method is responsible for rendering the block on the canvas
     * with its specified color and shape.
     *
     * @param surface the surface object
     */
    @Override
    public void drawOn(DrawSurface surface) {
        /*Draws and fills a Block on the received DrawSurface according
           to the color and center fields*/
        surface.setColor(this.rectangle.getColor());
        this.rectangle.fillRectangle(surface);
    }

    /**
     * notify the blocks that the time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Add to game is a methos that add an game variable to the
     * sprite and collidable lists.
     *
     * @param g the game value
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * The treshold method first defines a small constant value called epsilon
     * that is used as the threshold for comparing the two double variables.
     * In this case,epsilon is set to 0.00001.
     * The method then calculates the absolute difference between the two
     * variables using the Math.abs method and compares it to epsilon.
     * If the absolute difference is less than epsilon, the method returns
     * true, indicating that the variables are considered equal within the
     * given threshold. Otherwise, it returns false.
     *
     * @param var1 The first number we want to compare
     * @param var2 The other number we want to compare
     * @return boolean parameter true, indicating that the variables are
     * considered equal within the given threshold. Otherwise, it returns false
     */
    public boolean treshold(double var1, double var2) {
        if (var1 > var2) {
            return Math.abs(var1 - var2) < EPSILON;
        }
        return Math.abs(var2 - var1) < EPSILON;
    }


    /**
     * removes the block to the game.
     *
     * @param game - the game that we remove the block from
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }


    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * Make a copy of the hitListeners before iterating over them and notify
     * all listeners about a hit event
     *
     * @param hitter the ball that hit
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }


}
