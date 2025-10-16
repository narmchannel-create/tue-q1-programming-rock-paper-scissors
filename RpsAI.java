// Define a tiny AI interface

public interface RpsAI {
    // Ask the AI for its next move: "Rock", "Paper", or "Scissors"
    String nextMove();

    // Let the AI learn from the Last round (player + computer moves)
    default void observe(String playerMove, String aiMove, boolean playerWon) {}
}

    

