package levels;

import java.util.List;

import collision.Velocity;
import sprites.Block;
import sprites.Sprite;

/**
 * A interface LevelInformation.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public interface LevelInformation {
    /**
     * Note that initialBallVelocities().size() == numberOfBalls().
     * @return number Of Balls
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * @return List<Velocity>
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return paddle Speed
     */
    int paddleSpeed();

    /**
     * @return paddle Width
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return level Name
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return get Background
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color
     * and location.
     * @return List<Block>
     */
    List<Block> blocks();

    /**
     * Number of levels that should be removed.
     * @return number Of Blocks To Remove
     */
    int numberOfBlocksToRemove();
}