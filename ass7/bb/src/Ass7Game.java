
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import game.HighScoresTable;
import levels.LevelInformation;
import levels.SpaceLevel;
import menu.Menu;
import menu.MenuAnimation;
import menu.Task;

/**
 * A Ass7Game class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class Ass7Game {

    /**
     * main.
     * @param args is string who symbol numbers of levels
     * @throws IOException exception
     */
    public static void main(String[] args) throws IOException {
        GUI gui = new biuoop.GUI("Space Invaders", 800, 600);
        KeyboardSensor ks = gui.getKeyboardSensor();
        AnimationRunner ar = new AnimationRunner(gui);
        Menu<Task<Void>> mainMenu = new MenuAnimation<Task<Void>>("Space Invaders", ks, ar);
        File highScoreFile = new File("highscores.txt");
        HighScoresTable highScoreTable = HighScoresTable.loadFromFile(highScoreFile);
        List<LevelInformation> l = new ArrayList<>();
        l.add(new SpaceLevel());
        Task<Void> s = new Task<Void>() {
            public Void run() {
                GameFlow game = new GameFlow(800, 600, highScoreTable, gui, ks, ar);
                game.runLevels(l);
                return null;
            }
        };
        mainMenu.addSelection("s", "Press (s) to start a new game", s);
        Task<Void> h = new Task<Void>() {
            public Void run() {
                ar.run(new KeyPressStoppableAnimation(ks, KeyboardSensor.SPACE_KEY,
                        new HighScoresAnimation(highScoreTable, KeyboardSensor.SPACE_KEY, ks, gui)));
                return null;
            }
        };
        mainMenu.addSelection("h", "Press (h) to see the high scores", h);
        Task<Void> q = new Task<Void>() {
            public Void run() {
                gui.close();
                System.exit(0);
                return null;
            }
        };
        mainMenu.addSelection("q", "Press (q) to quit", q);
        while (true) {
            ar.run(mainMenu);
            // wait for user selection
            Task<Void> task = mainMenu.getStatus();
            if (task != null) {
                task.run();
            }
        }
    }
}
