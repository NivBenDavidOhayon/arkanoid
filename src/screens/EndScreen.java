// 213394364 Niv Ben David Ohayone

package screens;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import animation.Animation;

import java.awt.*;

/**
 *
 * The EndScreen class represents an end screen animation displayed at the end
 * of the game, showing the final score and whether the player won or lost.
 */
public class EndScreen implements Animation {
    private int finalScore;
    private boolean stop;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private Boolean win;


    /**
     * Constructor.
     *
     * @param finalScore     - the final score of the game.
     * @param keyboardSensor the keyboard sensor
     * @param gui            the gui
     * @param win            the win
     */
    public EndScreen(int finalScore, KeyboardSensor keyboardSensor, GUI gui, Boolean win) {
        this.stop = false;
        this.finalScore = finalScore;
        this.keyboardSensor = keyboardSensor;
        this.gui = gui;
        this.win = win;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //create the background
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.black);
        // If the player did not win
        if (!this.win) {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is "
                    + finalScore, 32);
        // if the player win
        } else {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is "
                    + finalScore, 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
