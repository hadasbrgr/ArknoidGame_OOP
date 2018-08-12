package geometry;

import java.util.List;

/**
 * A Line class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * Construct a Line given start and end point.
     * @param start the point
     * @param end the point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Construct a Line given x,y coordinates.
     * @param x1 the x point 1
     * @param y1 the y point 1
     * @param x2 the x point 2
     * @param y2 the x point 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Return the length of the line.
     * @return length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     * @return middle point of the line
     */
    public Point middle() {
        double x1 = (start.getX() + end.getX()) / 2;
        double y1 = (start.getY() + end.getY()) / 2;
        return new Point(x1, y1);
    }

    /**
     * Returns the start point of the line.
     * @return start point of the line
     */
    public Point start() {
        return new Point(start.getX(), start.getY());
    }

    /**
     * Returns the end point of the line.
     * @return end point of the line
     */
    public Point end() {
        return new Point(end.getX(), end.getY());
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     * @param other is line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        Point m = this.intersectionWith(other);
        if (((slope()) != slope(other.start(), other.end())) && (m != null)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the intersection point if the lines intersect, and null
     * otherwise.
     * @param other is line
     * @return the intersection point if the lines intersect, and null
     *         otherwise.
     */
    public Point intersectionWith(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();
        double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (d == 0) {
            return null;
        }
        double xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
        double yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;
        Point p1 = new Point(xi, yi);
        if (this.pointFindOnLine(p1) && other.pointFindOnLine(p1)) {
            return p1;
        } else {
            return null;
        }
    }

    /**
     * equals -- return true is the lines are equal, false otherwise.
     * @param other a line to measure the equals to
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start())) && (this.end.equals(other.end()))) {
            return true;
        }
        return false;
    }

    /**
     * pointFindOnLine -- return true if the point check is on the line and
     * false otherwise.
     * @param check a point to measure the find on line
     * @return true if the point is on the line and false otherwise
     */
    public boolean pointFindOnLine(Point check) {
        if ((this.start.getX() <= check.getX() && check.getX() <= this.end.getX())
                || (this.start.getX() >= check.getX() && check.getX() >= this.end.getX())) {
            if (((this.start.getY() <= check.getY() && check.getY() <= this.end.getY())
                    || (this.start.getY() >= check.getY() && check.getY() >= this.end.getY()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * return the incline of the line class.
     * @return slope of current point
     */
    public double slope() {
        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        return (y1 - y2) / (x1 - x2);
    }

    /**
     * return the incline of the line class.
     * @param start1 point's start line
     * @param end1 point's end line
     * @return slope of given point
     */
    public double slope(Point start1, Point end1) {
        double x1 = start1.getX();
        double x2 = end1.getX();
        double y1 = start1.getY();
        double y2 = end1.getY();
        return (y1 - y2) / (x1 - x2);
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the
     * line.
     * @param rect rectangle
     * @return point that closest Intersection To Start Of Line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        double distanceTempMin = Double.MAX_VALUE;
        Line line = new Line(start, end);
        List<Point> list1 = rect.intersectionPoints(line);
        Point tempMin = null;
        if (rect.intersectionPoints(line) == null) {
            return null;
        } else {
            for (int i = 0; i < list1.size(); i++) {
                if (this.start.distance(list1.get(i)) < distanceTempMin) {
                    tempMin = list1.get(i);
                    distanceTempMin = this.start.distance(list1.get(i));
                }
            }
        }
        return tempMin;
    }
}