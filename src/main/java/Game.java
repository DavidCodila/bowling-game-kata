public class Game {
    private int score;

    public void roll(int pinCount) {
        RollValidator.validateRoll(pinCount);
        score += pinCount;
    }

    public int score() {
        return score;
    }
}
