package levels;

import java.awt.Color;
import java.awt.Image;

import biuoop.DrawSurface;
import sprites.Sprite;

/**
 * A Background class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class Background implements Sprite {
    private Color color;
    private Image image;

    /**
     * @param color color
     */
    public Background(Color color) {
        this.color = color;
        this.image = null;
    }

    /**
     * @param image image
     */
    public Background(Image image) {
        this.image = image;
        this.color = null;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (this.color != null) {
            d.setColor(this.color);
            d.fillRectangle(0, 0, 800, 600);
        } else if (this.image != null) {
            d.drawImage(0, 0, this.image);
        }
    }

    @Override
    public void timePassed(double dt) {
        // TODO Auto-generated method stub

    }

}
