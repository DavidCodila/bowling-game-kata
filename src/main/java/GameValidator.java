import java.security.InvalidParameterException;

public class GameValidator {

    public static void validateRollScenario(int rollCount, int pinCount) {
        validateGameState(rollCount);
        validateRoll(pinCount);
    }

    private static void validateGameState(int rollCount) {
        if (rollCount > Constants.TOTAL_POSSIBLE_ROLLS) {
            throw new IllegalStateException("To many rolls for a game");
        }
    }

    private static void validateRoll(int pinCount) {
        if (pinCount < 0) {
            throw new InvalidParameterException("Can not roll less than 0");
        }
    }

}
