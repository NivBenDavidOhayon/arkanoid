// 213394364 Niv Ben David Ohayone
package game;
import core.Collidable;
import core.CollisionInfo;
import geometry.Line;
import geometry.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameEnvironment class stores a collection of Collidable objects and
 * provides a method to find the closest collision between a given Line object
 * and the stored Collidable objects.
 */
public class GameEnvironment {
    private List<Collidable> rectangleList;

    /**
     * the method create a new Game environment list.
     */
    public GameEnvironment() {
        rectangleList = new ArrayList<Collidable>();
    }

    /**
     * Add collidable add the given collidable to the environment.
     *
     * @param c the collidalee that will add
     */
    public void addCollidable(Collidable c) {
        this.rectangleList.add(c);
    }

    /**
     *  the method getCollidableList return the rectangleList from the field.
     *
     * @return the list
     */
    public List<Collidable> getCollidableList() {
        return this.rectangleList;
    }
    /**
     * Gets closest collision method detects collisions between a Line object
     * and a collection of Collidable objects by finding the closest
     * intersection point and returning the corresponding CollisionInfo.
     *
     * @param trajectory the line that the method do the test on
     * @return the closest collision of the Collidable objects
     */
//gpt
    public CollisionInfo getClosestCollision(Line trajectory) {
        // Initialize a list to store collision information
        List<CollisionInfo> collisions = new ArrayList<>();

        // Iterate over each collidable in the collection
        for (Collidable collidable : rectangleList) {
            /* Find the closest intersection point between the trajectory
             and the collidable
             */
            Point intersection = trajectory.closestIntersectionToStartOfLine(
                    collidable.getCollisionRectangle());

            // if there is no intersection point, continue to the next element
            if (intersection == null) {
                continue;
            }

            // Add the collision information to the list
            CollisionInfo collision = new CollisionInfo(intersection,
                    collidable);
            collisions.add(collision);
        }

        // If there are no collisions, return null
        if (collisions.isEmpty()) {
            return null;
        }

        /* Find the collision with the closest intersection point
         to the start of the trajectory
         */
        CollisionInfo closestCollision = collisions.get(0);
        double closestDistance = trajectory.start().distance(
                closestCollision.collisionPoint());
        for (CollisionInfo collision : collisions) {
            double distance = trajectory.start().distance(
                    collision.collisionPoint());
            if (distance < closestDistance) {
                closestCollision = collision;
                closestDistance = distance;
            }
        }

        // Return the collision with the closest intersection point
        return closestCollision;
    }

    public void removeCollidable(Collidable c){
        this.rectangleList.remove(c);
    }
}
