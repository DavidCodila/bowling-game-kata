import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRollValidator {

    @Test
    public void testRoll_negative1() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> RollValidator.validateRoll(-1)
        );
        assertEquals(exception.getMessage(), "Can not roll less than 0");
    }

}
