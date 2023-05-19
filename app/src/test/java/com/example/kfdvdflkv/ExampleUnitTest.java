package com.example.kfdvdflkv;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testSwipeLeft() {
        Game game = new Game();
        game.setValue(0, 3, 2);
        game.swipeLeft();
        assertEquals(game.getValue(0,0), 2);
    }

    @Test
    public void testSwipeRight() {
        Game game = new Game();
        game.setValue(0, 0, 2);
        game.swipeRight();
        assertEquals(game.getValue(0,3), 2);
    }

    @Test
    public void testSwipeUp() {
        Game game = new Game();
        game.setValue(3, 0, 2);
        game.swipeUp();
        assertEquals(game.getValue(0,0), 2);
    }

    @Test
    public void testSwipeDown() {
        Game game = new Game();
        game.setValue(0, 0, 8);
        game.swipeDown();
        assertEquals(game.getValue(3,0), 8);
    }

    @Test
    public void stackDown() {
        Game game = new Game();
        game.setValue(0,0,16);
        game.setValue(1,0,16);
        game.swipeDown();
        assertEquals(game.getValue(3,0), 32);
    }

    @Test
    public void newDigit() {
        Game game = new Game();
        game.spawnDigit();
        assertTrue(game.assertDigit());
    }
}
