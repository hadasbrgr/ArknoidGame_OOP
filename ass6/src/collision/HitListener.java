package collision;

import sprites.Ball;
import sprites.Block;

/**
 * A interface HitListener.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit. The hitter
     * parameter is the Ball that's doing the hitting.
     * @param beingHit beingHit object is hit
     * @param hitter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}