/**
 * Super class for user and computer.
 */
abstract class Player {
    String name;

    Player(String name) {
        this.name = name;
    }

    /**
     * let each players to play either rock, paper or scissors.
     */
    abstract Choice play();
    
    public String getName() {
        return this.name;
    }
}
