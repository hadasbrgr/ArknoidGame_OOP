package levels;

import java.util.ArrayList;
import java.util.List;
import collision.Velocity;
import sprites.Block;
import sprites.Sprite;

/**
 * A Level class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class Level implements LevelInformation {
    private String levelName;
    private Sprite background;
    private Integer numberOfBalls;
    private Integer numberOfBlocksToRemove;
    private List<Block> blocks;
    private List<Velocity> initialBallVelocities;
    private Integer paddleSpeed;
    private Integer paddleWidth;
    private Integer paddleHeight;
    private Integer blocksStartX;
    private Integer blocksStartY;
    private Integer rowHeight;

    /**
     * @param levelName
     * @param background
     * @param numberOfBalls
     * @param numberOfBlocksToRemove
     * @param blocks
     * @param initialBallVelocities
     * @param paddleSpeed
     * @param paddleWidth
     * @param paddleHeight
     * @param blocksStartX
     * @param blocksStartY
     * @param rowHeight
     */
    public Level() {
        this.levelName = null;
        this.background = null;
        this.numberOfBalls = null;
        this.numberOfBlocksToRemove = null;
        this.blocks = new ArrayList<Block>();
        this.initialBallVelocities = new ArrayList<Velocity>();
        this.paddleSpeed = null;
        this.paddleWidth = null;
        this.paddleHeight = null;
        this.blocksStartX = null;
        this.blocksStartY = null;
        this.rowHeight = null;
    }

    /**
     * @return the levelName
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * @param levelName1 the levelName to set
     */
    public void setLevelName(String levelName1) {
        this.levelName = levelName1;
    }

    /**
     * @return the background
     */
    public Sprite getBackground() {
        return background;
    }

    /**
     * @param background1 the background to set
     */
    public void setBackground(Sprite background1) {
        this.background = background1;
    }

    /**
     * @return the numberOfBalls
     */
    public Integer getNumberOfBalls() {
        return numberOfBalls;
    }

    /**
     * @param numberOfBalls1 the numberOfBalls to set
     */
    public void setNumberOfBalls(Integer numberOfBalls1) {
        this.numberOfBalls = numberOfBalls1;
    }

    /**
     * @return the numberOfBlocksToRemove
     */
    public Integer getNumberOfBlocksToRemove() {
        return numberOfBlocksToRemove;
    }

    /**
     * @param numberOfBlocksToRemove1 the numberOfBlocksToRemove to set
     */
    public void setNumberOfBlocksToRemove(Integer numberOfBlocksToRemove1) {
        this.numberOfBlocksToRemove = numberOfBlocksToRemove1;
    }

    /**
     * @return the blocks
     */
    public List<Block> getBlocks() {
        return blocks;
    }

    /**
     * @param blocks1 the blocks to set
     */
    public void setBlocks(List<Block> blocks1) {
        this.blocks = blocks1;
    }

    /**
     * @param block1 block
     */
    public void addBlock(Block block1) {
        this.blocks.add(block1);
    }

    /**
     * @return the initialBallVelocities
     */
    public List<Velocity> getInitialBallVelocities() {
        return initialBallVelocities;
    }

    /**
     * @param initialBallVelocities1 the initialBallVelocities to set
     */
    public void setInitialBallVelocities(List<Velocity> initialBallVelocities1) {
        this.initialBallVelocities = initialBallVelocities1;
    }
/**
 * add Initial Ball Velocities.
 * @param velocity Velocity
 */
    public void addInitialBallVelocities(Velocity velocity) {
        this.initialBallVelocities.add(velocity);
    }

    /**
     * @return the paddleSpeed
     */
    public Integer getPaddleSpeed() {
        return paddleSpeed;
    }

    /**
     * @param paddleSpeed1 the paddleSpeed to set
     */
    public void setPaddleSpeed(Integer paddleSpeed1) {
        this.paddleSpeed = paddleSpeed1;
    }

    /**
     * @return the paddleWidth
     */
    public Integer getPaddleWidth() {
        return paddleWidth;
    }

    /**
     * @param paddleWidth1 the paddleWidth to set
     */
    public void setPaddleWidth(Integer paddleWidth1) {
        this.paddleWidth = paddleWidth1;
    }

    /**
     * @return the paddleHeight
     */
    public Integer getPaddleHeight() {
        return paddleHeight;
    }

    /**
     * @param paddleHeight1 the paddleHeight to set
     */
    public void setPaddleHeight(Integer paddleHeight1) {
        this.paddleHeight = paddleHeight1;
    }

    /**
     * @return the blocksStartX
     */
    public Integer getBlocksStartX() {
        return blocksStartX;
    }

    /**
     * @param blocksStartX1 the blocksStartX to set
     */
    public void setBlocksStartX(Integer blocksStartX1) {
        this.blocksStartX = blocksStartX1;
    }

    /**
     * @return the blocksStartY
     */
    public Integer getBlocksStartY() {
        return blocksStartY;
    }

    /**
     * @param blocksStartY1 the blocksStartY to set
     */
    public void setBlocksStartY(Integer blocksStartY1) {
        this.blocksStartY = blocksStartY1;
    }

    /**
     * @return the rowHeight
     */
    public Integer getRowHeight() {
        return rowHeight;
    }

    /**
     * @param rowHeight1 the rowHeight to set
     */
    public void setRowHeight(Integer rowHeight1) {
        this.rowHeight = rowHeight1;
    }

    @Override
    public int numberOfBalls() {
        return this.getNumberOfBalls();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.getInitialBallVelocities();
    }

    @Override
    public int paddleSpeed() {
        return this.getPaddleSpeed();
    }

    @Override
    public int paddleWidth() {
        return this.getPaddleWidth();
    }

    @Override
    public String levelName() {
        return this.getLevelName();
    }

    @Override
    public List<Block> blocks() {
        return this.getBlocks();
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.getNumberOfBlocksToRemove();
    }

}
