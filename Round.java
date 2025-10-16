<<<<<<< HEAD
package rock_paper_scissors.model;

import rock_paper_scissors.interfaces.ChoiceChecker;

=======
>>>>>>> 7975222764f2cd747ee7fb614b460620b8057cbf
/**
 * This Round class represents each round of the game.
 * It checkes either if the user won or lost, via polymorphism
 * looping over multiple checkers, which implements the ChoiceChecker class.
 */
public class Round {
    ChoiceChecker[] checkers;

    /**
     * Round class constructor.
     */
    public Round(ChoiceChecker[] checkers) {
        this.checkers = checkers;
    }

    /**
     * This method determines either if the user won or lost
     * and returns true if the user won,
     * otherwise, false.
     */
<<<<<<< HEAD
    public Boolean go(RPSChoice userChoice, RPSChoice computerChoice) {
=======
    public Boolean go(Choice userChoice, Choice computerChoice) {
>>>>>>> 7975222764f2cd747ee7fb614b460620b8057cbf
        boolean result = false;
        for (int i = 0; i < checkers.length; i++) {
            checkers[i].setUserChoice(userChoice);
            if (checkers[i].check(computerChoice)) {
                result = true;
            }
        }
        return result;
    }
}
