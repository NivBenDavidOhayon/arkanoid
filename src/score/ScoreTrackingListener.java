// 213394364 Niv Ben David Ohayone
package score;

import listeners.HitListener;
import gameObjects.Ball;
import gameObjects.Block;

/**
 * tracks the score in the game by implementing the HitListener interface.
 * It increases the score by 5 whenever a block is hit by a ball. The current
 * score can be retrieved using the getCurrentScore() method. */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * the method return the current score.
     *
     * @return the center - the current center of the ball.
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }

    /**
     * Constructor.
     *
     * @param scoreCounter - the current score in the game
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
