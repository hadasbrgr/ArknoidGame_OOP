package sprites;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import biuoop.DrawSurface;
import collision.Collidable;
import collision.HitListener;
import collision.HitNotifier;
import collision.Velocity;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;

/**
 * A Block class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // members
    private Rectangle rect;
    private Color colorBlock;
    private Color colorStroke;
    private int numOfHit;
    private Map<Integer, Color> colors;
    private Map<Integer, Image> images;
    private Color borderColor;
    private boolean hasBorder;
    private List<HitListener> hitListeners;
/**
 * constructor defult.
 */
    public Block() {
        this.rect = new Rectangle(new Point(0, 0), 1, 1);
        this.colors = new TreeMap<Integer, Color>();
        this.images = new TreeMap<Integer, Image>();
        this.colorStroke = null;
        this.hasBorder = false;
        this.numOfHit = 0;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * @param rect rectangle
     * @param colorBlock color
     * @param colorStroke color
     * @param numOfHit int
     */
    public Block(Rectangle rect, Color colorBlock, Color colorStroke, int numOfHit) {
        super();
        this.rect = rect;
        this.colorBlock = colorBlock;
        this.colorStroke = null;
        this.numOfHit = numOfHit;
    }

    /**
     * Construct a Block given rectangle,color and num of hits.
     * @param rect the rectangle of the block
     * @param color the color of the block
     * @param numOfHit the number of the hits in the block
     */
    public Block(Rectangle rect, java.awt.Color color, int numOfHit) {
        this.rect = rect;
        this.colorBlock = color;
        this.numOfHit = numOfHit;
        this.hitListeners = new ArrayList<HitListener>();
        this.colorStroke = null;
    }

    /**
     * Construct a Block given rectangle,color and num of hits.
     * @param rect the rectangle of the block
     * @param color the color of the block
     */
    public Block(Rectangle rect, java.awt.Color color) {
        this.rect = rect;
        this.colorBlock = color;
        this.hitListeners = new ArrayList<HitListener>();
        this.colorStroke = null;

    }
/**
 * @param rect2 rectangle
 * @param fillBlockColor map
 * @param fillBlockImage map
 * @param strokeColor color
 * @param hitPoints int
 */
    public Block(Rectangle rect2, Map<Integer, Color> fillBlockColor, Map<Integer, Image> fillBlockImage,
            Color strokeColor, int hitPoints) {
        this.rect = rect2;
        this.colors = fillBlockColor;
        this.images = fillBlockImage;
        this.colorStroke = strokeColor;
        this.numOfHit = hitPoints;
        this.hitListeners = new ArrayList<HitListener>();
    }
/**
 * constructor from other block.
 * @param other block
 */
    public Block(Block other) {
        this.rect = other.rect;
        this.colors = other.colors;
        this.images = other.images;
        this.colorStroke = other.colorStroke;
        this.numOfHit = other.numOfHit;
        this.hitListeners = other.hitListeners;
    }

    /**
     * set the color.
     * @param color1 a color
     */
    public void setColor(Color color1) {
        this.colorBlock = color1;
    }

    /**
     * @return the color ball
     */
    public Color getColor() {
        return colorBlock;
    }

    /**
     * interface methods.
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity. The return is the new velocity expected after the hit (based
     * onthe force the object inflicted on us).
     * @param collisionPoint a collisionPoint
     * @param currentVelocity a current Velocity
     * @param hitter ball
     * @return Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        setNumOfHit((this.numOfHit) - 1);
        this.notifyHit(hitter);

        // ocures hit
        if (this.rect.upperLine().pointFindOnLine(collisionPoint)
                || this.rect.lowerLine().pointFindOnLine(collisionPoint)
                || (collisionPoint == this.rect.leftLowerPoint()) || (collisionPoint == this.rect.rightLowerPoint())) {
            return new Velocity(dx, -dy);
        } else if (this.rect.leftSideLine().pointFindOnLine(collisionPoint)
                || this.rect.rightSideLine().pointFindOnLine(collisionPoint)
                || (collisionPoint == this.rect.getUpperLeft()) || (collisionPoint == this.rect.rightUpperPoint())) {
            return new Velocity(-dx, dy);
        } else {
            return new Velocity(-dx, -dy);
        }
    }

    /**
     * @return the number of hits.
     */
    public int getNumOfHit() {
        return numOfHit;
    }
/**
 * copy block.
 * @return block
 */
    public Block copyBlock() {
        return new Block(this);
    }

    /**
     * set the num of hits.
     * @param numOfHit1 the number of hits in the block
     */
    public void setNumOfHit(int numOfHit1) {
        this.numOfHit = numOfHit1;
    }

    /**
     * draw the block on the given DrawSurface.
     * @param surface an Object from biuoop
     */
    public void drawOn(DrawSurface surface) {
        if (this.colorBlock != null) {
            surface.setColor(colorBlock);
            surface.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                    (int) this.rect.getWidth(), (int) this.rect.getHeight());
        }

        if (images != null) {
            if (images.containsKey((numOfHit))) {
                surface.drawImage((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                        images.get(numOfHit));

            }
        }
        if (colors != null) {
            String string = Integer.toString(this.numOfHit);
            if (colors.containsKey((numOfHit))) {
                surface.setColor(colors.get(numOfHit));
                surface.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                        (int) this.rect.getWidth(), (int) this.rect.getHeight());
            }
        }

        if (this.colorStroke != null) {
            surface.setColor(colorStroke);
            surface.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                    (int) this.rect.getWidth(), (int) this.rect.getHeight());
        }
    }

    /**
     * change the color of the block.
     * @param dt double
     */
    public void timePassed(double dt) {
        // Random rand=new Random();
        // this.color=new Color(rand.nextInt());
        // this.color=Color.BLUE;
    }

    /**
     * add the g to the list of sprite and to the list of collidable.
     * @param g Game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * remove the g from the list of sprite and to the list of collidable.
     * @param game Game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * Notify all listeners about a hit event.
     * @param hitter Ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
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
 * @return block width
 */
    public int getWidth() {
        return (int) this.rect.getWidth();
    }
/**
 * @return block heigth
 */
    public int getHeigth() {
        return (int) this.rect.getHeight();
    }

    /**
     * @param rectwidth rect the rect to set
     */
    public void setRectwidth(int rectwidth) {
        this.rect.setWidth(rectwidth);
    }
/**
 * @param rectheight int
 */
    public void setRectHeight(int rectheight) {
        this.rect.setHeight(rectheight);
        ;
    }

}
