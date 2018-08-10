package counter;

import java.awt.Color;
import animation.Animation;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.GameLevel;
import sprites.SpriteCollection;

/**
 * A CountdownAnimation class. The CountdownAnimation will display the given
 * gameScreen, for numOfSeconds seconds, and on top of them it will show a
 * countdown from countFrom back to 1, where each number will appear on the
 * screen for (numOfSeconds / countFrom) secods, before it is replaced with the
 * next one.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private GameLevel game;
    private biuoop.Sleeper sleeper;
    private Counter leftCount;

    /**
     * a constructor to CountdownAnimation.
     * @param numOfSeconds time between numbers
     * @param countFrom the countdown from number
     * @param gameScreen a SpriteCollection
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.game = game;
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        // this.sleeper=
        this.stop = false;
        this.leftCount = new Counter(this.countFrom);

    }


@Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.gameScreen.drawAllOn(d);
        Sleeper sleep = new Sleeper();
        int i = this.leftCount.getValue() + 1;
        if (i == 0) {
            this.stop = true;
            return;
        }
        if (i != 0) {
            d.setColor(Color.yellow);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2 + 50, String.valueOf(i - 1), 70);
        }
        sleep.sleepFor(1000 / ((int) this.numOfSeconds / this.countFrom));
        this.leftCount.decrease(1);

    }

    /**
     * the method return the conditional stop.
     * @return boolean
     */
    public boolean shouldStop() {
        return this.stop;
    }

}