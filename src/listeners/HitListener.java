// 213394364 Niv Ben David Ohayone
package listeners;

import gameObjects.Ball;
import gameObjects.Block;

/**
 * The interface Hit listener defines a method called "hitEvent" which is
 * called whenever a block is hit by a ball.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit - the block that is being hit
     * @param hitter   - the Sprites.Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}