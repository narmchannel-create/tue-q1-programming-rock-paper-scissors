/**
 * User player that extends 'Player' superclass.
 * It represents the users,
 */
public class User extends Player {
    String choice;

    User(String name) {
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
    public Choice play() {
        Choice option = new Rock();

        if (choice.equals("Paper")) {
            option = new Paper();
        }
        if (choice.equals("Scissors")) {
            option = new Scissors();
        }

        return option;
    }
}
