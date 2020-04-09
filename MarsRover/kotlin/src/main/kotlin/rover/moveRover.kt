package rover

import rover.domain.Command.*
import rover.domain.Orientation.*
import rover.domain.Command
import rover.domain.Orientation
import rover.domain.RoverCoordinates

fun move(gridSize: Int): (RoverCoordinates, Command) -> RoverCoordinates =
    { coordinates, command ->
        val increase = increase(gridSize)
        val decrease = decrease(gridSize)
        computeCoordinates(command, coordinates, decrease, increase)
    }

private fun computeCoordinates(
    command: Command,
    coordinates: RoverCoordinates,
    decrease: (Int) -> Int,
    increase: (Int) -> Int
): RoverCoordinates {
    return when (command) {
        RIGHT -> coordinates.copy(orientation = coordinates.orientation.rotateRight())
        LEFT -> coordinates.copy(orientation = coordinates.orientation.rotateLeft())
        FORWARD -> moveForward(coordinates, increase, decrease)
        BACKWARD -> moveBackward(coordinates, increase, decrease)
    }
}

private fun moveBackward(coordinates: RoverCoordinates, increase: (Int) -> Int, decrease: (Int) -> Int): RoverCoordinates {
    return when (coordinates.orientation) {
        NORTH -> coordinates.copy(y = increase(coordinates.y))
        EAST -> coordinates.copy(x = decrease(coordinates.x))
        SOUTH -> coordinates.copy(y = decrease(coordinates.y))
        WEST -> coordinates.copy(x = increase(coordinates.x))
    }
}

private fun moveForward(coordinates: RoverCoordinates, increase: (Int) -> Int, decrease: (Int) -> Int): RoverCoordinates {
    return when (coordinates.orientation) {
        NORTH -> coordinates.copy(y = decrease(coordinates.y))
        EAST -> coordinates.copy(x = increase(coordinates.x))
        SOUTH -> coordinates.copy(y = increase(coordinates.y))
        WEST -> coordinates.copy(x = decrease(coordinates.x))
    }
}

private fun Orientation.rotateRight() = when (this) {
    NORTH -> EAST
    EAST -> SOUTH
    SOUTH -> WEST
    WEST -> NORTH
}

private fun Orientation.rotateLeft() = when (this) {
    NORTH -> WEST
    WEST -> SOUTH
    SOUTH -> EAST
    EAST -> NORTH
}

private fun increase(gridSize: Int) = { int: Int ->
    if (int + 1 <= gridSize - 1) {
        int + 1
    } else {
        0
    }
}

private fun decrease(gridSize: Int) = { int: Int ->
    if (int - 1 >= 0) {
        int - 1
    } else {
        gridSize - 1
    }
}
