import java.util.*;

public class Game {
    private int score;
    private final List<Integer> rolls;
    private int rollCount;

    public Game() {
        this.score = 0;
        this.rollCount = 0;
        this.rolls = getInitalisedList();
    }

    public void roll(int pinCount) {
        GameValidator.validateRollScenario(rollCount, pinCount);
        rolls.set(rollCount, pinCount);
        if (gameIsInTenthFrame()) {
            handleTenthFrame();
        } else if (isAStrikeOnTheFirstRollOfAFrame(rollCount)) {
            moveToNextFrame();
        } else {
            moveToNextRoll();
        }
    }

    public int score() {
        calculateBaseScore();
        calculateBonusScore();
        return score;
    }

    public int getRollCount() {
        return rollCount;
    }

    private boolean gameIsInTenthFrame() {
        return rollCount >= Constants.TENTH_FRAME_ROLL_COUNT;
    }

    private void handleTenthFrame() {
        if (secondLastRollOfGameIsNotAStrikeOrSpare()) {
            endGame();
        }
        else {
            moveToNextRoll();
        }
    }

    private boolean secondLastRollOfGameIsNotAStrikeOrSpare() {
        return rollCount == Constants.TOTAL_POSSIBLE_ROLLS - 1
                && !isASpare(rollCount - 1)
                && !isAStrike(rollCount - 1);
    }

    private void endGame() {
        rollCount = Constants.TOTAL_POSSIBLE_ROLLS + 1;
    }

    private boolean isAStrikeOnTheFirstRollOfAFrame(int index) {
        return isAStrike(index) && (rollCount % 2 != 1);
    }

    private boolean isAStrike(int index) {
        return rolls.get(index) == 10;
    }

    private void moveToNextFrame() {
        rollCount += 2;
    }

    private void moveToNextRoll() {
        rollCount++;
    }

    private void calculateBaseScore() {
        rolls.forEach(roll -> score += roll);
    }

    private void calculateBonusScore() {
        calculateBonusScoreForFramesOneToNine();
        calculateBonusScoreForTenthFrame();
    }

    private void calculateBonusScoreForFramesOneToNine() {
        for (int i = 0; i < Constants.TENTH_FRAME_ROLL_COUNT; i++) {
            if (isAStrike(i)) {
                addStrikeBonus(i);
                i++;
            }
            else if (isNotFirstRoll(i) && isASpare(i - 1)) {
                addSpareBonus(i);
            }
        }
    }

    private static boolean isNotFirstRoll(int i) {
        return i > 0;
    }

    private void addSpareBonus(int i) {
        score += rolls.get(i+1);
    }

    private void addStrikeBonus(int i) {
        score += rolls.get(i+2) + rolls.get(i+3);
    }

    private void calculateBonusScoreForTenthFrame() {
        for (int i = Constants.TENTH_FRAME_ROLL_COUNT; i < Constants.TOTAL_POSSIBLE_ROLLS; i++) {
            if (isAStrike(i)) {
                handleStrikeBonus(i);
            } else if (isASpare(i - 1)) {
                addSpareBonus(i);
            }
        }
    }

    private void handleStrikeBonus(int rollIndex) {
        if (rollIndex == Constants.TENTH_FRAME_ROLL_COUNT) {
            score += rolls.get(rollIndex + 1) + rolls.get(rollIndex + 2);
        } else if (rollIndex == Constants.TENTH_FRAME_ROLL_COUNT + 1) {
            score += rolls.get(rollIndex + 1);
        }
    }

    private boolean isASpare(Integer rollIndex) {
        return rolls.get(rollIndex) + rolls.get(rollIndex + 1) == 10;
    }

    private List<Integer> getInitalisedList() {
        return new ArrayList<>(Collections.nCopies(Constants.TOTAL_POSSIBLE_ROLLS + 1,0));
    }

}
