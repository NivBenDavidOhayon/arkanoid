// 213394364 Niv Ben David Ohayone
package geometry;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 The Rectangle class represents a rectangle in a two-dimensional coordinate
 system. It has methods for calculating intersection points between rectangles
 with lines. Storing information about the height of the rectangle,
 the width of the rectangle, its starting point and its color.
 */
public class Rectangle {
    static final double EPSILON = 0.00001;

    private final double width;
    private final double height;

    private Point upperLeft;
    private java.awt.Color color;

    /**
     * the method (constructor)set the rectangle values with the upper left,
     * width and height that it get.
     *
     * @param upperLeft the upper left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * the method (constructor)set the rectangle values with the upper left,
     * width, height and color that it get.
     *
     * @param upperLeft the upper left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     * @param color     the color of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height,
                     java.awt.Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * The method intersectionPoints receives a Line and checks if it
     * intersects with any of the four lines of a Rectangle. If there are
     * intersections, it returns a list of Point objects representing the
     * intersection points.
     *
     * @param line the line that the method check if intersection with
     *            the rectangle
     * @return the list of all intersection points
     */
// Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        //create a new list that save all the points
        List<Point> pointsList = new ArrayList<Point>();

        //calculate the left down point and the line that connects through it
        Point firstLeftDown = new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.height);
        Line firstLine = new Line(this.upperLeft, firstLeftDown);

        //calculate the right upper point and the line that connects through it
        Point secondRightUpper = new Point(this.upperLeft.getX()
                + this.getWidth(), this.upperLeft.getY());
        Line secondLine = new Line(this.upperLeft, secondRightUpper);

        //calculate the right down point and the line that connects through it
        Point thirdRightDown = new Point(this.upperLeft.getX()
                + this.getWidth(), this.upperLeft.getY() + this.getHeight());
        Line thirdLine = new Line(secondRightUpper, thirdRightDown);

        //calculate the last line at the rectangle
        Line fourLine = new Line(thirdRightDown, firstLeftDown);



        // if the line is intersecting with the firstLine of the rectangle
        if (line.isIntersecting(firstLine)) {
            //add the intersection point to the list
            pointsList.add(line.intersectionWith(firstLine));
        }
        // if the line is intersecting with the secondLine of the rectangle
        if (line.isIntersecting(secondLine)) {
            //add the intersection point to the list
            pointsList.add(line.intersectionWith(secondLine));
        }
        // if the line is intersecting with the thirdLine of the rectangle
        if (line.isIntersecting(thirdLine)) {
            //add the intersection point to the list
            pointsList.add(line.intersectionWith(thirdLine));
        }
        // if the line is intersecting with the fourLine of the rectangle
        if (line.isIntersecting(fourLine)) {
            //add the intersection point to the list
            pointsList.add(line.intersectionWith(fourLine));
        }
        //return the final list
        return pointsList;
    }
    /**
     * method that return the width value of the rectangle.
     *
     * @return the width value
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.width;
    }

    /**
     * method that return the height value of the rectangle.
     *
     * @return the height value
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * method that return the upper left point value of the rectangle.
     *
     * @return the upper left point value
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * fillRectangle method draws a filled rectangle with a specified color
     * on a given DrawSurface object and also draws a black outline around it.
     *
     * @param d the DrawSurface parameter to draw on it
     */
    public void fillRectangle(DrawSurface d) {

        d.setColor(this.color);
        //draw and fill a rectangle on that surface.
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) width, (int) height);
        //Draws black lines around the rectangles
        d.setColor(Color.BLACK);
        d.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) width, (int) height);

    }

    /**
     * method that return the color of the rectangle.
     *
     * @return the color of the rectangle
     */
    public Color getColor() {
        return this.color;
    }


}
