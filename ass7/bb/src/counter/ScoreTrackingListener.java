package counter;

import collision.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * A ScoreTrackingListener class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * a constructor to ScoreTrackingListener.
     * @param scoreCounter Counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit. The hitter
     * parameter is the Ball that's doing the hitting.
     * @param beingHit beingHit object is hit
     * @param hitter is the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        /*if (beingHit.getNumOfHit() >= 0) {
            currentScore.increase(5);
        }*/
        if (beingHit.getNumOfHit() == 0) {
            currentScore.increase(100);
        }
    }

}
