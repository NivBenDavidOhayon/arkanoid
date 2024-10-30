// 213394364 Niv Ben David Ohayone

package animation;

import biuoop.DrawSurface;

/**
 * This interface is used to represent animations in the game
 */
public interface Animation {
    /**
     * This method is used to draw a single frame animation on the drawing surface.
     *
     * @param d - the drawing surface
     */
    public void doOneFrame(DrawSurface d);

    /**
     * This method is used to determine whether the animation should stop and
     * not do another frame.
     *
     * @return - true if needed to continue the animation, false otherwise
     */
    public boolean shouldStop();
}
