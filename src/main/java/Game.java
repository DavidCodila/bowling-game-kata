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

    public void roll(int pinCount) {
        RollValidator.validateRoll(pinCount);
        frames.set(rollCount, pinCount);
        rollCount++;
    }

    public int score() {
        calculateScore();
        return score;
    }

    private void calculateScore() {
        frames.forEach(roll -> score += roll);
        for (int i = 1; i < frames.size() - 3; i += 2) {
            if (isASpare(frames.subList(i-1, i+1))) {
                score += frames.get(i+1);
            }
        }
    }

    private boolean isASpare(List<Integer> rolls) {
        return rolls.get(0) + rolls.get(1) == 10;
    }
}
