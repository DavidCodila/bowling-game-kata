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
        if (rollCount >= Constants.TENTH_FRAME_ROLL_COUNT) {
            handleTenthFrame();
        }
        else {
            rollCount = (isAStrike(pinCount)) ? rollCount + 2 : rollCount + 1;
        }
    }

    private void handleTenthFrame() {
        if (rollCount == Constants.TOTAL_POSSIBLE_ROLLS - 1 && !isASpare(rollCount - 1) && !isAStrike(rolls.get(rollCount - 1))) {
            rollCount = Constants.TOTAL_POSSIBLE_ROLLS + 1;
            return;
        }
        rollCount++;
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
        for (int i = 0; i < Constants.TENTH_FRAME_ROLL_COUNT; i++) {
            if (isAStrike(rolls.get(i))) {
                score += rolls.get(i+2) + rolls.get(i+3);
                i++;
            }
            else if (i > 0 && isASpare(i - 1)) {
                score += rolls.get(i+1);
            }
        }
        calculateBonusScoreForTenthFrame();
    }

    private void calculateBonusScoreForTenthFrame() {
        for (int i = Constants.TENTH_FRAME_ROLL_COUNT; i < Constants.TOTAL_POSSIBLE_ROLLS; i++) {
            if (isAStrike(rolls.get(i))) {
                switch (i) {
                    case Constants.TENTH_FRAME_ROLL_COUNT:
                        score += rolls.get(i+1) + rolls.get(i+2);
                        break;
                    case Constants.TENTH_FRAME_ROLL_COUNT + 1:
                        score += rolls.get(i+1);
                        break;
                }
            } else if (isASpare(i - 1)) {
                score += rolls.get(i+1);
            }
        }
    }

    private boolean isASpare(Integer rollIndex) {
        return rolls.get(rollIndex) + rolls.get(rollIndex + 1) == 10;
    }

    private List<Integer> getInitalisedList() {
        return new ArrayList<>(Collections.nCopies(Constants.TOTAL_POSSIBLE_ROLLS + 1,0));
    }
}
