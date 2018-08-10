package game;

import java.io.File;
import java.io.IOException;
import java.util.List;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import counter.Counter;
import levels.LevelInformation;
import menu.Menu;
import menu.Task;

/**
 * A GameFlow class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class GameFlow {

    private int width;
    private int hight;
    private GUI gui;
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private Counter score;
    private Counter numberOfLives;
    private Counter numberOfBalls;
    private boolean win;
    private HighScoresTable table;
    private Menu<Task<Void>> menu;

    /**
     * a constructor to GameFlow.
     * @param width of GameFlow
     * @param hight of GameFlow
     * @param table HighScoresTable
     * @param ks KeyboardSensor
     * @param gui GUI
     * @param ar AnimationRunner
     */
    public GameFlow(int width, int hight, HighScoresTable table, GUI gui, KeyboardSensor ks, AnimationRunner ar) {
        this.width = width;
        this.hight = hight;
        this.gui = gui;
        this.ks = ks;
        this.score = new Counter(0);
        this.numberOfLives = new Counter(1);
        this.ar = ar;
        this.win = false;
        this.table = table;
    }

    /**
     * moving from one level to the next.
     * @param levels list the levels at the game.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.gui, this.ks, this.score, this.numberOfLives, this.ar);

            level.initialize();

            while (level.shouldStop() && level.getCounterBlocks().getValue() != 0
                    && this.numberOfLives.getValue() != 0) {
                level.playOneTurn();
                if (this.numberOfLives.getValue() == 0) {
                    this.ar.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY,
                            new EndScreen(1, this.score.getValue(), this.gui, this.win)));
                    addToTable();
                }
            }
        }
        if (this.numberOfLives.getValue() > 0) {
            this.win = true;
            this.ar.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY,
                    new EndScreen(2, this.score.getValue(), this.gui, this.win)));
            addToTable();
        }
    }

    /**
     * add the new information to the high score table if is good.
     */
    public void addToTable() {
        if (this.table.getRank(this.score.getValue()) <= this.table.size()) {

            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            this.table.add(new ScoreInfo(name, this.score.getValue()));

            File highScoresFile = new File("highscores.txt");
            try {
                this.table.save(highScoresFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.ar.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(this.table, "h", this.ks, this.gui)));
    }

}