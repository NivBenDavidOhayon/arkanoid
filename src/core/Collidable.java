// 213394364 Niv Ben David Ohayone
package core;
import gameObjects.Ball;
import geometry.Point;
import geometry.Rectangle;

/**
 * The interface Collidable This is an interface for objects that can be
 * collided with. It specifies two methods: getCollisionRectangle, which
 * returns the rectangle representing the object's collision shape, and hit,
 * which is called when the object is collided with at a given collision
 * point with a given velocity, and returns the new velocity of the object
 * after the collision.
 */
public interface Collidable {
    /**
     *  the method getCollisionRectangle Return the "collision shape"
     *  of the object.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit method notify the object that we collided with it at
     * collisionPoint with a given velocity. The return is the new velocity
     * expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity of the ball.
     * @return the new velocity after the calculation.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}