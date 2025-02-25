import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestGame {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testGetScoreAfterRoll_1() {
        game.roll(1);
        assertEquals(1, game.score());
    }

    @Test
    public void testGetScoreAfterRoll_1_2() {
        game.roll(1);
        game.roll(2);
        assertEquals(3, game.score());
    }

    @Test
    public void testGetScoreAfterRollSpare_9_1_1_1() {
        game.roll(9);
        game.roll(1);
        game.roll(1);
        game.roll(1);
        assertEquals(13, game.score());
    }

    @Test
    public void testRoll_negative1() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> game.roll(-1)
        );
        assertEquals(exception.getMessage(), "Can not roll less than 0");
    }
}
