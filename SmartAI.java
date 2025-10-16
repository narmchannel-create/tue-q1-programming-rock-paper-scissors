public class SmartAI implements RpsAI {
    private int rockCount = 0;
    private int paperCount = 0;
    private int scissorsCount = 0;

    public String nextMove() {
        int total = rockCount + paperCount + scissorsCount;
        if (total == 0) {
            return new RandomAI().nextMove();
        }
        if (rockCount >= paperCount && rockCount >= scissorsCount) {
            return "Paper"; // counter Rock
        } else if (paperCount >= scissorsCount && paperCount >= rockCount) {
            return "Scissors"; // counter Paper
        } else {
            return "Rock"; // counter Scissors
        }
    }

    @Override
    public void observe(String playerMove, String aiMove, boolean playerWon) {
        switch (playerMove) {
            case "Rock" -> rockCount++;
            case "Paper" -> paperCount++;
            case "Scissors" -> scissorsCount++;
        }
    }
}
