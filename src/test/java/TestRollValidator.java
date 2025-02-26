import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRollValidator {

    @Test
    public void testTooManyRolls() {
        var exception = assertThrows(
                IllegalStateException.class,
                () -> GameValidator.validateRollScenario(Constants.TOTAL_POSSIBLE_ROLLS + 1, 1)
        );
        assertEquals(exception.getMessage(), "To many rolls for a game");
    }

    @Test
    public void testRoll_negative1() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> GameValidator.validateRollScenario(0, -1)
        );
        assertEquals(exception.getMessage(), "Can not roll less than 0");
    }

}
