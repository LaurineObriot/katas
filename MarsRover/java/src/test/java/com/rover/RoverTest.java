package com.rover;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class RoverTest {

    private Console console;

    @Before
    public void setUp() throws Exception {
        this.console = mock(Console.class);
        Rover.SPEED = 0;
    }

    @Test
    public void display_should_return_graphical_representation_of_default_initial_position() throws Exception {
        // given
        Position position = new Position(0, 0, 'N');

        // when
        Rover positionnement = new Rover(position, console);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0| ^                   |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void display_should_return_graphical_representation_of_custom_initial_position() throws Exception {
        // given
        Position position = new Position(1, 1, 'N');

        // when
        Rover positionnement = new Rover(position, console);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0|                     |\n" +
                "1|   ^                 |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void display_should_be_able_to_display_a_facing_direction_to_east() throws Exception {
        // given
        Position position = new Position(1, 2, 'E');

        // when
        Rover positionnement = new Rover(position, console);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0|                     |\n" +
                "1|                     |\n" +
                "2|   >                 |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void display_should_be_able_to_display_a_facing_direction_to_west() throws Exception {
        // given
        Position position = new Position(9, 9, 'W');

        // when
        Rover positionnement = new Rover(position, console);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0|                     |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                   < |\n" +
                " +---------------------+\n"
        );

    }


    @Test
    public void display_should_be_able_to_display_a_facing_direction_to_south() throws Exception {
        // given
        Position position = new Position(8, 6, 'S');

        // when
        Rover positionnement = new Rover(position, console);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0|                     |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                 v   |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_should_move_backward_when_facing_north() throws Exception {
        // given
        Position position = new Position(0, 0, 'N');

        // given
        Rover positionnement = new Rover(position, console);
        String[] commands = {"b"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0|                     |\n" +
                "1| ^                   |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_should_move_backward_when_facing_west() throws Exception {
        // given
        Position position = new Position(0, 0, 'W');

        // given
        Rover positionnement = new Rover(position, console);
        String[] commands = {"b"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0|   <                 |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_should_move_backward_when_facing_east() throws Exception {
        // given
        Position position = new Position(1, 0, 'E');

        // given
        Rover positionnement = new Rover(position, console);
        String[] commands = {"b"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0| >                   |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_should_move_backward_when_facing_south() throws Exception {
        // given
        Position position = new Position(0, 1, 'S');

        // given
        Rover positionnement = new Rover(position, console);
        String[] commands = {"b"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0| v                   |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_should_move_forward_when_facing_north() throws Exception {
        // given
        Position position = new Position(0, 1, 'N');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"f"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0| ^                   |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_should_move_forward_when_facing_west() throws Exception {
        // given
        Position position = new Position(1, 0, 'W');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"f"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0| <                   |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_should_move_forward_when_facing_east() throws Exception {
        // given
        Position position = new Position(0, 0, 'E');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"f"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0|   >                 |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_should_move_forward_when_facing_south() throws Exception {
        // given
        Position position = new Position(0, 0, 'S');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"f"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0|                     |\n" +
                "1| v                   |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_rotate_to_east_on_right_command() throws Exception {
        // given
        Position position = new Position(0, 0, 'N');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"r"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0| >                   |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_rotate_to_south_on_right_command() throws Exception {
        // given
        Position position = new Position(0, 0, 'E');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"r"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                        " +---------------------+\n" +
                        "0| v                   |\n" +
                        "1|                     |\n" +
                        "2|                     |\n" +
                        "3|                     |\n" +
                        "4|                     |\n" +
                        "5|                     |\n" +
                        "6|                     |\n" +
                        "7|                     |\n" +
                        "8|                     |\n" +
                        "9|                     |\n" +
                        " +---------------------+\n"
        );

    }

    @Test
    public void run_rotate_to_west_on_right_command() throws Exception {
        // given
        Position position = new Position(0, 0, 'S');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"r"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                        " +---------------------+\n" +
                        "0| <                   |\n" +
                        "1|                     |\n" +
                        "2|                     |\n" +
                        "3|                     |\n" +
                        "4|                     |\n" +
                        "5|                     |\n" +
                        "6|                     |\n" +
                        "7|                     |\n" +
                        "8|                     |\n" +
                        "9|                     |\n" +
                        " +---------------------+\n"
        );

    }

    @Test
    public void run_rotate_to_north_on_right_command() throws Exception {
        // given
        Position position = new Position(0, 0, 'W');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"r"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                        " +---------------------+\n" +
                        "0| ^                   |\n" +
                        "1|                     |\n" +
                        "2|                     |\n" +
                        "3|                     |\n" +
                        "4|                     |\n" +
                        "5|                     |\n" +
                        "6|                     |\n" +
                        "7|                     |\n" +
                        "8|                     |\n" +
                        "9|                     |\n" +
                        " +---------------------+\n"
        );

    }

    @Test
    public void run_rotate_to_west_on_left_command() throws Exception {
        // given
        Position position = new Position(0, 0, 'N');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"l"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0| <                   |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_rotate_to_south_on_left_command() throws Exception {
        // given
        Position position = new Position(0, 0, 'W');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"l"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0| v                   |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_rotate_to_east_on_left_command() throws Exception {
        // given
        Position position = new Position(0, 0, 'S');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"l"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                        " +---------------------+\n" +
                        "0| >                   |\n" +
                        "1|                     |\n" +
                        "2|                     |\n" +
                        "3|                     |\n" +
                        "4|                     |\n" +
                        "5|                     |\n" +
                        "6|                     |\n" +
                        "7|                     |\n" +
                        "8|                     |\n" +
                        "9|                     |\n" +
                        " +---------------------+\n"
        );

    }

    @Test
    public void run_rotate_to_north_on_left_command() throws Exception {
        // given
        Position position = new Position(0, 0, 'E');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"l"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                        " +---------------------+\n" +
                        "0| ^                   |\n" +
                        "1|                     |\n" +
                        "2|                     |\n" +
                        "3|                     |\n" +
                        "4|                     |\n" +
                        "5|                     |\n" +
                        "6|                     |\n" +
                        "7|                     |\n" +
                        "8|                     |\n" +
                        "9|                     |\n" +
                        " +---------------------+\n"
        );
    }

    @Test
    public void run_should_move_the_rover_forward_to_the_over_side_of_the_planet() throws Exception {
        // given
        Position position = new Position(0, 0, 'N');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"f"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0|                     |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9| ^                   |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_should_move_the_rover_backward_to_the_over_side_of_the_planet() throws Exception {
        // given
        Position position = new Position(0, 9, 'N');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"b"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                        " +---------------------+\n" +
                        "0| ^                   |\n" +
                        "1|                     |\n" +
                        "2|                     |\n" +
                        "3|                     |\n" +
                        "4|                     |\n" +
                        "5|                     |\n" +
                        "6|                     |\n" +
                        "7|                     |\n" +
                        "8|                     |\n" +
                        "9|                     |\n" +
                        " +---------------------+\n"
        );

    }


    @Test
    public void run_should_move_the_rover_forward_to_the_over_side_of_the_planet_east_direction() throws Exception {
        // given
        Position position = new Position(9, 0, 'E');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"f"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0| >                   |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_should_move_the_rover_backward_to_the_over_side_of_the_planet_east_direction() throws Exception {
        // given
        Position position = new Position(0, 0, 'E');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"b"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0|                   > |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_should_move_the_rover_forward_to_the_over_side_of_the_planet_west_direction() throws Exception {
        // given
        Position position = new Position(0, 0, 'W');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"f"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0|                   < |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_should_move_the_rover_backward_to_the_over_side_of_the_planet_west_direction() throws Exception {
        // given
        Position position = new Position(9, 0, 'W');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"b"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0| <                   |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_should_move_the_rover_forward_to_the_over_side_of_the_planet_south_direction() throws Exception {
        // given
        Position position = new Position(0, 9, 'S');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"f"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0| v                   |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_should_move_the_rover_backward_to_the_over_side_of_the_planet_south_direction() throws Exception {
        // given
        Position position = new Position(0, 0, 'S');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"b"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0|                     |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9| v                   |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_on_multiple_commands() throws Exception {
        // given
        Position position = new Position(1, 1, 'S');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"f", "f", "r", "f"};

        // when
        positionnement.run(commands);

        // then
        assertThat(positionnement.display()).isEqualTo(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0|                     |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3| <                   |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n"
        );

    }

    @Test
    public void run_call_multiple_times_the_console() throws Exception {
        // given
        Position position = new Position(1, 1, 'S');
        Rover positionnement = new Rover(position, console);
        String[] commands = {"f", "f", "r", "f"};

        // when
        positionnement.run(commands);

        // then
        verify(console, times(10)).print(anyString());
        InOrder inOrder = inOrder(console);
        inOrder.verify(console).print(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0|                     |\n" +
                "1|   v                 |\n" +
                "2|                     |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n");
        inOrder.verify(console).print(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0|                     |\n" +
                "1|                     |\n" +
                "2|   v                 |\n" +
                "3|                     |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n");

        inOrder.verify(console).print(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0|                     |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|   v                 |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n");

        inOrder.verify(console).print(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0|                     |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3|   <                 |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n");

        inOrder.verify(console).print(
                "   0 1 2 3 4 5 6 7 8 9\n" +
                " +---------------------+\n" +
                "0|                     |\n" +
                "1|                     |\n" +
                "2|                     |\n" +
                "3| <                   |\n" +
                "4|                     |\n" +
                "5|                     |\n" +
                "6|                     |\n" +
                "7|                     |\n" +
                "8|                     |\n" +
                "9|                     |\n" +
                " +---------------------+\n");

    }

}
