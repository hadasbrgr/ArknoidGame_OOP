package collision;

import counter.Counter;
import game.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * A BallRemover class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * a constructor to BallRemover.
     * @param game GameLevel
     */
    public BallRemover(GameLevel game) {
        this.game = game;
        this.remainingBalls = null;
    }
/**
 *  a constructor to BallRemover.
 * @param game GameLevel
 * @param removedBalls Counter
 */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * ball that are hit and reach 0 hit-points should be removed from the game.
     * Remember to remove this listener from the block that is being removed
     * from the game.
     * @param beingHit Block
     * @param hitter Ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (remainingBalls == null) {
            hitter.removeFromGame(game);
        } else {
            hitter.removeFromGame(game);
            this.remainingBalls.decrease(1);
        }
    }

}
