package sprites;

import biuoop.DrawSurface;
/**
 * A Sprite interface.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d a DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     * @param dt double
     */
    void timePassed(double dt);
}