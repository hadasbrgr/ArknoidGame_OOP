package collision;

/**
 * A interface HitNotifier.
 * @author Hadas Berger <hadasbrgr@gmail.com>
 * @version 1.6
 * @since 2010-03-31 )
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl HitListener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl HitListener
     */
    void removeHitListener(HitListener hl);

}