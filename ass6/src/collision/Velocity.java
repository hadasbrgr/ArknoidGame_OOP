package collision;

import geometry.Point;

/**
 * A Velocity class. Velocity specifies the change in position on the `x` and
 * the `y` axes.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Construct a velocity given dx and dy coordinates.
     * @param dx the dx coordinate.
     * @param dy the dy coordinate.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Construct a velocity given angle and speed.
     * @param angle the angle of velocity(with degree).
     * @param speed the speed (length) of velocity.
     * @return the new Velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = speed * Math.sin(radians);
        double dy = (-1) * speed * Math.cos(radians);
        return new Velocity(dx, dy);
    }

    /**
     * @return the dx coordinate
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the dy coordinate
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * set the dx velocity.
     * @param dx1 an dx Velocity
     */
    public void setDx(double dx1) {
        this.dx = dx1;
    }

    /**
     * set the dy velocity.
     * @param dy1 an dx Velocity
     */
    public void setDy(double dy1) {
        this.dy = dy1;
    }

    /**
     * Take a point with position (x,y) and return a new point with position
     * (x+dx, y+dy).
     * @param p point with position
     * @return the new point with new position
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Take a point with position (x,y) and return a new point with position*dt.
     * @param p point with position
     * @param dt double
     * @return the new point with new position
     */
    public Point applyToPoint(Point p, double dt) {
        return new Point(p.getX() + this.dx * dt, p.getY() + this.dy * dt);
    }
}
