// 213394364 Niv Ben David Ohayone
package removers;

import game.GameLevel;
import listeners.HitListener;
import gameObjects.Ball;
import gameObjects.Block;
import score.Counter;

/**
 * BlockRemover is in charge of removing blocks from the game, as well as
 * keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param game          the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //remove a block from the game
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);
        // decrease the number of balls to -1
        this.remainingBlocks.decrease(1);
    }
}