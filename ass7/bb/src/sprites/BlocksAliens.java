package sprites;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import biuoop.DrawSurface;
import collision.Velocity;
import game.GameEnvironment;
import game.GameLevel;
import geometry.Point;

/**
 * A BlocksAilens class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class BlocksAliens extends Block {
    private GameLevel gf;
    private double speed;
    private boolean moveright;
    private boolean finish;
    private List<Block> blocks;
    private long lastTimeShot;
    private GameEnvironment gameEnvironment;
    private List<Integer> positionsMax;
    private List<Block> endBlocks;
/**
 * constructor.
 * @param gf GameLevel
 * @param speed double
 * @param gameEnvironment GameEnvironment
 */
    public BlocksAliens(GameLevel gf, double speed, GameEnvironment gameEnvironment) {
        this.gf = gf;
        this.speed = speed; // * ((double) 1 / (double) 2);
        this.finish = false;
        this.blocks = new ArrayList<Block>();
        this.moveright = true;
        this.lastTimeShot = 0;
        this.gameEnvironment = gameEnvironment;
        this.positionsMax = new ArrayList<Integer>();
        this.endBlocks = new ArrayList<Block>();
    }

    @Override
    public void drawOn(DrawSurface d) {
    }

    @Override
    public void timePassed(double dt) {
        move();
        // updatePlace();
        checkStstus();

        shootRandom(gf);
    }
/**
 * shoot Random.
 * @param g GameLevel
 */
    public void shootRandom(GameLevel g) {
        if (System.currentTimeMillis() - lastTimeShot > 500) {
            Random random = new Random();
            if (this.blocks.size() != 0) {
                Block shooterIndex = null;
                do {
                    int place = random.nextInt(endBlocks.size());
                    shooterIndex = endBlocks.get(place);
                } while (shooterIndex == null);
                Ball shot = new Ball((int) shooterIndex.getUpperLeft().getX() + shooterIndex.getWidth() / 2,
                        (int) shooterIndex.getUpperLeft().getY() + shooterIndex.getHeigth() + 10, 4,
                        Color.red);
                Velocity v = Velocity.fromAngleAndSpeed(360, -4);
                shot.setVelocity(v.getDx(), v.getDy());
                shot.setGameEnvironment(gameEnvironment);
                shot.addToGame(g);
                this.lastTimeShot = System.currentTimeMillis();
            }
        }
    }
/**
 * update Place of Aliens when they come to the limit.
 */
    public void updatePlace() {
        for (Block block : blocks) {
            double x = block.getUpperLeft().getX();
            double y = block.getUpperLeft().getY();
            block.setUpperLeft((int) x, (int) y + 30);
        }
    }
/**
 * update Status Of Hit Of Alien.
 * @param b Block
 */
    public void updateStatusOfHitOfAlien(Block b) {
        b.setHitbyshot(true);
    }
/**
 * restsrt() the update place of the aliens.
 */
    public void restsrt() {
        for (int t = 0; t < blocks.size(); t++) {
            int col = this.blocks.get(t).getPosition() % 10;
            int row = (this.blocks.get(t).getPosition()) / 10;
            Point p = new Point(20 + col * 50, 40 + row * 40);
            blocks.get(t).setUpperLeft(p.getX(), p.getY());
        }
    }
/**
 * move the aliens and change is velocity according to the limits.
 */
    public void move() {
        if (finish || this.blocks.size() == 0) {
            this.finish = false;
            gf.comeToShildes();
        } else if (moveright) {
            for (Block block : blocks) {
                if (block.getUpperLeft().getX() + block.getWidth() >= 800) {
                    this.moveright = false;
                    this.speed *= 1.1;
                    updatePlace();
                    break;
                }
                if (block.getUpperLeft().getY() + block.getHeigth() >= 470) {
                    this.finish = true;
                }
                int spd = (int) (Math.floor(this.speed));
                double x = block.getUpperLeft().getX();
                double y = block.getUpperLeft().getY();
                block.setUpperLeft((int) (x + spd), (int) y);
            }
        } else {
            for (Block block : blocks) {
                if (block.getUpperLeft().getX() <= 0) {
                    this.moveright = true;
                    this.speed *= 1.1;
                    updatePlace();
                    break;
                }
                if (block.getUpperLeft().getY() + block.getHeigth() >= 470) {
                    this.finish = true;
                }
                int spd = (int) (Math.floor(this.speed));
                double x = block.getUpperLeft().getX();
                double y = block.getUpperLeft().getY();
                block.setUpperLeft((int) (x - spd), (int) y);
            }
        }
    }

    /**
     * @param speed1 the speed to set
     */
    public void setSpeed(double speed1) {
        this.speed = speed1;
    }
/**
 * checkStstus()=update list with the max block in the col.
 */
    public void checkStstus() {
        positionsMax.clear();
        endBlocks.clear();
        for (int i = 0; i < 10; i++) {
            int maxNum = 0;
            int maxPos = 0;
            Block temp = null;
            for (Block block : blocks) {
                if (block.getPosition() % 10 == i) {
                    if (block.getPosition() / 10 >= maxNum) {
                        maxPos = block.getPosition();
                        temp = block;
                    }
                }
            }
            positionsMax.add(maxPos);
            endBlocks.add(temp);
        }
    }

/**
 * hitEvent.
 * @param beingHit Block
 * @param hitter Ball
 */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("inn");
        this.blocks.remove(beingHit);
        super.removeFromGame(gf);
    }
/**
 * remove Block from list.
 * @param beingHit Block
 */
    public void removeBlock(Block beingHit) {
        this.blocks.remove(beingHit);

    }
/**
 * add Block to list.
 * @param copyBlock Block
 */
    public void addBlock(Block copyBlock) {
        this.blocks.add(copyBlock);
    }

    /*public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }*/

    /**
     * @return the blocks
     */
    public List<Block> getBlocks() {
        return blocks;
    }
}
