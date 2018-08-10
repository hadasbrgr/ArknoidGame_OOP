package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import animation.Animation;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
//import java.util.ArrayList;
//import java.util.List;
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
import levels.LevelName;
import sprites.Ball;
import sprites.Block;
import sprites.LivesIndicator;
import sprites.Paddle;
import sprites.ScoreIndicator;
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

    /**
     * Construct a GameLevel.
     * @param level LevelInformation
     * @param gui GUI
     * @param keyboard KeyboardSensor
     * @param score the score
     * @param numberOfLives the number of lives
     * @param ar AnimationRunner
     */
    public GameLevel(LevelInformation level, GUI gui, KeyboardSensor keyboard, Counter score, Counter numberOfLives,
            AnimationRunner ar) {
        this.sprites = new SpriteCollection();
        this.gameEnvironment = new GameEnvironment();
        this.heightFrame = 600;
        this.widthFrame = 800;
        this.gui = gui;
        this.level = level;
        this.counterBlocks = new Counter(this.level.numberOfBlocksToRemove());
        this.counterBalls = new Counter(0);
        this.score = score;
        this.numberOfLives = numberOfLives;
        this.running = false;
        this.runner = ar;
        this.keyboard = keyboard;
    }

    /**
     * @return the counterBlocks.
     */
    public Counter getCounterBlocks() {
        return counterBlocks;
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
        Block death = new Block(new Rectangle(new Point(25, this.heightFrame), this.widthFrame - 35, 25), Color.gray);
        death.addToGame(this);
        BallRemover b = new BallRemover(this, counterBalls);
        death.addHitListener(b);

        createBoundaryBlobks();
        createBlocks();

        ScoreIndicator scorePrint = new ScoreIndicator(this, score);
        scorePrint.addToGame(this);

        LivesIndicator livesPrint = new LivesIndicator(this, numberOfLives);
        livesPrint.addToGame(this);

        LevelName levelPrint = new LevelName(this.level.levelName());
        levelPrint.addToGame(this);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {
        createBalls();
        createPaddle();
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(3, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * the method create the ball and add him to the game.
     */
    public void createBalls() {
        int numOfBall = this.level.numberOfBalls();
        List<Ball> ballList = new ArrayList<Ball>();
        List<Velocity> velocityBallList = this.level.initialBallVelocities();
        for (int i = 0; i < numOfBall; i++) {
            ballList.add(new Ball(this.widthFrame / 2, (int) (this.heightFrame - 60), 6, Color.white));
            ballList.get(i).setVelocity(velocityBallList.get(i).getDx(), velocityBallList.get(i).getDy());
            ballList.get(i).setGameEnvironment(this.gameEnvironment);
            ballList.get(i).addToGame(this);
            this.counterBalls.increase(1);
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
        // System.out.println("number block
        // remove"+this.level.numberOfBlocksToRemove());
        int numOfBlocks = this.level.numberOfBlocksToRemove();
        List<Block> blocksList = this.level.blocks();
        List<Block> copyList = new ArrayList<Block>();
        for (int i = 0; i < this.level.blocks().size(); i++) {
            copyList.add(this.level.blocks().get(i).copyBlock());
        }
        /*
         * for (Block block : blocksList) { copyList.add(block); }
         */
        for (Block b : copyList) {
            HitListener listener = new BlockRemover(this, counterBlocks);
            b.addHitListener(listener);
            HitListener scoreListener = new ScoreTrackingListener(score);
            b.addHitListener(scoreListener);
            // than adding it to the game
            b.addToGame(this);
        }
    }

    /**
     * the method create the paddle and add him to the game.
     */
    public void createPaddle() {
        int paddleWidth = this.level.paddleWidth();
        int paddleHeight = 25;
        int pointPaddleX = this.widthFrame / 2 - (paddleWidth / 2);

        int pointPaddleY = this.heightFrame - 2 * 25;
        Paddle paddle = new Paddle(this.keyboard,
                new Rectangle(new Point(pointPaddleX, pointPaddleY), paddleWidth, paddleHeight), Color.orange, 0,
                this.widthFrame, this.level.paddleSpeed());
        paddle.addToGame(this);
        this.p = paddle;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (counterBlocks.getValue() == 0) {
            this.score.increase(100);
            p.removeFromGame(this);
            this.running = false;
        }

        if (counterBalls.getValue() == 0 && counterBlocks.getValue() != 0) {
            p.removeFromGame(this);
            this.running = false;
            this.numberOfLives.decrease(1);
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

}