package game;

import java.util.ArrayList;
import java.util.List;

import collision.Collidable;
import collision.CollisionInfo;
import geometry.Line;
import geometry.Point;

/**
 * A GameEnvironment class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class GameEnvironment {
    private List<Collidable> listCollidable;

    /**
     * Construct a GameEnvironment.
     */
    public GameEnvironment() {
        this.listCollidable = new ArrayList<Collidable>();
    }

    /**
     * add the given collidable to the environment.
     * @param c Collidable
     */
    public void addCollidable(Collidable c) {
        // CollisionInfo m = new CollisionInfo(c.getCollisionPoint(), c);
        this.listCollidable.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end(). If this object
     * will not collide with any of the collidables in this collection, return
     * null. Else, return the information about the closest collision that is
     * going to occur.
     * @param trajectory line
     * @return CollisionInfo
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> collidablesPoint = new ArrayList<Point>();
        int counter = -1;
        Point start = trajectory.start();
        for (Collidable i : listCollidable) {
            collidablesPoint.add(trajectory.closestIntersectionToStartOfLine(i.getCollisionRectangle()));
        }
        double min = Double.MAX_VALUE;
        for (int j = 0; j < collidablesPoint.size(); j++) {
            if (collidablesPoint.get(j) != null) {
                if (start.distance(collidablesPoint.get(j)) < min) {
                    min = start.distance(collidablesPoint.get(j));
                    counter = j;
                }
            }
        }
        if (counter != -1) {
            return new CollisionInfo(collidablesPoint.get(counter), this.listCollidable.get(counter));
        } else {
            return null;
        }
    }

    /**
     * remove the c from the list of Collidable.
     * @param c Collidable
     */
    public void removeCollidable(Collidable c) {
        this.listCollidable.remove(c);

    }
}
