package com.example.kfdvdflkv;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    Game game = new Game();
    public boolean assertDigit() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if (game.field[i][j] != 0) return true;
            }
        }
        return false;
    }
    @Test
    public void testSwipeLeft() {
        game.setValue(0, 3, 2);
        game.swipeLeft();
        assertEquals(game.getValue(0,0), 2);
    }

    @Test
    public void testSwipeRight() {
        game.setValue(0, 0, 2);
        game.swipeRight();
        assertEquals(game.getValue(0,3), 2);
    }

    @Test
    public void testSwipeUp() {
        game.setValue(3, 0, 2);
        game.swipeUp();
        assertEquals(game.getValue(0,0), 2);
    }

    @Test
    public void testSwipeDown() {
        game.setValue(0, 0, 8);
        game.swipeDown();
        assertEquals(game.getValue(3,0), 8);
    }

    @Test
    public void stackDown() {
        game.setValue(0,0,16);
        game.setValue(1,0,16);
        game.swipeDown();
        assertEquals(game.getValue(3,0), 32);
    }

    @Test
    public void newDigit() {
        game.spawnDigit();
        assertTrue(assertDigit());
    }
}
