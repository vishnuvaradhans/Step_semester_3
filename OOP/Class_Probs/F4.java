final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    final double calculatePenalty(double ticketFare, int minutesLate) {

        if (ticketFare < 0 || minutesLate < 0)
            throw new IllegalArgumentException("Invalid input");

        if (minutesLate == 0)
            return 0;

        double percent = 0;

        percent += Math.min(minutesLate, 5) * 0.5;

        if (minutesLate > 5)
            percent += Math.min(minutesLate - 5, 10) * 1.0;

        if (minutesLate > 15)
            percent += (minutesLate - 15) * 2.0;

        double tieredPenalty = ticketFare * percent / 100;
        double minimumPenalty =
            ticketFare * minimumPenaltyPercent / 100;

        return Math.max(tieredPenalty, minimumPenalty);
    }
}

public class F4 {
    public static void main(String[] args) {

        BoardingPenaltyCalculator calculator =
            new BoardingPenaltyCalculator(1);

        System.out.println(
            "Rs " + calculator.calculatePenalty(1000, 0)
        );

        System.out.println(
            "Rs " + calculator.calculatePenalty(1000, 1)
        );

        System.out.println(
            "Rs " + calculator.calculatePenalty(1000, 16)
        );
    }
}