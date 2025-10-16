<<<<<<< HEAD
package rock_paper_scissors.model;
import java.util.Random;

import rock_paper_scissors.abstracts.Player;
=======
import java.util.Random;
>>>>>>> 7975222764f2cd747ee7fb614b460620b8057cbf
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
<<<<<<< HEAD
    public RPSChoice play() {
        String[] options = {"Rock", "Paper", "Scissors"};
        String computerChoice = options[new Random().nextInt(3)];

        RPSChoice option = new Rock();
=======
    public Choice play() {
        String[] options = {"Rock", "Paper", "Scissors"};
        String computerChoice = options[new Random().nextInt(3)];

        Choice option = new Rock();
>>>>>>> 7975222764f2cd747ee7fb614b460620b8057cbf

        if (computerChoice.equals("Paper")) {
            option = new Paper();
        }
        if (computerChoice.equals("Scissors")) {
            option = new Scissors();
        }

        return option;
    }
}
