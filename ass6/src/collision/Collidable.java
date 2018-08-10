package collision;

import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;

/**
 * A Collidable interface.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return Rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity. The return is the new velocity expected after the hit (based
     * onthe force the object inflicted on us).
     * @param collisionPoint the collision Point
     * @param currentVelocity the current Velocity
     * @param hitter ball
     * @return Velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
