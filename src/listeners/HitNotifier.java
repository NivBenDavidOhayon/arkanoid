// 213394364 Niv Ben David Ohayone
package listeners;

/**
 * This interface is used to enable object to notify when there was a hit
 * event in the game.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl - the listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl - the listener to remove
     */

    void removeHitListener(HitListener hl);
}