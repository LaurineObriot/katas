package rover

import rover.domain.*

fun computeRoute(
    grid: Grid,
    move: (RoverCoordinates, Command) -> RoverCoordinates
): RouteComputation {
    return infere(
        grid,
        move
    )(
        Inference.first(grid.roverInitialCoordinates)
    )
}

private fun infere(
    grid: Grid,
    move: (RoverCoordinates, Command) -> RoverCoordinates
): (inference: Inference) -> RouteComputation = { inference: Inference ->
    val reccursion = infere(
        grid,
        move
    )
    if (inference.isGoalReached(grid.goalCoordinates)) {
        RouteComputation.Route(inference.commandSequence())
    } else if (inference.isAtRocks(grid.rocksCoordinates)) {
        reccursion(inference.minusLastMove())
    } else {
        val command = mostRelevantCommandToTry(inference, grid.goalCoordinates, move)
        if (command != null) {
            val newCoordinates = move(inference.currentCoordinates, command)
            reccursion(inference.plusMove(newCoordinates, command))
        } else {
            if (inference.stillHasSomeUnexploredOptions()) {
                RouteComputation.RouteNotFound
            } else {
                reccursion(inference.backToLastUnexplored())
            }
        }
    }
}

internal data class Inference private constructor(
    val currentCoordinates: RoverCoordinates,
    val currentPath: List<Pair<RoverCoordinates, Command?>>,
    val exploredPath: List<Pair<RoverCoordinates, Command>>
) {
    companion object {
        fun first(currentCoordinates: RoverCoordinates): Inference = Inference(
            currentCoordinates,
            listOf(currentCoordinates to null),
            emptyList()
        )
    }

    fun isGoalReached(goalCoordinates: GoalCoordinates) =
        this.currentCoordinates isAt goalCoordinates

    fun isAtRocks(rocksCoordinates: List<RockCoordinates>) =
        this.currentCoordinates isAtAny rocksCoordinates

    fun commandSequence() = this.currentPath.mapNotNull { it.second }

    fun minusLastMove() = Inference(
        this.currentPath.last().first,
        this.currentPath.dropLast(1),
        this.exploredPath
    )

    fun backToLastUnexplored(): Inference {
        val previousMouvements = this.currentPath.dropLast(1)
        return Inference(
            previousMouvements.last().first,
            previousMouvements.dropLast(1),
            this.exploredPath
        )
    }

    fun stillHasSomeUnexploredOptions() = this.currentPath.size < 2

    fun plusMove(newCoordinates: RoverCoordinates, commandTried: Command) = Inference(
        newCoordinates,
        this.currentPath.plus(this.currentCoordinates to commandTried),
        this.exploredPath.plus(this.currentCoordinates to commandTried)
    )

    fun hasNeverBeenTried(command: Command) =
        this.exploredPath.none { trial ->
            trial.first == this.currentCoordinates && trial.second == command
        }
}

private fun mostRelevantCommandToTry(
    inference: Inference,
    goalCoordinates: GoalCoordinates,
    move: (RoverCoordinates, Command) -> RoverCoordinates
): Command? =
    Command.values()
        .filterNot { it == Command.BACKWARD }
        .orderByDistance(
            goalCoordinates,
            inference.currentCoordinates,
            move
        )
        .firstOrNull { command ->
            inference.hasNeverBeenTried(command)
        }


private fun List<Command>.orderByDistance(
    goalCoordinates: GoalCoordinates,
    roverCoordinates: RoverCoordinates,
    move: (RoverCoordinates, Command) -> RoverCoordinates
): List<Command> {
    return this.map {
        val nextPosition = when (it) {
            Command.FORWARD, Command.BACKWARD -> move(roverCoordinates, it)
            Command.RIGHT, Command.LEFT -> move(roverCoordinates, it).let { rotated -> move(rotated, Command.FORWARD) }
        }
        it to distance(nextPosition, goalCoordinates)
    }.sortedBy { it.second }.map { it.first }
}

private fun distance(roverCoordinates: RoverCoordinates, goalCoordinates: GoalCoordinates) =
    (Math.abs((goalCoordinates.x - roverCoordinates.x).toDouble())
        + Math.abs((goalCoordinates.y - roverCoordinates.y).toDouble()))

