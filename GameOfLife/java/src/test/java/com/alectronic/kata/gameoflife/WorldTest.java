package com.alectronic.kata.gameoflife;

import com.alectronic.kata.gameoflife.exception.DeadWorldException;
import com.alectronic.kata.gameoflife.utils.GameOfLifeUtils;
import com.alectronic.kata.gameoflife.utils.PrinterImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorldTest {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// Constructor
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void initBaseWorld() {
        World world = new World(new boolean[1][1]);

        String expectedWorld = "Step: 0\n" + " . \n";
        String currentWorld = world.toString();

        assertEquals(expectedWorld,currentWorld);
    }

    @Test
    public void initBaseWorld2() {
        World world = new World(new boolean[2][2]);

        String expectedWorld = "Step: 0\n" + " .  . \n" + " .  . \n";
        String currentWorld = world.toString();

        assertEquals(expectedWorld,currentWorld);
    }

    @Test
    public void initBaseWorld3WithLiveCell() {
        boolean[][] cells =  new boolean[][]{
                {true, false},
                {false, false},
        };

        World world = new World(cells);

        String expectedWorld = "Step: 0\n" + " *  . \n" + " .  . \n";
        String currentWorld = world.toString();

        assertEquals(expectedWorld,currentWorld);
    }

    @Test
    public void yAxis() {
        boolean[][] cells = new boolean[][]{
                {true, false, false},
                {true, false, false},
                {true, false, false}
        };

        World world = new World(cells);

        String expectedWorld = "Step: 0\n" + " *  .  . \n"  + " *  .  . \n" + " *  .  . \n";
        String currentWorld = world.toString();

        assertEquals(expectedWorld,currentWorld);
    }

    @Test
    public void xAxis() {
        boolean[][] cells = new boolean[][]{
                {true, true, true},
                {false, false, false},
                {false, false, false}
        };

        World world = new World(cells);

        String expectedWorld = "Step: 0\n" + " *  *  * \n" + " .  .  . \n" + " .  .  . \n";
        String currentWorld = world.toString();

        assertEquals(expectedWorld,currentWorld);
    }

    @Test
    public void middle() {
        boolean[][] cells = new boolean[][]{
                {false, false, false},
                {false, true, false},
                {false, false, false}
        };

        World world = new World(cells);

        String expectedWorld = "Step: 0\n" + " .  .  . \n" + " .  *  . \n" +  " .  .  . \n";
        String currentWorld = world.toString();

        assertEquals(expectedWorld,currentWorld);
    }

    @Test
    public void initRandomBaseWorld() {
        World world = new World();

        boolean[][] state = world.getState();

        assertEquals(20,state.length);
        assertEquals(20,state[0].length);
    }

    @Test
    public void initRandomBaseWorldAlwaysTrueGenerator() {
        World world = new World(1, 1, new PrinterImpl(), () -> true);

        boolean[][] state = world.getState();

        assertEquals(1,state.length);
        assertEquals(1,state[0].length);
        assertTrue(state[0][0]);
    }

    @Test
    public void initRandomBaseWorldAlwaysFalseGenerator() {
        World world = new World(1, 1, new PrinterImpl(), () -> false);

        boolean[][] state = world.getState();

        assertEquals(1,state.length);
        assertEquals(1,state[0].length);
        assertFalse(state[0][0]);
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// Scenarios
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(expected = DeadWorldException.class)
    public void shouldRemainDeadAndThrowDeadWorldException() throws DeadWorldException {
        boolean[][] cells = new boolean[][]{
                {false, false, false},
                {false, false, false},
                {false, false, false}
        };

        World world = new World(cells);
        world.print();
        world.step();
        world.print();
        boolean[][] currentWorld = world.getState();

        boolean[][] expectedWorld = new boolean[][]{
                {false, false, false},
                {false, false, false},
                {false, false, false}
        };

        assertArrayEquals("Expect:\n" + GameOfLifeUtils.toString(expectedWorld),expectedWorld,currentWorld);}

    @Test
    public void shouldDieOfUnderPopulation() throws DeadWorldException {
        boolean[][] cells = new boolean[][]{
                {false, true, false},
                {true, false, false},
                {false, false, false}
        };

        World world = new World(cells);
        world.print();
        world.step();
        world.print();
        boolean[][] currentWorld = world.getState();

        boolean[][] expectedWorld = new boolean[][]{
                {false, false, false},
                {false, false, false},
                {false, false, false}
        };

        assertArrayEquals("Expect:\n" + GameOfLifeUtils.toString(expectedWorld),expectedWorld,currentWorld);}

    @Test
    public void shouldCreateLife() throws DeadWorldException {
        boolean[][] cells = new boolean[][]{
                {false, true, false},
                {true, true, false},
                {false, false, false}
        };

        World world = new World(cells);
        world.print();
        world.step();
        world.print();
        boolean[][] currentWorld = world.getState();

        boolean[][] expectedWorld = new boolean[][]{
                {true, true, false},
                {true, true, false},
                {false, false, false}
        };

        assertArrayEquals("Expect:\n" + GameOfLifeUtils.toString(expectedWorld),expectedWorld,currentWorld);
    }

    @Test
    public void shouldCreateLifeButOnlyOneOtherLivesInProcess() throws DeadWorldException {
        boolean[][] cells = new boolean[][]{
                {false, true, false},
                {true, false, true},
                {false, false, false}
        };

        World world = new World(cells);
        world.print();
        world.step();
        world.print();
        boolean[][] currentWorld = world.getState();

        boolean[][] expectedWorld = new boolean[][]{
                {false, true, false},
                {false, true, false},
                {false, false, false}
        };

        assertArrayEquals("Expect:\n" + GameOfLifeUtils.toString(expectedWorld),expectedWorld,currentWorld);
    }

    @Test
    public void shouldCreateLifeButDieInProcess() throws DeadWorldException {
        boolean[][] cells = new boolean[][]{
                {true, false, false},
                {false, false, true},
                {true, false, false}
        };

        World world = new World(cells);
        world.print();
        world.step();
        world.print();
        boolean[][] currentWorld = world.getState();

        boolean[][] expectedWorld = new boolean[][]{
                {false, false, false},
                {false, true, false},
                {false, false, false}
        };

        assertArrayEquals("Expect:\n" + GameOfLifeUtils.toString(expectedWorld),expectedWorld,currentWorld);
    }


    @Test
    public void test() throws DeadWorldException {
        boolean[][] cells = new boolean[][]{
                {true, true, true},
                {true, true, true},
                {false, false, false}
        };

        World world = new World(cells);
        world.print();
        world.step();
        world.print();
        boolean[][] currentWorld = world.getState();

        boolean[][] expectedWorld = new boolean[][]{
                {true, false, true},
                {true, false, true},
                {false, true, false}
        };

        assertArrayEquals("Expect:\n" + GameOfLifeUtils.toString(expectedWorld),expectedWorld,currentWorld);
    }

    @Test
    public void shouldKillOvercrowded() throws DeadWorldException {
        boolean[][] cells = new boolean[][]{
                {true, true, true},
                {true, true, true},
                {true, true, true}
        };

        World world = new World(cells);
        world.print();
        world.step();
        world.print();
        boolean[][] currentWorld = world.getState();

        boolean[][] expectedWorld = new boolean[][]{
                {true, false, true},
                {false, false, false},
                {true, false, true}
        };

        assertArrayEquals("Expect:\n" + GameOfLifeUtils.toString(expectedWorld),expectedWorld,currentWorld);
    }

}