package animation;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.HighScoresTable;

/**
 * A HighScoresAnimation class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class HighScoresAnimation implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private HighScoresTable scores;
    private String endKey;
    private GUI gui;

    /**
     * constructor HighScoresAnimation.
     * @param scores HighScoresTable
     * @param endKey String to press
     * @param keyboard KeyboardSensor
     * @param gui GUI
     */
    public HighScoresAnimation(HighScoresTable scores, String endKey, KeyboardSensor keyboard, GUI gui) {
        this.gui = gui;
        this.scores = scores;
        this.stop = false;
        this.keyboard = this.gui.getKeyboardSensor();
        this.endKey = endKey;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.yellow);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.drawText(240, 100, "High Scores Table", 40);
        d.setColor(Color.WHITE);
        d.drawText(242, 100, "High Scores Table", 40);

        d.setColor(Color.red);

        d.drawText(285, 200, "name", 30);
        d.drawText(455, 200, "score", 30);
        d.drawText(280, 200, "_________________", 30);

        for (int i = 0; i < this.scores.getHighScores().size(); i++) {
            d.setColor(Color.black);

            d.drawText(280, 240 + 32 * i, (i + 1) + ".  " + this.scores.getHighScores().get(i).getName(), 30);
            d.drawText(470, 240 + 32 * i, Integer.toString(this.scores.getHighScores().get(i).getScore()), 30);
        }
        d.drawText(250, 510, "Press space to continue", 30);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}