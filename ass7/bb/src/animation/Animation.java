package animation;

import biuoop.DrawSurface;

/**
 * An interface Animation.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31
 */
public interface Animation {
    /**
     * @param d DrawSurface
     * @param dt double
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * @return boolean
     */
    boolean shouldStop();
}