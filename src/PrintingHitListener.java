import gameObjects.Ball;
import gameObjects.Block;
import listeners.HitListener;

public class PrintingHitListener implements HitListener {
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}
