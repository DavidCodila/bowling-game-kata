import java.security.InvalidParameterException;

public class RollValidator {

    public static void validateRoll(int pinCount) {
        if (pinCount < 0) {
            throw new InvalidParameterException("Can not roll less than 0");
        }
    }
}
