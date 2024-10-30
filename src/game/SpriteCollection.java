// 213394364 Niv Ben David Ohayone
package game;

import core.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a collection of sprites, and provides methods to
 * add sprites, notify them of elapsed time, and draw them on a given
 * DrawSurface. It uses a List data structure to store the sprites and
 * iterates over the list to perform the required operations on each sprite.
 */
public class SpriteCollection {

    static final int START = 0;

    private List<Sprite> spriteList;

    /**
     * create a new list sprites.
     */
    public SpriteCollection() {
        spriteList = new ArrayList<Sprite>();
    }

    /**
     * add a new sprite to the sprite list.
     *
     * @param s the sprite element that the method will add to list
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * call timePassed() on all sprites elements in the list.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(spriteList);
        //run on any index of the list
        for (int i = START; i < spritesCopy.size(); i++) {
            spritesCopy.get(i).timePassed();
        }
    }

    /**
     * call Draw on() on all sprites elements in the list.
     *
     * @param d the DrawSurface element to draw on it
     */
    public void drawAllOn(DrawSurface d) {
        //run on any index of the list
        for (int i = START; i < spriteList.size(); i++) {
            spriteList.get(i).drawOn(d);
        }

    }

    /**
     * Remove sprite from list.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }


}
