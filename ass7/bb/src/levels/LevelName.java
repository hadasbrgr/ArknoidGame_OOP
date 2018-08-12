package levels;

import java.awt.Color;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

/**
 * A LevelName class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class LevelName implements Sprite {
    private String levelName;
    private Rectangle rect;

    /**
     * a constructor to LevelName.
     * @param levelName String
     */
    public LevelName(String levelName) {
        this.levelName = levelName;
        this.rect = new Rectangle(new Point(500, 0), 300, 25);
    }

    @Override
    public void drawOn(DrawSurface d) {
        String string;
        d.setColor(Color.WHITE);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.black);

        string = "Level Name: " + levelName;

        d.drawText((int) (this.rect.getUpperLeft().getX() + ((-60) + this.rect.getWidth()) / 4),
                (int) (this.rect.getUpperLeft().getY() + (2 + this.rect.getHeight()) / 2), string, 15);
    }

    @Override
    public void timePassed(double dt) {
        return;
    }

    /**
     * add the g to the list of sprite.
     * @param g Game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
