// 213394364 Niv Ben David Ohayone
package core;

import geometry.Point;

/**
 * The Velocity class represents the velocity of an object in two-dimensional
 * space. It provides functionality for calculating the horizontal and
 * vertical components of velocity based on an angle and speed, and for
 * applying these components to a given point to determine the new position of
 * the object. The class also allows access to the horizontal and vertical
 * components of the velocity through getter methods.
 * Overall, this class is useful in simulating motion of objects in
 * a two-dimensional space.
 */
public class Velocity {

    // fields
    private double dx;
    private double dy;

    /**
     * The method (constructor) get dx and dy values and inserts them into
     * the dx and dy fields.
     *
     * @param dx - the dx value of the velocity
     * @param dy - the dy value of the velocity
     */
    public Velocity(double dx, double dy) {
        //restart the fields with the arguments values
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * The method fromAngleAndSpeed takes in two parameters, angle and speed,
     * both of type double. The method calculates the horizontal and vertical
     * components of velocity based on the given angle and speed, and returns
     * a new Velocity object with those components. First, the code converts
     * the given angle from degrees to radians using the Math.toRadians()
     * method. Then, it calculates the horizontal component of velocity (dx)
     * by multiplying the given speed by the cosine of the converted angle.
     * Similarly, the vertical component of velocity (dy) is calculated by
     * multiplying the given speed by the negative sine of the converted angle
     * The negative sign is included because the y-axis is assumed to be
     * inverted, positive values of dy represent upward movement.
     * Finally, a new Velocity object is created using the calculated
     * dx and dy values, and returned from the method.
     *
     * @param angle the angle of the Ball
     * @param speed the speed of the Ball
     * @return the velocity that calculated by the angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        /*convert angle from degrees to radians calculate the horizontal
          component of velocity*/
        double dx = speed * Math.sin(Math.toRadians(angle));
        /* calculate the vertical component of velocity with negative sign
         because y-axis is inverted*/
        double dy = -speed * Math.cos(Math.toRadians(angle));
        // create a new Velocity object with the calculated values of dx and dy
        return new Velocity(dx, dy);
    }

    /**
     * AThe method receives the center of a ball and adds the DX and DY
     * to the values of the center point and thus basically shows the next
     * point the ball will find in space. the method create a new point with
     * those values and return it.
     *
     * @param p the center of a ball
     * @return the new center of a ball with the aply of the velocity
     */
    public Point applyToPoint(Point p) {
        //create the values of the new point (x+dx, y+dy)
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        //return the new point
        return new Point(newX, newY);
    }

    /**
     * the method getDy return the dx value of the dx field.
     *
     * @return the dy value of the velocity from the dx field.
     */
    public double getDx() {
        //return the dx field value
        return this.dx;
    }

    /**
     * the method getDy return the dy value of the dy field.
     *
     * @return the dy value of the velocity from the dy field.
     */
    public double getDy() {
        //return the dy field value
        return this.dy;
    }
}
