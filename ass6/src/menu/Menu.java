package menu;

import animation.Animation;

/**
 A Menu<T> class.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 * @param <T> generic
 */
public interface Menu<T> extends Animation {
    /**
     * @param key string
     * @param message string
     * @param returnVal T
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * @return status
     */
    T getStatus();

    /**
     * @param key string
     * @param message string
     * @param subMenu menu<t>
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

}