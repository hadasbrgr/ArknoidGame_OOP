package collision;
import geometry.Point;

/**
 * A CollisionInfo interface.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class CollisionInfo {
    private Point collisionOccursPoint;
    private Collidable object;

    /**
     * Construct a CollisionInfo given point and object.
     * @param collisionOccursPoint
     *            the point collision Occurs Point
     * @param object
     *            a Collidable
     */
    public CollisionInfo(Point collisionOccursPoint, Collidable object) {
        this.collisionOccursPoint = collisionOccursPoint;
        this.object = object;
    }

    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionOccursPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.object;
    }
}