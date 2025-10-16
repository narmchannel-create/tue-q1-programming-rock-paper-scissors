<<<<<<< HEAD
package rock_paper_scissors.model;

import rock_paper_scissors.abstracts.Player;

=======
>>>>>>> 7975222764f2cd747ee7fb614b460620b8057cbf
/**
 * User player that extends 'Player' superclass.
 * It represents the users,
 */
public class User extends Player {
    String choice;

<<<<<<< HEAD
    public User(String name) {
=======
    User(String name) {
>>>>>>> 7975222764f2cd747ee7fb614b460620b8057cbf
        super(name);
    }

    public void setChoice(String input) {
        this.choice = input;
    }

    /**
     * The user input will be converted in one of te following subclasses of 'Choice'.
     * Either rock, paper, or scissors.
     */
    @Override
<<<<<<< HEAD
    public RPSChoice play() {
        RPSChoice option = new Rock();
=======
    public Choice play() {
        Choice option = new Rock();
>>>>>>> 7975222764f2cd747ee7fb614b460620b8057cbf

        if (choice.equals("Paper")) {
            option = new Paper();
        }
        if (choice.equals("Scissors")) {
            option = new Scissors();
        }

        return option;
    }
}
