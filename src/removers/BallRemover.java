// 213394364 Niv Ben David Ohayone
package removers;

import game.GameLevel;
import listeners.HitListener;
import gameObjects.Ball;
import gameObjects.Block;
import score.Counter;

/**
 * BallRemover class is responsible for removing a ball from the game when it
 * hits a block, and updating the count of remaining balls accordingly.
 */

public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param game           - the game the ball is in
     * @param remainingBalls - the amount of balls in the game
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //remove a ball from the game
        hitter.removeFromGame(game);
        // decrease the number of balls to -1
        this.remainingBalls.decrease(1);
    }
}