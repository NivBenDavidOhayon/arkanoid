// 213394364 Niv Ben David Ohayone
package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * The Lines class has two constructors, its fields are two points that make
 * up a straight line. The class contains operations that return the midpoint
 * of the segment of any straight line, a method that returns whether there
 * is an intersection point between two straight lines, a method that returns
 * the intersection point of two straight lines, a method that checks whether
 * the two straight lines are exactly equal.
 */
public class Line {

    /**
     * The Epsilon.
     */
    static final double EPSILON = 0.00001;
    static final int FIRST_INDEX = 0;
    static final int SECOND_INDEX = 1;



    // fields
    private final Point start;
    private final Point end;


    /**
     * The method (constructor) receives 2 points and inserts their
     * values into the start and end points fields.
     *
     * @param start Point type parameter - the beginning of the line
     * @param end   Point type parameter - the end of the line
     */
    public Line(Point start, Point end) {
        //restart the start and end points values
        this.start = start;
        this.end = end;
    }

    /**
     * The method (constructor) First receives an X value and a Y value,
     * creates a point from it and inserts its value into the start field.
     * In addition, the method receives an X value and a Y value,
     * creates a point from it and inserts its value into the end field.
     *
     * @param x1 -  X value of the start point
     * @param y1 - Y value of the start point
     * @param x2 -  X value of the end point
     * @param y2 - Y value of the start point
     */
    public Line(double x1, double y1, double x2, double y2) {
        // creates two new points and restart the values
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * The length method takes the values of the start point of the line and
     * the end point of the line and returns the distance between
     * them - that is, the length of the line.
     *
     * @return double value - the lenth of the line
     */
    public double length() {
        // return the lenth between the start and end points
        return start.distance(end());
    }

    /**
     * The middle method calculates the midpoint of the line by calculating:
     * adding the X values of the start and end points and dividing by 2,
     * adding the Y values of the start and end points and dividing by 2
     * and from these to create a point - which is the midpoint of the line.
     *
     * @return Point type parameter - the middle of the line
     */
    public Point middle() {
        // create a point consisting of the average of X and Y
        double xVal = (this.start.getX() + this.end.getX()) / 2;
        double yVal = (this.start.getY() + this.end.getY()) / 2;
        // return the point
        return new Point(xVal, yVal);
    }

    /**
     * the method start return the start point value from the start field.
     *
     * @return Point type parameter - the start of the line
     */
    public Point start() {
        //return the start value
        return this.start;
    }

    /**
     * the method end return the end point value from the end field.
     *
     * @return Point type parameter - the end of the line
     */
    public Point end() {
        //return the end value
        return this.end;
    }

    /**
     * The method finds whether there is an intersection point between two
     * straight lines. The method uses the equation of the straight line
     * y=mx+n. If an intersection point is found, the method will return true,
     * otherwise - false. The method finds during it the slope of a straight
     * line, the n of a straight line and compares them.
     * The method during it checks edge cases such as if the lines are
     * identical, if the lines are contained within each other, if the lines
     * continue each other exactly, if the lines have the same slope (then
     * cannot be divided by zero), if the lines are parallel to the Y axis.
     * and accordingly returns the desired value.
     *
     * @param other the other line that compared to "this" line
     * @return boolean parameter - true if there is a intersecting point,
     * otherwise-false
     */
    public boolean isIntersecting(Line other) {
        //if the lines are the same one
        if (equals(other)) {
            return true;
        }
        //save the minimum and meximum values of the start and end points
        double maxXOther = Math.max(other.start.getX(), other.end.getX());
        double maxYOther = Math.max(other.start.getY(), other.end.getY());
        double maxXThis = Math.max(this.start.getX(), this.end.getX());
        double maxYThis = Math.max(this.start.getY(), this.end.getY());
        double minXOther = Math.min(other.start.getX(), other.end.getX());
        double minYOther = Math.min(other.start.getY(), other.end.getY());
        double minXThis = Math.min(this.start.getX(), this.end.getX());
        double minYThis = Math.min(this.start.getY(), this.end.getY());


        // if "this" line is parallel to y axis
        if (treshold(this.start.getX(), this.end.getX())) {
            // if the "other" line parallel to y axis too
            if (treshold(other.start.getX(), other.end.getX())) {
                //if the lines have the same X
                if (treshold(this.start.getX(), other.start.getX())) {
                    //check if the lines are never intersecting - return false
                    if ((maxYOther + EPSILON < minYThis - EPSILON
                            || minYOther - EPSILON > maxYThis + EPSILON)) {
                        return false;
                    }
                    // the lines intersecting - return true
                    return true;
                }
                // if the lines parallels and will never intersect
                return false;
            }

            /* if the x value of "this" line is not in the range of the "other"
            line - it means that the lines will never intersect*/
            if (this.start.getX() < minXOther - EPSILON
                    || this.start.getX() > maxXOther + EPSILON) {
                return false;
            }
            //calculate the slope of the "other" line
            double slope = (other.start.getY() - other.end.getY())
                    / (other.start.getX() - other.end.getX());

            //check for the y in y=mx+n, by isolate it: n=y-mx
            double yIntercept = other.end.getY() - slope * other.end.getX();
            /* check for the equasion of "other" line = mx+n
               (Placing the X of the intercept in the equation of the line to
               find the Y of the intercept)*/
            double yValue = slope * this.start.getX() + yIntercept;

            /* if the Y value after the placing is not in the Y range of
            the "this" line - return false*/
            if (yValue < minYThis - EPSILON || yValue > maxYThis + EPSILON
                    || yValue < minYOther - EPSILON
                    || yValue > maxYOther + EPSILON) {
                return false;
            }
            return true;
        }

        // if the other line is parallel to y axis
        if (treshold(other.start.getX(), other.end.getX())) {

            /* if the x value of "other" line is not in the range of the "this"
            line - it means that the lines will never intersect*/
            if (other.start.getX() < minXThis - EPSILON
                    || other.start.getX() > maxXThis + EPSILON) {
                return false;
            }

            //calculate the slope of the "this" line
            double slope = (this.start.getY() - this.end.getY())
                    / (this.start.getX() - this.end.getX());
            //check for the y in y=mx+n, by isolate it: n=y-mx
            double yIntercept = this.end.getY() - slope * this.end.getX();
            /* check for the equasion of "this" line = mx+n
               (Placing the X of the intercept in the equation of the line to
               find the Y of the intercept)*/
            double yValue = slope * other.start.getX() + yIntercept;

            /* if the Y value after the placing is not in the Y range of
            the "other" line - return false*/
            if (yValue < minYThis - EPSILON || yValue > maxYThis + EPSILON
                    || yValue < minYOther - EPSILON
                    || yValue > maxYOther + EPSILON) {
                return false;
            }
            return true;
        }


        /*If the two lines are not parallel to the Y axis - Calculate slopes
         and y-intercepts of both lines*/

        //calculate the slope of the "this" line
        double slope1 = (this.end.getY() - this.start.getY())
                / (this.end.getX() - this.start.getX());
        //check for the y in y=mx+n, by isolate it: n=y-mx
        double yIntercept1 = this.start.getY() - slope1 * this.start.getX();

        //calculate the slope of the "other" line
        double slope2 = (other.end.getY() - other.start.getY())
                / (other.end.getX() - other.start.getX());
        //check for the y in y=mx+n, by isolate it: n=y-mx
        double yIntercept2 = other.start.getY() - slope2 * other.start.getX();

        // if the slopes are equal
        if (treshold(slope1, slope2)) {
            // if the "n" of the lines (in y=mx+n) are equals too
            if (treshold(yIntercept1, yIntercept2)) {
                //if the lines are not one of the other range
                if (minXOther - EPSILON > maxXThis + EPSILON
                        || maxXOther + EPSILON < minXThis - EPSILON) {
                    return false;
                }
                //the lines have an intersection point
                return true;
            }
            /* the ines have the same slope but not the same n - it means
               they are parallels and will never intersect */
            return false;
        }

        //Checks if one line starts at the point the other line ends
        if (treshold(this.start.getX(), other.end.getX())
                && treshold(this.start.getY(), other.end.getY())) {
            //return the intersection point
            return true;
        }
        //Checks if one line starts at the point the other line ends
        if (treshold(other.start.getX(), this.end.getX())
                && treshold(other.start.getY(), this.end.getY())) {
            //return the intersection point
            return true;
        }
        //Checks if one line starts at the point the other line starts
        if (treshold(other.start.getX(), this.start.getX())
                && treshold(other.start.getY(), this.start.getY())) {
            //return the intersection point
            return true;
        }
        //Checks if one line ends at the point the other line ends
        if (treshold(other.end.getX(), this.end.getX())
                && treshold(other.end.getY(), this.end.getY())) {
            //return the intersection point
            return true;
        }

        /*now we check that the slopes are not equals and we know that we do
          not dividing in 0*/

        // Find the intersection point x=(n2-n1)/(m1-m2)
        double x = (yIntercept2 - yIntercept1) / (slope1 - slope2);
        // Find the intersection point y= m1*x+n1
        double y = slope1 * x + yIntercept1;

        // Check if the intersection point lies on both line segments

        if ((x >= minXThis - EPSILON && x <= maxXThis + EPSILON)
                && (x >= minXOther - EPSILON && x <= maxXOther + EPSILON)) {
            return true;
        }
        //return the intersection point
        return false;
    }


    /**
     * The method finds whether there is an intersection point between two
     * straight lines - if there is returne the point, otherwise-return null.
     * The method uses the equation of the straight line y=mx+n.
     * The method finds during it the slope of a straight line, the n of a
     * straight line and compares them.The method during it checks edge cases
     * such as if the lines are identical, if the lines are contained within
     * each other, if the line continue each other exactly, if the lines have
     * the same slope (then cannot be divided by zero), if the lines are
     * parallel to the Y axis and accordingly returns the desired value.
     *
     * @param other the other line to calculate intersection with
     * @return the intersection point, or null if the lines do not intersection
     */
    public Point intersectionWith(Line other) {

        if (this.isIntersecting(other)) {
            //if the lines are the same one
            if (this.equals(other)) {
                return null;
            }
        }

        //save the minimum and maximum values of the start and end points
        double maxXOther = Math.max(other.start.getX(), other.end.getX());
        double maxYOther = Math.max(other.start.getY(), other.end.getY());
        double maxXThis = Math.max(this.start.getX(), this.end.getX());
        double maxYThis = Math.max(this.start.getY(), this.end.getY());
        double minXOther = Math.min(other.start.getX(), other.end.getX());
        double minYOther = Math.min(other.start.getY(), other.end.getY());
        double minXThis = Math.min(this.start.getX(), this.end.getX());
        double minYThis = Math.min(this.start.getY(), this.end.getY());


        // if "this" line is parallel to y axis
        if (treshold(this.start.getX(), this.end.getX())) {
            // if the "other" line parallel to y axis too
            if (treshold(other.start.getX(), other.end.getX())) {
                //if the lines have the same X
                if (treshold(this.start.getX(), other.start.getX())) {
                    //if one line start at the same point the other line end
                    if (treshold(maxYOther, minYThis)) {
                        //return this point
                        return new Point(this.start.getX(), minYThis);
                    }
                    //if one line start at the same point the other line end
                    if (treshold(minYOther, maxYThis)) {
                        //return this point
                        return new Point(this.start.getX(), minYOther);
                    }
                    return null;
                }
                return null;
            }

            /* if the x value of "this" line is not in the range of the "other"
            line - it means that the lines will never intersect*/
            if (this.start.getX() < minXOther - EPSILON
                    || this.start.getX() > maxXOther + EPSILON) {
                return null;
            }
            //calculate the slope of the "other" line
            double slope = (other.start.getY() - other.end.getY())
                    / (other.start.getX() - other.end.getX());
            //check for the y in y=mx+n, by isolate it: n=y-mx
            double yIntercept = other.end.getY() - slope * other.end.getX();

            /* check for the equasion of "other" line = mx+n
               (Placing the X of the intercept in the equation of the line to
               find the Y of the intercept)*/

            double yValue = slope * this.start.getX() + yIntercept;
            /* if the Y value after the placing is not in the Y range of
            the "this" line - return null*/
            if (yValue < minYThis - EPSILON || yValue > maxYThis + EPSILON
                    || yValue < minYOther - EPSILON
                    || yValue > maxYOther + EPSILON) {
                return null;
            }
            //else - return the intercept point
            return new Point(this.start.getX(), yValue);
        }

        // if the "other" line is parallel to y axis
        if (treshold(other.start.getX(), other.end.getX())) {

            /* if the x value of "other" line is not in the range of the "this"
            line - it means that the lines will never intersect*/
            if (other.start.getX() < minXThis - EPSILON
                    || other.start.getX() > maxXThis + EPSILON) {
                return null;
            }

            //calculate the slope of the "this" line
            double slope = (this.start.getY() - this.end.getY())
                    / (this.start.getX() - this.end.getX());
            //check for the y in y=mx+n, by isolate it: n=y-mx
            double yIntercept = this.end.getY() - slope * this.end.getX();

            /* check for the equasion of "this" line = mx+n
               (Placing the X of the intercept in the equation of the line to
               find the Y of the intercept)*/
            double yValue = slope * other.start.getX() + yIntercept;

            /* if the Y value after the placing is not in the Y range of
            the "other" line - return null*/
            if (yValue < minYOther - EPSILON || yValue > maxYOther + EPSILON
                    || yValue < minYThis - EPSILON
                    || yValue > maxYThis + EPSILON) {
                return null;
            }
            /*If the X values of the two lines or the Y values of the two
            lines are not the same*/
            //else - return the intercept point
            return new Point(other.start.getX(), yValue);
        }

        /*If the two lines are not parallel to the Y axis - Calculate slopes
         and y-intercepts of both lines*/

        //calculate the slope of the "this" line
        double slope1 = (this.end.getY() - this.start.getY())
                / (this.end.getX() - this.start.getX());
        //check for the y in y=mx+n, by isolate it: n=y-mx
        double yIntercept1 = this.start.getY() - slope1 * this.start.getX();
        //calculate the slope of the "other" line
        double slope2 = (other.end.getY() - other.start.getY())
                / (other.end.getX() - other.start.getX());
        //check for the y in y=mx+n, by isolate it: n=y-mx
        double yIntercept2 = other.start.getY() - slope2 * other.start.getX();


        // If the slopes are equal
        if (treshold(slope1, slope2)) {
            // if the "n" of the lines (in y=mx+n) are equals too
            if (treshold(yIntercept1, yIntercept2)) {
                // there are 4 cases to one intersection point
                // if this start and other end same point
                if ((treshold(this.start.getX(), other.end.getX()))
                        && (treshold(this.start.getY(), other.end.getY()))) {
                    // if there is inclusion
                    if (other.start.getX() >= minXThis - EPSILON
                            && other.start.getX() <= maxXThis + EPSILON
                            || this.end.getX() >= minXOther - EPSILON
                            && this.end.getX() <= maxXOther + EPSILON) {
                        return null;
                    }
                    return this.start;
                }
                // if this end and other start same point
                if ((treshold(other.start.getX(), this.end.getX()))
                        && (treshold(other.start.getY(), this.end.getY()))) {
                    // if there is inclusion
                    if (other.end.getX() >= minXThis - EPSILON
                            && other.end.getX() <= maxXThis + EPSILON
                            || this.start.getX() >= minXOther - EPSILON
                            && this.start.getX() <= maxXOther + EPSILON) {
                        return null;
                    }
                    return other.start;
                }
                // if this start and other start same point
                if ((treshold(this.start.getX(), other.start.getX()))
                        && (treshold(this.start.getY(), other.start.getY()))) {
                    // if there is inclusion
                    if (other.end.getX() >= minXThis - EPSILON
                            && other.end.getX() <= maxXThis + EPSILON
                            || this.end.getX() >= minXOther - EPSILON
                            && this.end.getX() <= maxXOther + EPSILON) {
                        return null;
                    }
                    return this.start;
                }
                // if this end and other end same point
                if ((treshold(other.end.getX(), this.end.getX()))
                        && (treshold(other.end.getY(), this.end.getY()))) {
                    // if there is inclusion
                    if (other.start.getX() >= minXThis - EPSILON
                            && other.start.getX() <= maxXOther + EPSILON
                            || this.start.getX() >= minXOther - EPSILON
                            && this.start.getX() <= maxXOther + EPSILON) {
                        return null;
                    }
                    return other.end;
                }
                // not share or share infinite points
//                return null;

                // if the lines are parallel to X axis
                if (slope1 == 0) {
                    //Checks if one line starts at the point the other line ends
                    if (treshold(this.start.getX(), other.end.getX())
                            && treshold(this.start.getY(), other.end.getY())) {
                        //return the intersection point
                        return this.start;
                    }
                    //Checks if one line starts at the point the other line ends
                    if (treshold(other.start.getX(), this.end.getX())
                            && treshold(other.start.getY(), this.end.getY())) {
                        //return the intersection point
                        return other.start;
                    }
                    /*if the both slopes are parallels to X axis but not
                     at the same x value - it means they will never intersect*/
                    return null;
                }
                // if the lines Values are not in the same range
                if (minXOther - EPSILON > maxXThis + EPSILON
                        || maxXOther + EPSILON < minXThis - EPSILON) {
                    return null;
                }
                //if one of the lines is contained exactly in the other line
                if (minYOther - EPSILON > minYThis && maxYOther < maxYThis
                        || minYOther < minYThis - EPSILON
                        && maxYOther > maxYThis + EPSILON) {
                    return null;
                }

                //Checks if one line starts at the point the other line ends
                if (treshold(this.start.getX(), other.end.getX())
                        && treshold(this.start.getY(), other.end.getY())) {
                    //return the intersection point
                    return this.start;
                }
                //Checks if one line starts at the point the other line ends
                if (treshold(other.start.getX(), this.end.getX())
                        && treshold(other.start.getY(), this.end.getY())) {
                    //return the intersection point
                    return other.start;
                }
            }
            return null;
        }
        //Checks if one line starts at the point the other line ends
        if (treshold(this.start.getX(), other.end.getX())
                && treshold(this.start.getY(), other.end.getY())) {
            //return the intersection point
            return this.start;
        }
        //Checks if one line starts at the point the other line ends
        if (treshold(other.start.getX(), this.end.getX())
                && treshold(other.start.getY(), this.end.getY())) {
            //return the intersection point
            return other.start;
        }
        //Checks if one line starts at the point the other line starts
        if (treshold(other.start.getX(), this.start.getX())
                && treshold(other.start.getY(), this.start.getY())) {
            //return the intersection point
            return other.start;
        }
        //Checks if one line end at the point the other line ends
        if (treshold(other.end.getX(), this.end.getX())
                && treshold(other.end.getY(), this.end.getY())) {
            //return the intersection point
            return other.end;
        }

        // Find the intersection point x=(n2-n1)/(m1-m2)
        double x = (yIntercept2 - yIntercept1) / (slope1 - slope2);
        // Find the intersection point y= m1*x+n1
        double y = slope1 * x + yIntercept1;

        // Check if the intersection point lies on both line segments
        if ((x >= minXThis - EPSILON && x <= maxXThis + EPSILON)
                && (x >= minXOther - EPSILON && x <= maxXOther + EPSILON)) {
            return new Point(x, y);
        }
        //return the intersection point
        return null;
    }


    /**
     * The equals method is used to determine if two lines are equal
     * to each other. the method do it by compare the start point of the
     * first line with the start point of the other line and the end point of
     * the first line with the end point of the other line.
     * On the other hand, even  lines with opposite points will be
     * considered equal and therefore compares the start point of the first
     * line with the end point of The other line and the end point of the
     * line with the start point of the other line
     * If the equality holds - it will return true - otherwise
     * it will return false.
     *
     * @param other the other
     * @return boolean parameter. true - if the lines are equals.
     * otherwise - false.
     */
    public boolean equals(Line other) {

        /* if the start point of this and the start point of other are equal
           and the end point of this and the end point of other are equals
           - the lines are equals*/
        if (treshold(this.start.getX(), other.start.getX())
                && treshold(this.start.getY(), other.start.getY())
                && treshold(this.end.getX(), other.end.getX())
                && treshold(this.end.getY(), other.end.getY())) {
            return true;
        }
        /* if the start point of this and the end point of other are equal
           and the end point of this and the start point of other are equals
           - the lines are equals - The starting and ending order of the line
            is not important*/
        if (treshold(this.start.getX(), other.end.getX())
                && treshold(this.start.getY(), other.end.getY())
                && treshold(this.end.getX(), other.start.getX())
                && treshold(this.end.getY(), other.start.getY())) {
            return true;
        }
        //else - the lines are not equals
        return false;
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
     * Closest intersection to start of line point.
     *
     * @param rect the rect
     * @return the point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // create a new Line object with the start and end points of the line
        Line newLine = new Line(this.start, this.end);
        /* create a new list to store intersection points between the
         line and the rectangle*/
        List<Point> intersectionPoints = new ArrayList<>();
        // find the intersection points between the line and the rectangle
        intersectionPoints = rect.intersectionPoints(newLine);
        // if there are no intersection points, return null
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        //save the first intersection point to compare it with the other points
        Point closest = intersectionPoints.get(FIRST_INDEX);
        for (int i = SECOND_INDEX; i < intersectionPoints.size(); i++) {
            if (intersectionPoints.get(i) == null || closest == null) {
                continue;
            }
            //compare the closest value with any point distance
            if (intersectionPoints.get(i).distance(rect.getUpperLeft())
                    < closest.distance(rect.getUpperLeft()) + EPSILON) {
                closest = intersectionPoints.get(i);
            }
        }
        //return the most close point
        return closest;
    }
}

