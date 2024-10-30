// 213394364 Niv Ben David Ohayone
package core;
import geometry.Point;
/**
 * CollisionInfo class has two private fields:
 * "collisionPoint" and "collisionObject". It also has a constructor that
 * takes in a Point and Collidable object to set the values of the
 * fields, and two methods to return these values.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * the CollisionInfo method update the collisionPoint and collisionObject
     * with the values obtained.
     *
     * @param collisionPoint  the collision point that will update
     * @param collisionObject the collision object that will update
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * method that return the collision point.
     *
     * @return the point value from the field
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * method that return the collision object.
     *
     * @return the collision object from the field.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}