package levels;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import collision.Velocity;
import geometry.Point;
import geometry.Rectangle;
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
    private int numLevel;
    private Sprite background;
    private Integer numberOfAliensToRemove;
    private List<Block> blocks;
    private List<Velocity> initialBallVelocities;
    private Integer paddleSpeed;
    private Integer paddleWidth;
    private Integer paddleHeight;
    private Integer ailensStartX;
    private Integer ailensStartY;
    private Integer rowHeight;
    private int velocityChange;

    /**
     * @param levelName
     * @param background
     * @param numberOfBalls
     * @param numberOfAliensToRemove
     * @param blocks
     * @param initialBallVelocities
     * @param paddleSpeed
     * @param paddleWidth
     * @param paddleHeight
     * @param ailensStartX
     * @param ailensStartY
     * @param rowHeight
     */
    public Level() {
        this.levelName = null;
        this.background = null;
        this.numberOfAliensToRemove = null;
        this.blocks = new ArrayList<Block>();
        this.initialBallVelocities = new ArrayList<Velocity>();
        this.paddleSpeed = null;
        this.paddleWidth = null;
        this.paddleHeight = null;
        this.ailensStartX = null;
        this.ailensStartY = null;
        this.rowHeight = null;
    }
/**
 * Constructor.
 * @param levelName String
 * @param numberOfAliensToRemove Integer
 * @param numLevel int
 */
    public Level(String levelName, Integer numberOfAliensToRemove, int numLevel) {
        this.levelName = levelName;
        this.numLevel = numLevel;
        this.background = new Background(Color.BLACK);
        this.numberOfAliensToRemove = numberOfAliensToRemove;
        this.blocks = createAilens();
        this.paddleSpeed = 650;
        this.paddleWidth = 100;
        this.paddleHeight = 25;
        this.ailensStartX = 20;
        this.ailensStartY = 30;
        this.velocityChange = this.numLevel * 5;
    }
/**
 * createAilens.
 * @return List<Block>
 */
    private List<Block> createAilens() {
        List<Block> ailensList = new ArrayList<Block>();
        Color color = Color.black;
        int x = 0;
        int y = 0;
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 5; i++) {
                Block b = new Block(new Rectangle(new Point(25 + x, 78 + y), 50, 30), color, 1);
                ailensList.add(b);
                x += 50;
            }
            x = 0;
            y += 30;
        }
        return ailensList;
    }

    /**
     * @return the levelName
     */
    public String getLevelName() {
        return levelName + this.numLevel;
    }

    /**
     * @param levelName1 the levelName to set
     */
    public void setLevelName(String levelName1) {
        this.levelName = levelName1 + this.numLevel;
    }

    /**
     * @return the background
     */
    public Sprite getBackground() {
        return background;
    }

    /**
     * @return the numberOfBlocksToRemove
     */
    public Integer getNumberOfAliensToRemove() {
        return numberOfAliensToRemove;
    }

    /**
     * @param numberOfAliensToRemove1 the numberOfBlocksToRemove to set
     */
    public void setNumberOfAliensToRemove(Integer numberOfAliensToRemove1) {
        this.numberOfAliensToRemove = numberOfAliensToRemove1;
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
     * @return the blocksStartX
     */
    public Integer getBlocksStartX() {
        return ailensStartX;
    }

    /**
     * @param blocksStartX1 the blocksStartX to set
     */
    public void setBlocksStartX(Integer blocksStartX1) {
        this.ailensStartX = blocksStartX1;
    }

    /**
     * @return the blocksStartY
     */
    public Integer getBlocksStartY() {
        return ailensStartY;
    }

    /**
     * @param blocksStartY1 the blocksStartY to set
     */
    public void setBlocksStartY(Integer blocksStartY1) {
        this.ailensStartY = blocksStartY1;
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
    public int paddleSpeed() {
        return this.paddleSpeed();
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth();
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
    public int numberOfAliensToRemove() {
        return this.getNumberOfAliensToRemove();
    }

    @Override
    public List<Velocity> initialAliensVelocities() {
        List<Velocity> ballsList = new ArrayList<Velocity>();
        int angle = 300;
        this.velocityChange = 10;
        for (int i = 0; i < numberOfAliensToRemove(); i++) {
            ballsList.add(Velocity.fromAngleAndSpeed(angle, velocityChange));
        }
        return ballsList;

    }

}
