
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import game.HighScoresTable;
import io.LevelSpecificationReader;
import levels.LevelInformation;
import menu.Menu;
import menu.MenuAnimation;
import menu.Task;

/**
 * A Ass6Game class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class Ass6Game {

    /**
     * main.
     * @param args is string who symbol numbers of levels
     * @throws IOException exception
     */
    public static void main(String[] args) throws IOException {
        GUI gui = new biuoop.GUI("Arkanoid", 800, 600);
        KeyboardSensor ks = gui.getKeyboardSensor();
        AnimationRunner ar = new AnimationRunner(gui);

        Menu<Task<Void>> mainMenu = new MenuAnimation<Task<Void>>("Arkanoid", ks, ar);
        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>("Sub Menu", ks, ar);

        File highScoreFile = new File("highscores.txt");
        HighScoresTable highScoreTable = HighScoresTable.loadFromFile(highScoreFile);

        // load the set levels
        InputStreamReader project;
        if (args.length > 0 && args[0] != null) {
            ClassLoader cl = ClassLoader.getSystemClassLoader();
            InputStream cl1 = cl.getResourceAsStream(args[0]);
            project = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]));
        } else {
            ClassLoader cl = ClassLoader.getSystemClassLoader();
            InputStream cl1 = cl.getResourceAsStream("levels_set.txt");
            project = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream("levels_set.txt"));
        }

        LineNumberReader numberReader = new LineNumberReader(project);

        ArrayList<String> key = new ArrayList<>();
        ArrayList<String> message = new ArrayList<>();
        ArrayList<String> path = new ArrayList<>();
        BufferedReader buffer = new BufferedReader(numberReader);

        // read the set levels
        String thisLine;
        while ((thisLine = buffer.readLine()) != null) {
            if (thisLine.equals("")) {
                continue;
            } else if (thisLine.contains(":")) {
                String[] parts = thisLine.split(":");
                key.add(parts[0]);
                message.add(parts[1]);
            } else {
                path.add(thisLine);
            }
        }
        // put to the sub menu
        // if (key.size() == message.size() && key.size() == path.size() &&
        // message.size() == path.size()) {
        for (int i = 0; i < key.size(); i++) {
            ClassLoader cla = ClassLoader.getSystemClassLoader();
            InputStream cla1 = cla.getResourceAsStream(path.get(i));
            InputStreamReader project1 = new InputStreamReader(
                    ClassLoader.getSystemClassLoader().getResourceAsStream(path.get(i)));
            LevelSpecificationReader b = new LevelSpecificationReader();
            // BufferedReader r=new BufferedReader((project1));
            List<LevelInformation> listToSend = b.fromReader((Reader) project1);

            Task<Void> list = new Task<Void>() {
                public Void run() {
                    GameFlow game = new GameFlow(800, 600, highScoreTable, gui, ks, ar);
                    List<LevelInformation> l = listToSend;
                    game.runLevels(l);
                    return null;
                }
            };
            subMenu.addSelection(key.get(i), message.get(i), list);
        }

        Task<Void> sm = new Task<Void>() {
            public Void run() {
                ar.run(subMenu);
                Task<Void> t = (Task<Void>) subMenu.getStatus();
                t.run();
                return null;
            }
        };

        mainMenu.addSelection("s", "Press (s) to start a new game", sm);

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
