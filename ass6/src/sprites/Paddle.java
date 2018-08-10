package sprites;

import java.awt.Color;

import biuoop.KeyboardSensor;
import collision.Collidable;
import collision.Velocity;
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
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private Color color;
    private int leftLimit;
    private int rightLimit;
    private double speed1;

    /**
     * Construct a Paddle given keyboard,Rectangle,leftLimit,rightLimit and
     * color.
     * @param keyboard the KeyboardSensor
     * @param rect the Rectangle of the paddle
     * @param color the color
     * @param leftLimit the left limit of the paddle
     * @param rightLimit the right limit of the paddle
     * @param speed the paddle speed
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rect, Color color, int leftLimit, int rightLimit,
            int speed) {
        this.keyboard = keyboard;
        this.rect = rect;
        this.color = color;
        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit;
        this.speed1 = speed;
    }

    /**
     * move the paddle to the left side.
     * @param dt double
     */
    public void moveLeft(double dt) {
        int spd = (int) (Math.floor(this.speed1 * dt));
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
        int spd = (int) (Math.floor(this.speed1 * dt));
        double x = this.rect.getUpperLeft().getX();
        double y = this.rect.getUpperLeft().getY();
        Point right = new Point((x + spd), y);
        this.rect.setUpperLeft(right);
    }

    /**
     * move the paddle up.
     */
    public void moveUp() {
        double x = this.rect.getUpperLeft().getX();
        double y = this.rect.getUpperLeft().getY();
        // if ((x + 10) < this.rightLimit) {
        Point up = new Point(x, y - 10);
        this.rect.setUpperLeft(up);
    }

    /**
     * move the paddle down.
     */
    public void moveDown() {
        double x = this.rect.getUpperLeft().getX();
        double y = this.rect.getUpperLeft().getY();
        // if ((x + 10) < this.rightLimit) {
        Point down = new Point(x, y + 10);
        this.rect.setUpperLeft(down);
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
     * @param speed1 the speed1 to set
     */
    /*
     * public void setSpeed1(int speed1) { this.speed1 = speed1; }
     */

    /**
     * draw the paddle on the given DrawSurface and set is color.
     * @param d an Object from biuoop
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        /*
         * d.drawLine((int)this.rect.getUpperLeft().getX(),
         * (int)this.rect.getUpperLeft().getY(),
         * (int)(this.rect.getUpperLeft().getX()+this.rect.getWidth()),
         * (int)this.rect.getUpperLeft().getY());
         */
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
        double angle = (double) Math.toDegrees(Math.atan2(currentVelocity.getDy(), currentVelocity.getDx()));
        double speed = Math.sqrt(
                currentVelocity.getDx() * currentVelocity.getDx() + currentVelocity.getDy() * currentVelocity.getDy());
        // collision with the Corners.
        if (collisionPoint.equals(rect.getUpperLeft()) || collisionPoint.equals(rect.rightUpperPoint())
                || collisionPoint.equals(rect.leftLowerPoint()) || collisionPoint.equals(rect.rightLowerPoint())) {
            System.out.println("in point o  ");
            return new Velocity(-1 * (currentVelocity.getDx()), -1 * (currentVelocity.getDy()));
        }
        // set the velocity according to the collision point hit
        if (rect.upperLine().pointFindOnLine(collisionPoint)) {
            if (collisionPoint.getX() <= rect.getUpperLeft().getX() + (this.rect.getWidth() / 5)) {
                return Velocity.fromAngleAndSpeed(300, speed);
            } else if (collisionPoint.getX() <= rect.getUpperLeft().getX() + 2 * (this.rect.getWidth() / 5)) {
                return Velocity.fromAngleAndSpeed(330, speed);
            } else if (collisionPoint.getX() <= rect.getUpperLeft().getX() + 3 * (this.rect.getWidth() / 5)) {
                return Velocity.fromAngleAndSpeed(360, speed);
            } else if (collisionPoint.getX() <= rect.getUpperLeft().getX() + 4 * (this.rect.getWidth() / 5)) {
                return Velocity.fromAngleAndSpeed(30, speed);
            } else {
                return Velocity.fromAngleAndSpeed(60, speed);
            }
            // collision with the sides
        } else if (rect.leftSideLine().pointFindOnLine(collisionPoint)
                || rect.rightSideLine().pointFindOnLine(collisionPoint)) {
            angle = (double) Math.toDegrees(Math.atan2(currentVelocity.getDy(), -currentVelocity.getDx()));
            return Velocity.fromAngleAndSpeed(angle, speed);
        } else {
            return Velocity.fromAngleAndSpeed(angle, speed);
        }
    }

    /**
     * Add this paddle to the game.
     * @param g Game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * remove the g from the remove From Game.
     * @param game GameLevel
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }
}