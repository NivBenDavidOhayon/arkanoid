// 213394364 Niv Ben David Ohayone
import game.GameFlow;
import levels.DirectHit;
import levels.Green3;
import levels.LevelInformation;
import levels.WideEasy;
import animation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * the Ass6Game class responsible to play the game.
 */
public class Ass6Game {
    /**
     * The main method runs the whole game.
     * calls the functions initialize and run that prepare the game an
     * d activate it respectively.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        //if the user didn't enter his choice
        if (args.length == 0) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
        } else {
            //the user entered a choice
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "1" -> levels.add(new DirectHit());
                    case "2" -> levels.add(new WideEasy());
                    case "3" -> levels.add(new Green3());
                    default -> {
                        continue;
                    }
                }
            }
            /*if reached here- the user entered only words without numbers-
             so as default, it adds all levels
             */
            if (levels.size() == 0) {
                levels.add(new DirectHit());
                levels.add(new WideEasy());
                levels.add(new Green3());
            }
        }
        AnimationRunner ar = new AnimationRunner();
        GameFlow gameFlow = new GameFlow(ar, ar.getGui().getKeyboardSensor(),ar.getGui());
        gameFlow.runLevels(levels);
    }
}
