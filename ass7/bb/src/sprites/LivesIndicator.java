package sprites;

import java.awt.Color;

import biuoop.DrawSurface;
import counter.Counter;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;

/**
 * A LivesIndicator class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public class LivesIndicator implements Sprite {
    private Counter currentScore;
    private Rectangle rect;

    /**
     * a constructor to LivesIndicator.
     * @param currentScore Counter
     */
    public LivesIndicator(Counter currentScore) {
        this.currentScore = currentScore;
        this.rect = new Rectangle(new Point(0, 0), 270, 25);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        String string;
        surface.setColor(Color.WHITE);
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        surface.setColor(Color.black);
        string = "Lives: " + Integer.toString(this.currentScore.getValue());
        surface.drawText((int) (this.rect.getUpperLeft().getX() + ((-2) + this.rect.getWidth()) / 2),
                (int) (this.rect.getUpperLeft().getY() + (2 + this.rect.getHeight()) / 2), string, 15);

    }

    @Override
    public void timePassed(double dt) {
    }

    /**
     * add the g to the list of sprite.
     * @param g Game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
