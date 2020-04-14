package com.alectronic.kata.gameoflife.utils;

import com.alectronic.kata.gameoflife.exception.MalformedLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameOfLifeUtils {

    public static int countNumberOfLiveNeighbour(final int x, final int y, final boolean[][] cells) {
        int count = 0;
        for (int xOffSet = x-1; xOffSet <= x+1; xOffSet++) {
            for (int yOffSet = y-1; yOffSet <= y+1; yOffSet++) {
                if (
                        !(xOffSet == x && yOffSet == y)
                                && 0 <= xOffSet && xOffSet < cells.length
                                && 0 <= yOffSet && yOffSet < cells[xOffSet].length
                                && cells[xOffSet][yOffSet]
                ) {
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean isCellAlive(final int numberOfLiveNeighbour, final boolean isCurrentCellAlive) {
        return (
                isCurrentCellAlive
                        && 1 < numberOfLiveNeighbour
                        && numberOfLiveNeighbour < 4
        ) || (
                !isCurrentCellAlive
                        && numberOfLiveNeighbour == 3
        );
    }


    public static boolean[][] deepCopy(boolean[][] cells) {
        boolean[][] newWorld = new boolean[cells.length][cells[0].length] ;
        for (int x = 0; x < cells.length; x++) {
            System.arraycopy(cells[x], 0, newWorld[x], 0, cells[x].length);
        }
        return newWorld;
    }

    public static String toString(boolean[][] cells) {
        StringBuilder s = new StringBuilder();
        for (boolean[] cell : cells) {
            for (boolean b : cell) {
                s.append(b ? " * " : " . ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static boolean[][] getStateFromFile(String arg) throws IOException, MalformedLine {
        return GameOfLifeUtils.convertStringToWorld(new String(Files.readAllBytes(Paths.get(arg))).split("\n"));
    }

    public static boolean[][] convertStringToWorld(String[] lines) throws MalformedLine {
        return convertStringToWorld("*",".",lines);
    }

    public static boolean[][] convertStringToWorld(String alive, String dead, String[] lines) throws MalformedLine {
        boolean[][] booleanList = new boolean[lines.length][];
        for (int i = 0; i < lines.length; i++){
            booleanList[i] = convertStringToWorld(alive,dead,lines[i]);
        }
        return booleanList;
    }

    private static boolean[] convertStringToWorld(String alive, String dead, String line) throws MalformedLine {
        String[] x = line.split("");
        boolean[] booleanList = new boolean[x.length];
        for (int i = 0; i < x.length; i++){
            if(x[i].equals(alive)) {
                booleanList[i] = true;
            } else if(x[i].equals(dead)) {
                booleanList[i] = false;
            } else {
                throw new MalformedLine();
            }
        }
        return booleanList;
    }

}
