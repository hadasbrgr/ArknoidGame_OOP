package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import animation.Animation;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import collision.BallRemover;
import collision.BlockRemover;
import collision.Collidable;
import collision.HitListener;
import collision.Velocity;
import counter.CountdownAnimation;
import counter.Counter;
import counter.ScoreTrackingListener;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import sprites.Ball;
import sprites.Block;
import sprites.BlocksAliens;
import sprites.Paddle;
import sprites.Sprite;
import sprites.SpriteCollection;

/**
 * A GameLevel class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment gameEnvironment;
    private GUI gui;
    private biuoop.Sleeper sleeper;
    private int widthFrame;
    private int heightFrame;
    private Counter counterBlocks;
    private Counter counterBalls;
    private Counter score;
    private Counter numberOfLives;
    private AnimationRunner runner;
    private boolean running;
    private Paddle p;
    private KeyboardSensor keyboard;
    private LevelInformation level;
    private double speedOfAilens;
    private Counter paddleCounter;
    private BlocksAliens enemy;
    private boolean finish;
    private List<Block> blocks;

    /**
     * Construct a GameLevel.
     * @param level LevelInformation
     * @param gui GUI
     * @param keyboard KeyboardSensor
     * @param score the score
     * @param numberOfLives the number of lives
     * @param ar AnimationRunner
     * @param speedOfAilens speedOfAilens
     */
    public GameLevel(LevelInformation level, GUI gui, KeyboardSensor keyboard, Counter score, Counter numberOfLives,
            AnimationRunner ar, double speedOfAilens) {
        this.sprites = new SpriteCollection();
        this.gameEnvironment = new GameEnvironment();
        this.heightFrame = 600;
        this.widthFrame = 800;
        this.gui = gui;
        this.level = level;
        this.counterBlocks = new Counter(this.level.numberOfAliensToRemove());
        this.counterBalls = new Counter(0);
        this.score = score;
        this.numberOfLives = numberOfLives;
        this.running = false;
        this.runner = ar;
        this.keyboard = keyboard;
        this.speedOfAilens = speedOfAilens;
        this.paddleCounter = null;
        this.enemy = new BlocksAliens(this, speedOfAilens, gameEnvironment);
        this.finish = false;
        this.blocks = new ArrayList<Block>();
        int paddleWidth = this.level.paddleWidth();
        int paddleHeight = 25;
        int pointPaddleX = this.widthFrame / 2 - (paddleWidth / 2);
        int pointPaddleY = this.heightFrame - 2 * 25;
        this.p = new Paddle(this.keyboard,
                new Rectangle(new Point(pointPaddleX, pointPaddleY),
                        paddleWidth, paddleHeight), 0,
                this.widthFrame, this.level.paddleSpeed(), this, this.gameEnvironment);
    }
/**
 * removeEnemy.
 * @param b Block
 */
    public void removeEnemy(Block b) {
        this.enemy.removeBlock(b);
    }

    /**
     * @return the counterBlocks.
     */
    public Counter getCounterBlocks() {
        return counterBlocks;
    }
/**
 * isWin().
 * @return isWin()
 */
    public boolean isWin() {
        return this.enemy.getBlocks().size() == 0;
    }

    /**
     * @return the width Frame
     */
    public int getWidthFrame() {
        return widthFrame;
    }

    /**
     * @return the height Frame
     */
    public int getHeightFrame() {
        return heightFrame;
    }

    /**
     * add a Collidable.
     * @param c a Collidable
     */
    public void addCollidable(Collidable c) {
        this.gameEnvironment.addCollidable(c);
    }

    /**
     * remove a Collidable.
     * @param c a Collidable
     */
    public void removeCollidable(Collidable c) {
        this.gameEnvironment.removeCollidable(c);
    }

    /**
     * add a Sprite.
     * @param s a Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove a Sprite.
     * @param s a Sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add
     * them to the game.
     */
    public void initialize() {
        addSprite(this.level.getBackground());
        this.sleeper = new Sleeper();
        // create the objects
        Block death = new Block(new Rectangle(new Point(25, this.heightFrame), this.widthFrame - 35, 25), Color.red);
        Block deathup = new Block(new Rectangle(new Point(0, 0), this.widthFrame, 25), Color.red);
        deathup.addToGame(this);
        death.addToGame(this);
        HitListener b = new BallRemover(this);
        death.addHitListener(b);
        deathup.addHitListener(b);
        createShield();
        createBlocks();
    }
/**
 * create Shield.
 */
    public void createShield() {
        Block[][] leftShield = new Block[3][30];
        Block[][] middleShield = new Block[3][30];
        Block[][] rightShield = new Block[3][30];
        HitListener listener = new BlockRemover(this);
        HitListener blr = new BallRemover(this);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 30; j++) {
                leftShield[i][j] = new Block();
                Map<Integer, Color> shieldColor = new TreeMap<Integer, Color>();
                shieldColor.put(1, Color.CYAN);
                leftShield[i][j].setColor(shieldColor.get(1));
                leftShield[i][j].setNumOfHit(1);
                leftShield[i][j].setRectwidth(5);
                leftShield[i][j].setRectHeight(5);
                leftShield[i][j].setUpperLeft(100 + j * 5, 480 + i * 5);
                leftShield[i][j].addHitListener(listener);
                leftShield[i][j].addHitListener(blr);
                leftShield[i][j].addToGame(this);

                middleShield[i][j] = new Block();
                middleShield[i][j].setColor(shieldColor.get(1));
                middleShield[i][j].setNumOfHit(1);
                middleShield[i][j].setRectwidth(5);
                middleShield[i][j].setRectHeight(5);
                middleShield[i][j].setUpperLeft(320 + j * 5, 480 + i * 5);
                middleShield[i][j].addHitListener(listener);
                middleShield[i][j].addHitListener(blr);
                middleShield[i][j].addToGame(this);

                rightShield[i][j] = new Block();
                rightShield[i][j].setColor(shieldColor.get(1));
                rightShield[i][j].setNumOfHit(1);
                rightShield[i][j].setRectwidth(5);
                rightShield[i][j].setRectHeight(5);
                rightShield[i][j].setUpperLeft(540 + j * 5, 480 + i * 5);
                rightShield[i][j].addHitListener(listener);
                rightShield[i][j].addHitListener(blr);
                rightShield[i][j].addToGame(this);
            }
        }
    }

    /**
     * the method create the blocks boundary and add them to the game.
     */
    public void createBoundaryBlobks() {
        Block upperBlock = new Block(new Rectangle(new Point(0, 0), this.widthFrame, 50), Color.black);
        Block leftBlock = new Block(new Rectangle(new Point(0, 50), 20, this.heightFrame - 35), Color.black);
        Block rightBlock = new Block(new Rectangle(new Point(this.widthFrame - 20, 50), 20, this.heightFrame - 35),
                Color.black);
        upperBlock.addToGame(this);
        leftBlock.addToGame(this);
        rightBlock.addToGame(this);
    }

    /**
     * the method create the blocks and add them to the game. (update the number
     * of hits accordingly their location.) the boundary according to the limits
     * of the gui.
     */
    public void createBlocks() {
        for (Block block : this.level.blocks()) {
            this.enemy.addBlock(block);
        }
        this.sprites.addSprite(enemy);
        HitListener blr = new BallRemover(this);
        for (Block b : enemy.getBlocks()) {
            HitListener listener = new BlockRemover(this, counterBlocks);
            b.addHitListener(listener);
            HitListener scoreListener = new ScoreTrackingListener(score);
            b.addHitListener(scoreListener);
            b.addHitListener(blr);
            // than adding it to the game
            b.addToGame(this);
        }
    }

    /**
     * the method create the paddle and add him to the game.
     * @param g GameLevel
     */
    public void createPaddle(GameLevel g) {
        this.paddleCounter = new Counter(1);
        HitListener pblr = new BallRemover(this);
        this.p.addHitListener(pblr);
        this.p.addToGame(this);
    }
/**
 * createBalls.
 */
    public void createBalls() {
        Ball b = new Ball(this.widthFrame / 2, (int) (this.heightFrame - 60), 6, Color.white);
        Velocity v = Velocity.fromAngleAndSpeed(360, 4);
        b.setVelocity(v.getDx(), v.getDy());
        b.setGameEnvironment(this.gameEnvironment);
        b.addToGame(this);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {
        this.enemy.setSpeed(speedOfAilens);
        createPaddle(this);
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(3, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        this.p.shoot();
        if (this.enemy.getBlocks().size() == 0) {
            this.score.increase(100);
            p.removeFromGame(this);
            this.running = false;
            this.isWin();
        }
        if (this.p.shootStatus()) {
            this.finish = true;
            this.p.setShootStatus();
        }
        if (this.finish) {
            p.removeFromGame(this);
            this.running = false;
            this.enemy.restsrt();
            this.finish = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(
                    new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen(this.keyboard)));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
/**
 * comeToShildes().
 */
    public void comeToShildes() {
        this.finish = true;
    }
}