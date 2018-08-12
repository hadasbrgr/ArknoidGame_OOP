package menu;

/**
 * A Task class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 * @param <T> generic
 */
public interface Task<T> {
    /**
     * @return t
     */
    T run();
}