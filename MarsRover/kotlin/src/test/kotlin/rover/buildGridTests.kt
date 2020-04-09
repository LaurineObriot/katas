package rover

import rover.domain.*
import rover.domain.Orientation.*
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class BuildGridTests : StringSpec({
    "Build an empty 1x1 grid, with rover isAt 0,0 facing north" {
        buildGrid(
            Grid(
                1,
                RoverCoordinates(0, 0, NORTH),
                GoalCoordinates(10, 10), // Outside
                emptyList()
            )
        )(RoverCoordinates(0, 0, NORTH)) shouldBe (
            listOf(
                "  0  ",
                " +--+",
                "0|△ |",
                " +--+"
            )
            )
    }
    "Build an empty 2x2 grid, with rover isAt 0,0 facing north" {
        buildGrid(
            Grid(
                2,
                RoverCoordinates(0, 0, NORTH),
                GoalCoordinates(10, 10), // Outside
                emptyList()
            )
        )(RoverCoordinates(0, 0, NORTH)) shouldBe (
            listOf(
                "  0 1  ",
                " +----+",
                "0|△   |",
                "1|    |",
                " +----+"
            )
            )
    }
    "Build an empty 3x3 grid, with rover isAt 0,0 facing north" {
        buildGrid(
            Grid(
                3,
                RoverCoordinates(0, 0, NORTH),
                GoalCoordinates(10, 10), // Outside
                emptyList()
            )
        )(RoverCoordinates(0, 0, NORTH)) shouldBe (
            listOf(
                "  0 1 2  ",
                " +------+",
                "0|△     |",
                "1|      |",
                "2|      |",
                " +------+"
            )
            )
    }
    "Build an empty 10x10 grid, with rover isAt 0,0 facing north" {
        buildGrid(
            Grid(
                10,
                RoverCoordinates(0, 0, NORTH),
                GoalCoordinates(10, 10), // Outside
                emptyList()
            )
        )(RoverCoordinates(0, 0, NORTH)) shouldBe (
            listOf(
                "  0 1 2 3 4 5 6 7 8 9  ",
                " +--------------------+",
                "0|△                   |",
                "1|                    |",
                "2|                    |",
                "3|                    |",
                "4|                    |",
                "5|                    |",
                "6|                    |",
                "7|                    |",
                "8|                    |",
                "9|                    |",
                " +--------------------+"
            )
            )
    }
    "Build an empty 10x10 grid, with rover isAt 1,0 facing north" {
        buildGrid(
            Grid(
                10,
                RoverCoordinates(0, 0, NORTH),
                GoalCoordinates(10, 10), // Outside
                emptyList()
            )
        )(RoverCoordinates(1, 0, NORTH)) shouldBe (
            listOf(
                "  0 1 2 3 4 5 6 7 8 9  ",
                " +--------------------+",
                "0|                    |",
                "1|△                   |",
                "2|                    |",
                "3|                    |",
                "4|                    |",
                "5|                    |",
                "6|                    |",
                "7|                    |",
                "8|                    |",
                "9|                    |",
                " +--------------------+"
            )
            )
    }
    "Build an empty 10x10 grid, with rover isAt 1,2 facing north" {
        buildGrid(
            Grid(
                10,
                RoverCoordinates(0, 0, NORTH),
                GoalCoordinates(10, 10), // Outside
                emptyList()
            )
        )(RoverCoordinates(1, 2, NORTH)) shouldBe (
            listOf(
                "  0 1 2 3 4 5 6 7 8 9  ",
                " +--------------------+",
                "0|                    |",
                "1|    △               |",
                "2|                    |",
                "3|                    |",
                "4|                    |",
                "5|                    |",
                "6|                    |",
                "7|                    |",
                "8|                    |",
                "9|                    |",
                " +--------------------+"
            )
            )
    }
    "Build an empty 10x10 grid, with rover isAt 9,9 facing north" {
        buildGrid(
            Grid(
                10,
                RoverCoordinates(0, 0, NORTH),
                GoalCoordinates(10, 10), // Outside
                emptyList()
            )
        )(RoverCoordinates(9, 9, NORTH)) shouldBe (
            listOf(
                "  0 1 2 3 4 5 6 7 8 9  ",
                " +--------------------+",
                "0|                    |",
                "1|                    |",
                "2|                    |",
                "3|                    |",
                "4|                    |",
                "5|                    |",
                "6|                    |",
                "7|                    |",
                "8|                    |",
                "9|                  △ |",
                " +--------------------+"
            )
            )
    }
    "Build an empty 10x10 grid, with rover isAt 9,9 facing south" {
        buildGrid(
            Grid(
                10,
                RoverCoordinates(0, 0, NORTH),
                GoalCoordinates(10, 10), // Outside
                emptyList()
            )
        )(RoverCoordinates(9, 9, SOUTH)) shouldBe (
            listOf(
                "  0 1 2 3 4 5 6 7 8 9  ",
                " +--------------------+",
                "0|                    |",
                "1|                    |",
                "2|                    |",
                "3|                    |",
                "4|                    |",
                "5|                    |",
                "6|                    |",
                "7|                    |",
                "8|                    |",
                "9|                  ▽ |",
                " +--------------------+"
            )
            )
    }
    "Build an empty 10x10 grid, with rover isAt 9,9 facing east" {
        buildGrid(
            Grid(
                10,
                RoverCoordinates(0, 0, NORTH),
                GoalCoordinates(10, 10), // Outside
                emptyList()
            )
        )(RoverCoordinates(9, 9, EAST)) shouldBe (
            listOf(
                "  0 1 2 3 4 5 6 7 8 9  ",
                " +--------------------+",
                "0|                    |",
                "1|                    |",
                "2|                    |",
                "3|                    |",
                "4|                    |",
                "5|                    |",
                "6|                    |",
                "7|                    |",
                "8|                    |",
                "9|                  ▷ |",
                " +--------------------+"
            )
            )
    }
    "Build an empty 10x10 grid, with rover isAt 9,9 facing west" {
        buildGrid(
            Grid(
                10,
                RoverCoordinates(0, 0, NORTH),
                GoalCoordinates(10, 10), // Outside
                emptyList()
            )
        )(RoverCoordinates(9, 9, WEST)) shouldBe (
            listOf(
                "  0 1 2 3 4 5 6 7 8 9  ",
                " +--------------------+",
                "0|                    |",
                "1|                    |",
                "2|                    |",
                "3|                    |",
                "4|                    |",
                "5|                    |",
                "6|                    |",
                "7|                    |",
                "8|                    |",
                "9|                  ◁ |",
                " +--------------------+"
            )
            )
    }
    "Build 10x10 grid with rocks isAt 1,1 and 2,2 " {
        buildGrid(
            Grid(
                10,
                RoverCoordinates(0, 0, NORTH),
                GoalCoordinates(10, 10), // Outside
                listOf(
                    RockCoordinates(1,1),
                    RockCoordinates(2,2)
                )
            )
        )(RoverCoordinates(0, 0, NORTH)) shouldBe (
            listOf(
                "  0 1 2 3 4 5 6 7 8 9  ",
                " +--------------------+",
                "0|△                   |",
                "1|  ◆                 |",
                "2|    ◆               |",
                "3|                    |",
                "4|                    |",
                "5|                    |",
                "6|                    |",
                "7|                    |",
                "8|                    |",
                "9|                    |",
                " +--------------------+"
            )
            )
    }
    "Build 10x10 grid with goal isAt 1,1" {
        buildGrid(
            Grid(
                10,
                RoverCoordinates(0, 0, NORTH),
                GoalCoordinates(1, 1), // Outside
                emptyList()
            )
        )(RoverCoordinates(0, 0, NORTH)) shouldBe (
            listOf(
                "  0 1 2 3 4 5 6 7 8 9  ",
                " +--------------------+",
                "0|△                   |",
                "1|  ☆                 |",
                "2|                    |",
                "3|                    |",
                "4|                    |",
                "5|                    |",
                "6|                    |",
                "7|                    |",
                "8|                    |",
                "9|                    |",
                " +--------------------+"
            )
            )
    }
})