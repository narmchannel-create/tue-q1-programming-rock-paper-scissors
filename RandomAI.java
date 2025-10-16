// easy AI: purely random
import java.util.concurrent.ThreadLocalRandom;

public class RandomAI implements RpsAI {
    private final String[] MOVES = {"Rock", "Paper", "Scissors"};

    public String nextMove() {
        int i = ThreadLocalRandom.current().nextInt(MOVES.length);
        return MOVES[i];
    }
}
