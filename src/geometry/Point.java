// 213394364 Niv Ben David Ohayone
package geometry;


/**
 * The class contains two instance variables, x and y, that are set through a
 * constructor. The class has additional methods that allow for calculation of
 * the distance between two Point objects, comparison of two Point objects for
 * equality, and retrieval of the x and y values of a Point object.
 * Overall, this Point class can be used to create and manipulate Point objects
 * in two-dimensional space.
 */
public class Point {
    static final double EPSILON = 0.00001;

    // fields
    private final double x;
    private final double y;

    /**
     * The method(constructor) receives 2 double values and inserts their
     * values into the X and Y fields.
     *
     * @param x - the x value of the point
     * @param y - the y value of the point
     */
    public Point(double x, double y) {
        //restart the x and y values
        this.x = x;
        this.y = y;
    }

    /**
     * The method distance receives a point and compares it to the point we
     * have in the field. The method will first check whether the points
     * are the equals - if so it will return 0 otherwise - calculate the
     * distance between the two points and return it.
     *
     * @param other the "other" point -  a new point that we will compare
     *              it to the existing point in our field "this" line
     * @return the distane between the two points ("other" and "this")
     */
    public double distance(Point other) {
        // if the points are equal - return 0
        if (equals(other)) {
            return 0;
        }
        // calculate the distance between the points
        double val1 = Math.pow(this.x - other.x, 2);
        double val2 = Math.pow(this.y - other.y, 2);
        //return the final value
        return Math.sqrt(val1 + val2);
    }

    /**
     * The method Equals receives a point and compares it to the existing
     * point in the field. The method compares between the two X values of
     * the points and between the two Y values of the points and returns true
     * if both values are equal - that is, the points are identical.
     * Otherwise - return false
     *
     * @param other - the "other" point -  a new point that we will
     *              compare it to the existing point in our field "this" line
     * @return true - if the points are equals and false if the
     * points are different
     */
    public boolean equals(Point other) {
//        if(other==null){
//            return false;
//        }
        // compare the x valus and the y values
        if (treshold(this.x, other.x) && treshold(this.y, other.y)) {
            return true;
        }
        return false;
    }

    /**
     * the method getX return the x value from the x field.
     *
     * @return the X value of the point in the field.
     */
    public double getX() {

        return this.x; //return the x value
    }

    /**
     * the method getY return the y value from the y field.
     *
     * @return the Y value of the point in the field.
     */
    public double getY() {

        return this.y; //return the y value
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

}
