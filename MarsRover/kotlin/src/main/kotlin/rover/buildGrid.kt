package rover

import rover.domain.*
import rover.domain.Orientation.*

fun buildGrid(
    grid: Grid
): (roverCoordinates: RoverCoordinates) -> List<String> =
    { coordinates: RoverCoordinates ->
        val topRow = buildTopHeaderRow(grid.size)
        val horizontalDivider = buildHorizontalDivider(grid.size)
        val middleRows = buildMiddleRows(grid, coordinates)
        listOf(topRow, horizontalDivider)
            .plus(middleRows)
            .plus(horizontalDivider)
    }

private fun buildHorizontalDivider(size: Int) = " +" + (0 until size).joinToString("") { "--" } + "+"

private fun buildTopHeaderRow(size: Int) = "  " + (0 until size).joinToString("") { "$it " } + " "

private fun buildMiddleRows(
    grid: Grid,
    roverCoordinates: RoverCoordinates
): List<String> {
    return (0 until grid.size).map { y ->
        "$y|" +
            (0 until grid.size).joinToString("") { x ->
                when {
                    roverCoordinates.isAt(y, x) -> buildRover(roverCoordinates.orientation)
                    grid.goalCoordinates.isAt(y, x) -> "☆ "
                    grid.rocksCoordinates.isAnyAt(y, x) -> "◆ "
                    else -> "  "
                }
            } + "|"
    }
}

private fun buildRover(orientation: Orientation): String {
    return when (orientation) {
        NORTH -> "△ "
        SOUTH -> "▽ "
        EAST -> "▷ "
        WEST -> "◁ "
    }
}

private fun List<RockCoordinates>.isAnyAt(y: Int, x: Int) : Boolean =
    this.any { it.x == x && it.y == y }


fun generateRandomGrid(
    size: Int,
    numberOfRocks: Int,
    randomInt: (Int) -> Int,
    randomOrientation: () -> Orientation
): Grid {
    val roverCoordinates = generateRandomRoverCoordinates(
        size,
        randomInt,
        randomOrientation
    )
    val goalCoordinates = generateRandomXYGoalCoordinates(
        size,
        roverCoordinates,
        randomInt
    )
    val rocksCoordinates = generateRandomXYRocksCoordinates(
        numberOfRocks,
        size,
        roverCoordinates,
        goalCoordinates,
        randomInt
    )
    return Grid(
        size,
        roverCoordinates,
        goalCoordinates,
        rocksCoordinates
    )
}

private fun generateRandomRoverCoordinates(
    gridSize: Int,
    randomInt: (Int) -> Int,
    randomOrientation: () -> Orientation
) =
    RoverCoordinates(
        randomInt(gridSize),
        randomInt(gridSize),
        randomOrientation()
    )

private fun generateRandomXYRocksCoordinates(
    number: Int,
    gridSize: Int,
    initialRoverCoordinates: RoverCoordinates,
    goalCoordinates: GoalCoordinates,
    randomInt: (Int) -> Int
) = (0 until number).map {
    generateRandomXYRockCoordinates(gridSize, initialRoverCoordinates, goalCoordinates, randomInt)
}

private fun generateRandomXYRockCoordinates(
    gridSize: Int,
    initialRoverCoordinates: RoverCoordinates,
    goalCoordinates: GoalCoordinates,
    randomInt: (Int) -> Int
): RockCoordinates {
    val rockCoordinates = RockCoordinates(
        randomInt(gridSize),
        randomInt(gridSize)
    )
    return if (
        rockCoordinates isAt initialRoverCoordinates
        || rockCoordinates isAt goalCoordinates
    ) {
        generateRandomXYRockCoordinates(gridSize, initialRoverCoordinates, goalCoordinates, randomInt)
    } else {
        rockCoordinates
    }
}

private fun generateRandomXYGoalCoordinates(
    gridSize: Int,
    initialRoverCoordinates: RoverCoordinates,
    randomInt: (Int) -> Int
): GoalCoordinates {
    val goalCoordinates = GoalCoordinates(
        randomInt(gridSize),
        randomInt(gridSize)
    )
    return if (goalCoordinates isAt initialRoverCoordinates) {
        generateRandomXYGoalCoordinates(gridSize, initialRoverCoordinates, randomInt)
    } else {
        goalCoordinates
    }
}