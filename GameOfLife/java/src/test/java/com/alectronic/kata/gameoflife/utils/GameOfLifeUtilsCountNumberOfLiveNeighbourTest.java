package com.alectronic.kata.gameoflife.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static com.alectronic.kata.gameoflife.utils.GameOfLifeUtils.countNumberOfLiveNeighbour;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class GameOfLifeUtilsCountNumberOfLiveNeighbourTest {

    private final int x;
    private final int y;
    private final int[] expectResult;
    private final static boolean[][][] worlds = new boolean[][][]{
            {
                    {false, false, false},
                    {false, false, false},
                    {false, false, false}
            },
            {
                    {true, false, false},
                    {false, false, false},
                    {false, false, false}
            },
            {
                    {true, true, false},
                    {false, false, false},
                    {false, false, false}
            },
            {
                    {true, true, true},
                    {false, false, false},
                    {false, false, false}
            },
            {
                    {true, true, true},
                    {true, false, false},
                    {false, false, false}
            },
            {
                    {true, true, true},
                    {true, true, false},
                    {false, false, false}
            },
            {
                    {true, true, true},
                    {true, true, true},
                    {false, false, false}
            },
            {
                    {true, true, true},
                    {true, true, true},
                    {true, false, false}
            },
            {
                    {true, true, true},
                    {true, true, true},
                    {true, true, false}
            },
            {
                    {true, true, true},
                    {true, true, true},
                    {true, true, true}
            },
    };

    public GameOfLifeUtilsCountNumberOfLiveNeighbourTest(int x, int y, int[] expectResult) {
        this.x = x;
        this.y = y;
        this.expectResult = expectResult;
    }

    @Parameterized.Parameters
    public static Collection input() {
        return Arrays.asList(new Object[][] {
                { 0, 0, new int[]{0, 0, 1, 1, 2, 3, 3, 3, 3, 3} },
                { 0, 1, new int[]{0, 1, 1, 2, 3, 4, 5, 5, 5, 5} },
                { 0, 2, new int[]{0, 0, 1, 1, 1, 2, 3, 3, 3, 3} },
                { 1, 0, new int[]{0, 1, 2, 2, 2, 3, 3, 4, 5, 5} },
                { 1, 1, new int[]{0, 1, 2, 3, 4, 4, 5, 6, 7, 8} },
                { 1, 2, new int[]{0, 0, 1, 2, 2, 3, 3, 3, 4, 5} },
                { 2, 0, new int[]{0, 0, 0, 0, 1, 2, 2, 2, 3, 3} },
                { 2, 1, new int[]{0, 0, 0, 0, 1, 2, 3, 4, 4, 5} },
                { 2, 2, new int[]{0, 0, 0, 0, 0, 1, 2, 2, 3, 3} }
        });
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Counting Neighbours (0,0)
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void shouldCountNoAgents() {
        assertEquals(expectResult[0],countNumberOfLiveNeighbour(x,y,worlds[0]));
    }

    @Test
    public void worldHasOneAgents() {
        assertEquals(expectResult[1],countNumberOfLiveNeighbour(x,y,worlds[1]));
    }

    @Test
    public void worldHasTwoAgents() {
        assertEquals(expectResult[2],countNumberOfLiveNeighbour(x,y,worlds[2]));
    }

    @Test
    public void worldHasThreeAgents() {
        assertEquals(expectResult[3],countNumberOfLiveNeighbour(x,y,worlds[3]));
    }

    @Test
    public void worldHasFourAgents() {
        assertEquals(expectResult[4],countNumberOfLiveNeighbour(x,y,worlds[4]));
    }

    @Test
    public void worldHasFiveAgents() {
        assertEquals(expectResult[5],countNumberOfLiveNeighbour(x,y,worlds[5]));
    }

    @Test
    public void worldHasSixAgents() {
        assertEquals(expectResult[6],countNumberOfLiveNeighbour(x,y,worlds[6]));
    }

    @Test
    public void worldHasSevenAgents() {
        assertEquals(expectResult[7],countNumberOfLiveNeighbour(x,y,worlds[7]));
    }

    @Test
    public void worldHasEightShould() {
        assertEquals(expectResult[8],countNumberOfLiveNeighbour(x,y,worlds[8]));
    }

    @Test
    public void worldHasNineAgents() {
        assertEquals(expectResult[9],countNumberOfLiveNeighbour(x,y,worlds[9]));
    }

}