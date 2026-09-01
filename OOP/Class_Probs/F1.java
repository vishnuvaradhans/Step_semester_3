import java.util.Random;

public class F1 {
    static String playRound(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove))
            return "Draw";

        if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
            (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
            (playerMove.equals("Scissors") && computerMove.equals("Paper")))
            return "Player Wins";

        return "Computer Wins";
    }

    public static void main(String[] args) {
        String[] moves = {"Rock", "Paper", "Scissors"};
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};

        Random random = new Random();

        int wins = 0, losses = 0, draws = 0;

        for (int i = 0; i < 5; i++) {
            String computerMove = moves[random.nextInt(3)];
            String result = playRound(playerMoves[i], computerMove);

            if (result.equals("Player Wins"))
                wins++;
            else if (result.equals("Computer Wins"))
                losses++;
            else
                draws++;

            System.out.println("Round " + (i + 1) +
                    " | Player: " + playerMoves[i] +
                    " | Computer: " + computerMove +
                    " | " + result);
        }

        double winPercentage = wins * 100.0 / 5;

        System.out.println("Wins: " + wins +
                " | Losses: " + losses +
                " | Draws: " + draws +
                " | Win % = " + winPercentage + "%");
    }
}