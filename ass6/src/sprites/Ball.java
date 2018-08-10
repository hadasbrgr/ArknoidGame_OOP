package sprites;

import biuoop.DrawSurface;
import collision.CollisionInfo;
import collision.HitListener;
import collision.Velocity;
import game.GameEnvironment;
import game.GameLevel;
import geometry.Line;
import geometry.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A Ball class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class Ball implements Sprite {
    private int radius;
    private java.awt.Color color;
    private Point center;
    private Velocity vel;
    private int dimStartX;
    private int dimStartY;
    private int dimEndX;
    private int dimEndY;
    private GameEnvironment gameEnvironment;
    private List<HitListener> hitListeners;

    /**
     * Construct a Ball given center,radius and color.
     * @param center the point coordinate
     * @param r the radius length
     * @param color the color
     */
    public Ball(Point center, int r, Color color) {
        this.radius = r;
        this.color = color;
        this.center = center;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Construct a ball given x and y coordinates,radius,color,dim point 1 and
     * dim point 2.
     * @param p the point of center
     * @param r the radius length
     * @param color the color
     * @param x1 the dimSrartX
     * @param y1 the dimStartY
     * @param x2 the dimEndX
     * @param y2 the dimEndY
     */
    public Ball(Point p, int r, Color color, int x1, int y1, int x2, int y2) {
        this.center = p;
        this.radius = r;
        this.color = color;
        this.dimStartX = x1;
        this.dimStartY = y1;
        this.dimEndX = x2;
        this.dimEndY = y2;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Construct a ball given x and y coordinates and radius and color.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param r the radius length
     * @param color the color
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.radius = r;
        this.color = color;
        this.center = new Point(x, y);
        this.hitListeners = new ArrayList<HitListener>();

    }

    /**
     * @return the x coordinate
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return the y coordinate
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the radius size
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return the color ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @return the dimSrartX coordinate
     */
    public int getDimSrartX() {
        return this.dimStartX;
    }

    /**
     * @return the dimEndY coordinate
     */
    public int getDimEndY() {
        return this.dimEndY;
    }

    /**
     * @return the dimEndX coordinate
     */
    public int getDimEndX() {
        return this.dimEndX;
    }

    /**
     * @return the dimStartY coordinate
     */
    public int getDimStartY() {
        return this.dimStartY;
    }

    /**
     * @return the Velocity
     */
    public Velocity getVelocity() {
        return this.vel;
    }

    /**
     * set the velocity.
     * @param vel1 an Velocity
     */
    public void setVelocity(Velocity vel1) {
        this.vel.setDx(vel1.getDx());
        this.vel.setDy(vel1.getDy());
    }

    /**
     * set velocity.
     * @param vel1 velocity
     * @param dt double
     */
    public void setVelocity(Velocity vel1, double dt) {
        this.vel.setDx(vel1.getDx() * dt);
        this.vel.setDy(vel1.getDy() * dt);
    }

    /**
     * set the velocity.
     * @param dx an Velocity coordinate
     * @param dy an Velocity coordinate
     */
    public void setVelocity(double dx, double dy) {
        this.vel = new Velocity(dx, dy);
    }

    /**
     * draw the ball on the given DrawSurface.
     * @param surface an Object from biuoop
     */
    public void drawOn(DrawSurface surface) {
        // surface.drawCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.radius);

    }

    /**
     * move the ball one step.The ball does not go outside of the screen -- when
     * it hits the border to the left or to the right, it change its horizontal
     * direction, and when it hits the border on the top or the bottom, it
     * change its vertical direction.
     * @param dt double
     */
    public void moveOneStep(double dt) {
        Velocity velo = new Velocity(this.vel.getDx(), this.vel.getDy());
        Point pointAfterStep = velo.applyToPoint(this.center, dt);
        Line trajectory = new Line(Math.round(this.center.getX()), Math.round(this.center.getY()),
                Math.round(pointAfterStep.getX()), Math.round(pointAfterStep.getY()));
        if (this.gameEnvironment.getClosestCollision(trajectory) != null) {
            CollisionInfo closeColl = this.gameEnvironment.getClosestCollision(trajectory);
            this.setVelocity(closeColl.collisionObject().hit(this, closeColl.collisionPoint(), this.vel));
        } else {
            this.center = trajectory.end();
        }
    }

    /**
     * set the boundary of the ball.
     * @param dimStartX1 x point start
     * @param dimStartY1 y point start
     * @param dimEndX1 x point end
     * @param dimEndY1 y point end
     */
    public void setBoundary(int dimStartX1, int dimStartY1, int dimEndX1, int dimEndY1) {
        this.dimStartX = dimStartX1;
        this.dimStartY = dimStartY1;
        this.dimEndX = dimEndX1;
        this.dimEndY = dimEndY1;
    }

    /**
     * set the GameEnvironment.
     * @param gameEnvironment1 GameEnvironment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment1) {
        this.gameEnvironment = gameEnvironment1;
    }

    /**
     * change the move of the ball.
     * @param dt double
     */
    public void timePassed(double dt) {
        moveOneStep(dt);
    }

    /**
     * add the g to the list of sprite.
     * @param g Game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * remove the g from the list of sprite.
     * @param g Game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

}
