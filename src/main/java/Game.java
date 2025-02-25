import java.util.*;

public class Game {
    private int score;
    private final int TOTAL_POSSIBLE_ROLLS = 21;
    private final List<Integer> frames;
    private int rollCount;

    public Game() {
        this.score = 0;
        List<Integer> immutableList = Collections.nCopies(TOTAL_POSSIBLE_ROLLS,0);
        this.frames = new ArrayList<>(immutableList);
        this.rollCount = 0;
    }

    public int getRollCount() {
        return rollCount;
    }

    public void roll(int pinCount) {
        RollValidator.validateRoll(pinCount);
        frames.set(rollCount, pinCount);
        if (isAStrike(pinCount)) {
            rollCount++;
        }
        rollCount++;
    }

    public int score() {
        calculateScore();
        return score;
    }

    private void calculateScore() {
        frames.forEach(roll -> score += roll);
        for (int i = 0; i < frames.size() - 1; i++) {
            if (isAStrike(frames.get(i))) {
                score += frames.get(i+2) + frames.get(i+3);
                i++;
                continue;
            }
            if (isASpare(frames.subList(i, i+2))) {
                score += frames.get(i+1);
            }
        }
    }

    private boolean isASpare(List<Integer> rolls) {
        return rolls.get(0) + rolls.get(1) == 10;
    }

    private boolean isAStrike(int pinCount) {
        return pinCount == 10;
    }
}
