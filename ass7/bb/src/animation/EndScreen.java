package animation;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * A EndScreen class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class EndScreen implements Animation {
    private int numOfScreen;
    private int score;
    private GUI gui;
    private boolean win;

    /**
     * a constructor to EndScreen.
     * @param num the type of message
     * @param score the num of point
     * @param gui GUI
     * @param win boolean condition
     */
    public EndScreen(int num, int score, GUI gui, boolean win) {
        this.numOfScreen = num;
        this.score = score;
        this.gui = gui;
        this.win = win;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        if (numOfScreen == 1) {
            drawOnLoser(d);
        } else if (numOfScreen == 2 && win) {
            drawOnWinner(d);
        }
    }

    /**
     * @return boolean
     */
    public boolean shouldStop() {
        return false;
    }

    /**
     * loser screen.
     * @param d DrawSurface
     */
    public void drawOnLoser(DrawSurface d) {
        d.setColor(Color.red);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.yellow);
        d.fillCircle(d.getWidth() / 2, d.getHeight() / 2, 200);

        d.setColor(Color.black);
        d.fillCircle(d.getWidth() / 2, d.getHeight() / 2 + 100, 80);
        d.setColor(Color.yellow);
        d.fillCircle(d.getWidth() / 2, d.getHeight() / 2 + 120, 80);
        d.setColor(Color.black);
        d.fillCircle(d.getWidth() / 2 - 60, d.getHeight() / 2 - 30, 20);
        d.fillCircle(d.getWidth() / 2 + 60, d.getHeight() / 2 - 30, 20);
        d.setColor(Color.blue);
        d.fillCircle(d.getWidth() / 2 - 68, d.getHeight() / 2 + 25, 8);
        d.fillCircle(d.getWidth() / 2 - 68, d.getHeight() / 2 + 5, 6);

        d.setColor(Color.black);
        d.drawText(d.getWidth() / 4 + 5, 50, "Game Over. Your score is " + score, 35);
        d.setColor(Color.yellow);
        d.drawText(d.getWidth() / 4 + 7, 50, "Game Over. Your score is " + score, 35);
        d.drawText(250, 550, "Press space to continue", 30);

    }

    /**
     * winner screen.
     * @param d DrawSurface
     */
    public void drawOnWinner(DrawSurface d) {
        d.setColor(Color.yellow);
        d.fillCircle(d.getWidth() / 2, d.getHeight() / 2, 200);

        d.setColor(Color.black);
        d.fillCircle(d.getWidth() / 2, d.getHeight() / 2 + 60, 80);
        d.setColor(Color.yellow);
        d.fillCircle(d.getWidth() / 2, d.getHeight() / 2 + 40, 80);
        d.setColor(Color.red);
        d.fillCircle(d.getWidth() / 2 - 120, d.getHeight() / 2 + 40, 30);
        d.fillCircle(d.getWidth() / 2 + 120, d.getHeight() / 2 + 40, 30);

        d.setColor(Color.black);
        d.fillCircle(d.getWidth() / 2 - 60, d.getHeight() / 2 - 30, 20);
        d.fillCircle(d.getWidth() / 2 + 60, d.getHeight() / 2 - 30, 20);
        d.setColor(Color.black);
        d.drawText(d.getWidth() / 4 + 28, 90, "You Win! Your score is " + score, 30);
        d.setColor(Color.black);

        d.drawText(d.getWidth() / 4 + 30, 90, "You Win! Your score is " + score, 30);

        d.drawText(250, 550, "Press space to continue", 30);

    }

}