package game;

import collision.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * A PrintingHitListener class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class PrintingHitListener implements HitListener {
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getNumOfHit() + " points was hit.");
    }
}