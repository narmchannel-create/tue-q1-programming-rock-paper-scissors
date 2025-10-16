package rock_paper_scissors.model;
import java.util.Random;

import rock_paper_scissors.abstracts.Player;
/**
 * Computer class represents the computer. It plays randomly against the user.
 */
public class Computer extends Player {
    public Computer(String name) {
        super(name);
    }

    /**
     * The computer plays randomly.
     * Either rock, paper, or scissors.
     */
    public RPSChoice play() {
        String[] options = {"Rock", "Paper", "Scissors"};
        String computerChoice = options[new Random().nextInt(3)];

        RPSChoice option = new Rock();

        if (computerChoice.equals("Paper")) {
            option = new Paper();
        }
        if (computerChoice.equals("Scissors")) {
            option = new Scissors();
        }

        return option;
    }
}
