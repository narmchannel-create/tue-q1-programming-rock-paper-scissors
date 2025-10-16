package rock_paper_scissors.model;
/**
 * This is the superclass of rock, paper, scissors that are played by
 * the user and the computer.
 */
public abstract class RPSChoice {
    private int number;
    private String name;

    /**
     * Each choice has a unique id, a number, and a name. The name is either rock, paper or scissors.
     */
    public RPSChoice(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }
}
