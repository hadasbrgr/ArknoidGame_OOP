package sprites;

import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * A SpriteCollection class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class SpriteCollection {
    private List<Sprite> spriteCollectionList;

    /**
     * Construct a SpriteCollection.
     */
    public SpriteCollection() {
        this.spriteCollectionList = new ArrayList<Sprite>();
    }

    /**
     * add the given Sprite to the SpriteCollectionList.
     * @param s Sprite
     */
    public void addSprite(Sprite s) {
        this.spriteCollectionList.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copy = new ArrayList<Sprite>(this.spriteCollectionList);
        for (Sprite sprite : copy) {
            double dt = ((double) 1 / (double) 60);
            sprite.timePassed(dt);
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d a DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteCollectionList) {
            sprite.drawOn(d);
        }
    }

    /**
     * get Sprite Collection List.
     * @return List<Sprite>
     */
    public List<Sprite> getSpriteCollectionList() {
        return spriteCollectionList;
    }

    /**
     * set Sprite Collection List.
     * @param spriteColRlectionList1 a List<Sprite>
     */
    public void setSpriteCollectionList(List<Sprite> spriteColRlectionList1) {
        this.spriteCollectionList = spriteColRlectionList1;
    }

    /**
     * remove the s from the list of Sprite.
     * @param s Sprite
     */
    public void removeSprite(Sprite s) {
        this.spriteCollectionList.remove(s);
    }
}