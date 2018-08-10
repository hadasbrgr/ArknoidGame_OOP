package geometry;
import java.util.ArrayList;
import java.util.List;


/**
 * A Game Rectangle.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft
     *            the left point of the rectangle.
     * @param width
     *            the width of the rectangle.
     * @param height
     *            the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified
     * line.
     * @param line
     *            a line that we check on him the intersection points
     * @return List<Point> of intersection Points on line
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> listOfIntersectionPoint = new ArrayList<Point>();
        if (this.upperLine().isIntersecting(line)) {
            listOfIntersectionPoint.add(this.upperLine().intersectionWith(line));
        }
        if (this.lowerLine().isIntersecting(line)) {
            listOfIntersectionPoint.add(this.lowerLine().intersectionWith(line));
        }
        if (this.leftSideLine().isIntersecting(line)) {
            listOfIntersectionPoint.add(this.leftSideLine().intersectionWith(line));
        }
        if (this.rightSideLine().isIntersecting(line)) {
            listOfIntersectionPoint.add(this.rightSideLine().intersectionWith(line));
        }
        return listOfIntersectionPoint;
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     * @return the upperLeft point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * set the upperLeft of the rectangle.
     * @param upperLeft1
     *            the upperLeft point og the rectangle
     */
    public void setUpperLeft(Point upperLeft1) {
        this.upperLeft = upperLeft1;
    }

    /**
     * set the width.
     * @param width1
     *            of the rectangle.
     */
    public void setWidth(double width1) {
        this.width = width1;
    }

    /**
     * set the height.
     * @param height1
     *            of the rectangle.
     */
    public void setHeight(double height1) {
        this.height = height1;
    }

    /**
     * @return the upperLine of the rectangle.
     */
    public Line upperLine() {
        return new Line(this.upperLeft, new Point(this.width + this.upperLeft.getX(),
                this.upperLeft.getY()));
    }

    /**
     * @return the lowerLine of the rectangle.
     */
    public Line lowerLine() {
        return new Line(new Point(this.upperLeft.getX(), this.height + this.upperLeft.getY()),
                new Point(this.width + this.upperLeft.getX(),
                        this.height + this.upperLeft.getY()));
    }

    /**
     * @return the leftSideLine of the rectangle.
     */
    public Line leftSideLine() {
        return new Line(this.upperLeft, new Point(this.upperLeft.getX(),
                this.height + this.upperLeft.getY()));
    }

    /**
     * @return the rightSideLine of the rectangle.
     */
    public Line rightSideLine() {
        return new Line(new Point(this.width + this.upperLeft.getX(), this.upperLeft.getY()),
                new Point(this.width + this.upperLeft.getX(),
                        this.height + this.upperLeft.getY()));
    }

    /**
     * @return the left Lower Point of the rectangle.
     */
    public Point leftLowerPoint() {
        return new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.height);
    }

    /**
     * @return the right Lower Point of the rectangle.
     */
    public Point rightLowerPoint() {
        return new Point(this.getUpperLeft().getX() + this.width,
                this.getUpperLeft().getY() + this.height);
    }

    /**
     * @return the right Upper Point of the rectangle.
     */
    public Point rightUpperPoint() {
        return new Point(this.getUpperLeft().getX() + this.width,
                this.getUpperLeft().getY());
    }
}
