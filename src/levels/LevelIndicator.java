// 213394364 Niv Ben David Ohayone

package levels;

import biuoop.DrawSurface;
import core.Sprite;
import game.GameLevel;

/**
 *
 * The "LevelIndicator" class represents a sprite that displays the name of
 * the current level on the screen. It implements the "Sprite" interface and
 * provides methods for drawing the level name on a DrawSurface and adding
 * the level indicator to a GameLevel.
 */
public class LevelIndicator implements Sprite {

    private String levelName;

    /**
     * Constructor.
     *
     * @param levelName - the name of the current level
     */
    public LevelIndicator(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        //add the level name to the screen
        d.drawText(460, 15, "Level Name: " + levelName, 15);
    }

    @Override
    public void timePassed() {

    }

    /**
     * This method is used to add the level indicator to the game.
     *
     * @param game - the game to add the level indicator to
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
