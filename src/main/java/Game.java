import java.util.*;

public class Game {
    private int score;
    private final List<Integer> rolls;
    private int rollCount;

    public Game() {
        this.score = 0;
        this.rolls = getInitalisedList();
        this.rollCount = 0;
    }

    public int getRollCount() {
        return rollCount;
    }

    public void roll(int pinCount) {
        GameValidator.validateRollScenario(rollCount, pinCount);
        rolls.set(rollCount, pinCount);
        rollCount = (isAStrike(pinCount)) ? rollCount + 2 : rollCount + 1;
    }

    public int score() {
        calculateBaseScore();
        calculateBonusScore();
        return score;
    }

    private boolean isAStrike(int pinCount) {
        return pinCount == 10;
    }

    private void calculateBaseScore() {
        rolls.forEach(roll -> score += roll);
    }

    private void calculateBonusScore() {
        for (int i = 0; i < rolls.size() - 1; i++) {
            if (isAStrike(rolls.get(i))) {
                score += rolls.get(i+2) + rolls.get(i+3);
                i++;
                continue;
            }
            if (isASpare(rolls.subList(i, i+2))) {
                score += rolls.get(i+1);
            }
        }
    }

    private boolean isASpare(List<Integer> rolls) {
        return rolls.get(0) + rolls.get(1) == 10;
    }

    private List<Integer> getInitalisedList() {
        return new ArrayList<>(Collections.nCopies(Constants.TOTAL_POSSIBLE_ROLLS,0));
    }
}
