package collision;

import counter.Counter;
import game.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * A BlockRemover class.
 * a BlockRemover is in charge of removing blocks from the
 * game, as well as keeping count of the number of blocks that remain.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * a constructor to BlockRemover.
     * @param game GameLevel
     * @param removedBlocks Counter
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }
/**
 * a constructor to BlockRemover.
 * @param gameLevel GameLevel
 */
    public BlockRemover(GameLevel gameLevel) {
        this.game = gameLevel;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed from the
     * game. Remember to remove this listener from the block that is being
     * removed from the game.
     * @param beingHit Block
     * @param hitter Ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getNumOfHit() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(game);
            game.removeEnemy(beingHit);
        }
        }
}
