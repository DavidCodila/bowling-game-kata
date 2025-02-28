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
    public void testGetScoreAfterStrike_10_1_1() {
        game.roll(10);
        game.roll(1);
        game.roll(1);
        assertEquals(14, game.score());
    }

    @Test
    public void testRollCountAfterStrike_10() {
        game.roll(10);
        assertEquals(2, game.getRollCount());
    }

    @Test
    public void testStrikeOn10thAnd11thFrames() {
        for (int i = 0; i < 21; i++) {
            game.roll(0);
        }
        game.roll(10);
        game.roll(10);
        assertEquals(30, game.score());
    }

    @Test
    public void testSpareOn10thFrameRoll2() {
        for (int i = 0; i < 20; i++) {
            game.roll(0);
        }
        game.roll(1);
        game.roll(9);
        game.roll(1);
        assertEquals(12, game.score());
    }

    @Test
    public void testStrikeOn10thFrameRoll2() {
        for (int i = 0; i < 20; i++) {
            game.roll(0);
        }
        game.roll(0);
        game.roll(10);
        game.roll(1);
        assertEquals(12, game.score());
    }

    @Test
    public void testStrikeOn10thFrameRoll1() {
        for (int i = 0; i < 20; i++) {
            game.roll(0);
        }
        game.roll(10);
        game.roll(1);
        game.roll(1);
        assertEquals(14, game.score());
    }

    @Test
    public void testRoll_negative1() {
        var exception = assertThrows(
                InvalidParameterException.class,
                () -> game.roll(-1)
        );
        assertEquals(exception.getMessage(), "Can not roll less than 0");
    }

    @Test
    public void testNoStrikeOrSpareOn10thFrame() {
        for (int i = 0; i < 22; i++) {
            game.roll(1);
        }
        var exception = assertThrows(
                IllegalStateException.class,
                () -> game.roll(1)
        );
        assertEquals(exception.getMessage(), "To many rolls for a game");
    }

}
