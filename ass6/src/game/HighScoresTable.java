package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A HighScoresTable class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class HighScoresTable implements Serializable {
    private static final long serialVersionUID = 1L;
    private int size;
    private List<ScoreInfo> scoreList;

    /**
     * Create an empty high-scores table with the specified size.
     * @param size the table holds up to size top scores.
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.scoreList = new ArrayList<ScoreInfo>(size);

    }

    /**
     * Add a high-score.
     * @param score ScoreInfo
     */
    public void add(ScoreInfo score) {
        this.scoreList.add(score);

        bouble(this.scoreList);
        while (this.scoreList.size() > this.size) {
            this.scoreList.remove(this.scoreList.size() - 1);
        }
    }

    /**
     * Return table size.
     * @return size.
     */
    public int size() {
        /*
         * int size=0; for (ScoreInfo scoreInfo : scoreList) {
         * if(scoreInfo!=null) size++; }
         */
        return size;
    }

    /**
     * Return the current high scores. The list is sorted such that the highest
     * scores come first.
     * @return list of score info
     */
    public List<ScoreInfo> getHighScores() {
        return this.scoreList;
    }

    /**
     * bouble.
     * @param scoreList1 list
     * @return List<ScoreInfo>
     */
    public List<ScoreInfo> bouble(List<ScoreInfo> scoreList1) {
        ScoreInfo temp;
        if (scoreList1.size() > 1) { // check if the number of orders is larger
                                     // than 1

            for (int x = 0; x < scoreList1.size(); x++) { // bubble sort outer
                                                          // loop

                for (int i = 0; i < scoreList1.size() - x - 1; i++) {
                    if (scoreList1.get(i).getScore() < (scoreList1.get(i + 1).getScore())) {
                        temp = scoreList1.get(i);
                        scoreList1.set(i, scoreList1.get(i + 1));
                        scoreList1.set(i + 1, temp);
                    }
                }
            }
        }
        return scoreList1;
    }

    /**
     * Rank 1 means the score will be highest on the list. Rank `size` means the
     * score will be lowest. Rank > `size` means the score is too low and will
     * not be added to the list.
     * @param score int
     * @return the rank of the current score: where will it be on the list if
     *         added?
     */
    public int getRank(int score) {
        int i;
        for (i = 0; i < this.scoreList.size(); i++) {
            if (score > this.scoreList.get(i).getScore()) {
                break;
            }
        }
        return i + 1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.scoreList.clear();
    }

    /**
     * Load table data from file. Current table data is cleared.
     * @param filename file
     * @throws IOException exeption
     */
    public void load(File filename) throws IOException {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(filename.getName()));
            HighScoresTable highScoresTable;
            highScoresTable = (HighScoresTable) objectInputStream.readObject();
            this.scoreList = highScoresTable.scoreList;
            this.size = highScoresTable.size;
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + filename.getName());
            HighScoresTable emptyTable = new HighScoresTable(5);
            emptyTable.save(filename);
            this.scoreList = emptyTable.scoreList;
            this.size = emptyTable.size;
        } catch (ClassNotFoundException e) {
            System.err.println("Unable to find class for object in file: " + filename.getName());
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename.getName());
            }
        }
    }

    /**
     * Save table data to the specified file.
     * @param filename File
     * @throws IOException exception
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving object");
            throw e;
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * Read a table from file and return it. If the file does not exist, or
     * there is a problem with reading it, an empty table is returned.
     * @param filename File
     * @return table
     * @throws IOException
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable emptyTable = new HighScoresTable(5);
        try {
            if (!filename.exists()) {
                return emptyTable;
            }
            emptyTable.load(filename);
        } catch (IOException e) {
            System.err.println("Failed closing file: " + filename.getName());
            return new HighScoresTable(5);
        }
        return emptyTable;
    }
/**
 * print table.
 */
    public void printTable() {
        int count = 1;
        for (ScoreInfo score : this.getHighScores()) {
            System.out.println(count + ": " + "name:" + score.getName() + "\t" + "score:" + score.getScore());
            count++;
        }
    }
/**
 * @param score ScoreInfo
 * @return ScoreInfo to string
 */
    public String print(ScoreInfo score) {
        return "name:" + score.getName() + "score:" + score.getScore();
    }

}