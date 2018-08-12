package geometry;

/**
 * A Point class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */

public class Point {
    private double x;
    private double y;

    /**
     * Construct a point given x and y coordinates.
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to an other point.
     * @param other
     *            a point to measure the distance to
     * @return the distance to other point
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * equals -- return true if the points are equal, false otherwise.
     * @param other
     *            a point to measure the equals to
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if ((other.getX() == this.x) && (other.getY() == this.y)) {
            return true;
        }
        return false;
    }

    /**
     * @return the x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate
     */
    public double getY() {
        return this.y;
    }
}