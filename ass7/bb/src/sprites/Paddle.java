package sprites;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.KeyboardSensor;
import collision.BallRemover;
import collision.Collidable;
import collision.HitListener;
import collision.HitNotifier;
import collision.Velocity;
import game.GameEnvironment;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;

/**
 * A Paddle class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class Paddle implements Sprite, Collidable, HitNotifier {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private Color color;
    private int leftLimit;
    private int rightLimit;
    private double speed;
    private GameLevel g;
    private List<HitListener> hitListeners;
    private GameEnvironment gameEnvironment;
    private long lastTimeShot;
    private boolean shoot;

    /**
     * Construct a Paddle given keyboard,Rectangle,leftLimit,rightLimit and
     * color.
     * @param keyboard the KeyboardSensor
     * @param rect the Rectangle of the paddle
     * @param leftLimit the left limit of the paddle
     * @param rightLimit the right limit of the paddle
     * @param speed the paddle speed
     * @param g GameLevel
     * @param gameEnvironment GameEnvironment
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rect, int leftLimit, int rightLimit, int speed,
            GameLevel g, GameEnvironment gameEnvironment) {
        this.keyboard = keyboard;
        this.rect = rect;
        this.color = Color.orange;
        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit;
        this.speed = speed;
        this.g = g;
        this.hitListeners = new ArrayList<HitListener>();
        this.gameEnvironment = gameEnvironment;
        this.lastTimeShot = 0;
        this.shoot = false;
    }

    /**
     * move the paddle to the left side.
     * @param dt double
     */
    public void moveLeft(double dt) {
        int spd = (int) (Math.floor(this.speed * dt));
        double x = this.rect.getUpperLeft().getX();
        double y = this.rect.getUpperLeft().getY();
        Point left = new Point((x - spd), y);
        this.rect.setUpperLeft(left);
    }

    /**
     * move the paddle to the right side.
     * @param dt double
     */
    public void moveRight(double dt) {
        int spd = (int) (Math.floor(this.speed * dt));
        double x = this.rect.getUpperLeft().getX();
        double y = this.rect.getUpperLeft().getY();
        Point right = new Point((x + spd), y);
        this.rect.setUpperLeft(right);
    }

    /**
     * Sprite check if the keyboard is on.
     * @param dt double
     */
    public void timePassed(double dt) {
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            if ((this.rect.getUpperLeft().getX() + this.rect.getWidth() + 25) < this.rightLimit) {
                moveRight(dt);
            }
        } else if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            if ((this.rect.getUpperLeft().getX() - 25) > this.leftLimit) {
                moveLeft(dt);
            }
        } else {
            return;
        }
    }
/**
 * if the player key space- send a shoot.
 */
    public void shoot() {
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            if (System.currentTimeMillis() - lastTimeShot > 350) {
                this.lastTimeShot = System.currentTimeMillis();
                Ball shot = new Ball(new Point(this.rect.getUpperLeft().getX() + this.rect.getWidth() / 2,
                        this.rect.getUpperLeft().getY() - 10), 2, Color.white);
                Velocity v = Velocity.fromAngleAndSpeed(360, 4);
                shot.setVelocity(v.getDx(), v.getDy());
                shot.setGameEnvironment(gameEnvironment);
                shot.addToGame(this.g);
            }
        }
    }

    /**
     * draw the paddle on the given DrawSurface and set is color.
     * @param d an Object from biuoop
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * interface methods.
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * @return the rectangle.
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * set the rectangle.
     * @param rect1 the rectangle
     */
    public void setRect(Rectangle rect1) {
        this.rect = rect1;
    }

    /**
     * set the color.
     * @param color1 a color
     */
    public void setColor(Color color1) {
        this.color = color1;
    }

    /**
     * @param speed1 the speed1 to set
     */
    public void setSpeed1(double speed1) {
        this.speed = speed1;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity. The return is the new velocity expected after the hit (based
     * onthe force the object inflicted on us). The behavior of the ball's
     * bounce depends on where it hits the paddle.
     * @param collisionPoint a collisionPoint
     * @param currentVelocity a current Velocity
     * @param hitter ball
     * @return Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        hitter.removeFromGame(this.g);
        HitListener blr = new BallRemover(this.g);
        this.addHitListener(blr);
        this.shoot = true;
        return currentVelocity;
    }

    /**
     * Add this paddle to the game.
     * @param game Game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * remove the g from the remove From Game.
     * @param game GameLevel
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);

    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);

    }
/**
 * if the paddle get shoot-return true.
 * @return boolean
 */
    public boolean shootStatus() {
        return this.shoot;
    }
/**
 * set Shoot Status.
 */
    public void setShootStatus() {
        this.shoot = false;
    }
}