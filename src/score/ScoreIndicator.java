// 213394364 Niv Ben David Ohayone
package score;

import biuoop.DrawSurface;
import core.Sprite;
import game.GameLevel;

import java.awt.*;

/**
 * represents a graphical indicator that displays the current score on the
 * screen. It implements the Sprite interface, allowing it to be added to
 * the game and participate in the game's drawing process.
 */
public class ScoreIndicator implements Sprite {
    static final int WIDTH = 800;
    static final int START = 0;
    static final int HEIGHT = 20;
    static final int TEXT = 15;
    static final int START_TEXT = 180;


    private Counter score;

    /**
     * Constructor.
     *
     * @param score - the current score in the game
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        //create a score object on the screen
        d.setColor(Color.WHITE);
        d.fillRectangle(START, START, WIDTH, HEIGHT);
        d.setColor(Color.BLACK);
        d.drawRectangle(START, START, WIDTH, HEIGHT);
        d.drawText(START_TEXT, TEXT, "Score: " + score.getValue(), TEXT);
    }

    @Override
    public void timePassed() {
    }

    /**
     * Add to game is a methos that add a game variable to the
     * sprite and collidable lists.
     *
     * @param g the game value
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
