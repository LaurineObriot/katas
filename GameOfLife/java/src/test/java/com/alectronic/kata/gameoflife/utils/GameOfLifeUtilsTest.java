package com.alectronic.kata.gameoflife.utils;

import com.alectronic.kata.gameoflife.exception.MalformedLine;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class GameOfLifeUtilsTest {

    @Test
    public void initUtil(){
        new GameOfLifeUtils();
    }

    @Test
    public void parseArrayOfStringToWorldMap() throws MalformedLine {
        String[] stringWorld = {"...",".*.","..."};
        boolean[][] expectedWorld = {
                {false,false,false},
                {false,true,false},
                {false,false,false}
        };
        boolean[][] world = GameOfLifeUtils.convertStringToWorld(stringWorld);

        assertArrayEquals(expectedWorld,world);
    }

    @Test
    public void parseArrayOfStringToWorldMapWithDifferentModels() throws MalformedLine {
        String[] stringWorld = {"***","*X*","***"};
        boolean[][] expectedWorld = {
                {false,false,false},
                {false,true,false},
                {false,false,false}
        };
        boolean[][] world = GameOfLifeUtils.convertStringToWorld("X","*",stringWorld);

        assertArrayEquals(expectedWorld,world);
    }

    @Test(expected = MalformedLine.class)
    public void parseMalformedArrayOfStringToWorldMapWithDifferentModels() throws MalformedLine {
        String[] stringWorld = {"***","*.*","***"};
        boolean[][] expectedWorld = {
                {false,false,false},
                {false,true,false},
                {false,false,false}
        };
        boolean[][] world = GameOfLifeUtils.convertStringToWorld("X","*",stringWorld);

        assertArrayEquals(expectedWorld,world);
    }

    @Test(expected = MalformedLine.class)
    public void parseMalformedArrayOfStringToWorldMap() throws MalformedLine {
        String[] stringWorld = {"...",".X.","..."};
        boolean[][] expectedWorld = {
                {false,false,false},
                {false,true,false},
                {false,false,false}
        };
        boolean[][] world = GameOfLifeUtils.convertStringToWorld(stringWorld);

        assertArrayEquals(expectedWorld,world);
    }

}