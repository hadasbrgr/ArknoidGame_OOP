package menu;

import java.util.List;

import levels.LevelInformation;

/**
 * A OptionTask class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 * @param <T> generic
 */
public class OptionTask<T> {
    private String key;
    private String message;
    private T returnVal;
    private List<LevelInformation> levelSet;

    /**
     * constructor.
     * @param key string
     * @param message string
     * @param returnVal T
     */
    public OptionTask(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    /**
     * @param key string
     * @param message string
     * @param levelSet list
     */
    public OptionTask(String key, String message, List<LevelInformation> levelSet) {
        super();
        this.key = key;
        this.message = message;
        this.levelSet = levelSet;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the levelSet
     */
    public List<LevelInformation> getLevelSet() {
        return levelSet;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the returnval
     */
    public T getReturnVal() {
        return returnVal;
    }

}
