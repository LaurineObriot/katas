package com.alectronic.kata.gameoflife.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static com.alectronic.kata.gameoflife.utils.GameOfLifeUtils.isCellAlive;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class GameOfLifeUtilsIsAliveTest {

    private final int numberOfLiveNeighbour;
    private final boolean isCurrentCellAlive;
    private final boolean expectResult;

    public GameOfLifeUtilsIsAliveTest(int numberOfLiveNeighbour, boolean isCurrentCellAlive, boolean expectResult) {
        this.numberOfLiveNeighbour = numberOfLiveNeighbour;
        this.isCurrentCellAlive = isCurrentCellAlive;
        this.expectResult = expectResult;
    }

    @Parameterized.Parameters
    public static Collection input() {
        return Arrays.asList(new Object[][] {
//                anyLiveCellWithMoreThanThreeLiveNeighboursDiesAsIfByOvercrowding
                {4, true, false},
                {5, true, false},
                {6, true, false},
                {7, true, false},
                {8, true, false},
//                anyLiveCellWithTwoOrThreeLiveNeighboursLivesOnToTheNextGeneration
                {2, true, true},
                {3, true, true},
//                anyDeadCellWithExactlyThreeLiveNeighboursBecomesALiveCell
                {3, false, true},
//                AnyLiveCellWithFewerThanTwoLiveNeighboursDiesAsIfCausedByUnderpopulation
                {0, true, false},
                {1, true, false},
//                noMatchingState
                {0, false, false},
                {1, false, false},
                {2, false, false},
                {4, false, false},
                {5, false, false},
                {6, false, false},
                {7, false, false},
                {8, false, false}
        });
    }

    @Test
    public void testIsCellAlive() {
        assertEquals(expectResult, isCellAlive(numberOfLiveNeighbour, isCurrentCellAlive));
    }

}