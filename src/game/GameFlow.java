// 213394364 Niv Ben David Ohayone

package game;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import animation.AnimationRunner;
import levels.LevelInformation;
import score.Counter;
import screens.EndScreen;
import screens.KeyPressStoppableAnimation;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private Counter finalScore;
    private Boolean win;

    /**
     * Constructor.
     *
     * @param ar  - the animation runner with will run the animations during the game
     * @param ks  - the keyboard of the user
     * @param gui - the gui which the game will be played on
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.finalScore = new Counter(0);
        this.win = true;
    }

    /**
     * This method is used to run all the levels of the game one by one.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, finalScore,
                    animationRunner, keyboardSensor, gui);
            level.initialize();
            //while level has more blocks and balls
            while (level.getBallsCounter().getValue() > 0
                    && level.getBlocksCounter().getValue() > 0) {
                level.run();
            }
            if (level.getBallsCounter().getValue() <= 0) {
                this.win = false;
                break;
            }
        }
        EndScreen screen = new EndScreen(this.finalScore.getValue(),
                this.keyboardSensor, this.gui, this.win);
        KeyPressStoppableAnimation key = new KeyPressStoppableAnimation
                (this.keyboardSensor, this.keyboardSensor.SPACE_KEY, screen);
        this.animationRunner.run(key);
        this.animationRunner.getGui().close();
    }


}