package game;

import java.io.Serializable;

/**
 * A ScoreInfo class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public class ScoreInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int score;

    /**
     * Constructor of ScoreInfo.
     * @param name the name player
     * @param score the score player
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return score.
     */
    public int getScore() {
        return this.score;
    }
/**
 * @param score1 ScoreInfo.
 * @return ScoreInfo to string
 */
    public String print(ScoreInfo score1) {
        return "name:" + score1.getName() + "score:" + score1.getScore();
    }
}