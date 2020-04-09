package rover

import rover.domain.RouteComputation.*
import rover.domain.Command
import rover.domain.Grid
import rover.domain.RouteComputation
import rover.domain.RoverCoordinates

fun run(
    grid: Grid,
    computeRoute: (grid: Grid, move: (RoverCoordinates, Command) -> RoverCoordinates) -> RouteComputation,
    move: (RoverCoordinates, Command) -> RoverCoordinates,
    buildGrid: (RoverCoordinates) -> List<String>,
    presenter: Presenter
) {
    presenter.presentGrid(buildGrid(grid.roverInitialCoordinates))

    when (val result = computeRoute(grid, move)) {
        is Route -> runCommandSequence(
            grid,
            result.commands,
            move,
            buildGrid,
            presenter
        )
        is RouteNotFound -> presenter.presentPathNotFound()
    }
}

private fun runCommandSequence(
    grid: Grid,
    commands: List<Command>,
    move: (RoverCoordinates, Command) -> RoverCoordinates,
    buildGrid: (RoverCoordinates) -> List<String>,
    presenter: Presenter
) {
    var nextRoverCoordinates = grid.roverInitialCoordinates
    commands.forEach {command ->

        nextRoverCoordinates = move(nextRoverCoordinates, command)

        if (nextRoverCoordinates isAtAny grid.rocksCoordinates) {
            presenter.presentRockEncounteredMessage()
            return
        } else {
            presenter.presentGrid(buildGrid(nextRoverCoordinates))

            if (nextRoverCoordinates isAt grid.goalCoordinates) {
                presenter.presentGoalReachedMessage()
                return
            }
        }
    }
}

interface Presenter {
    fun presentGrid(grid: List<String>)
    fun presentRockEncounteredMessage()
    fun presentGoalReachedMessage()
    fun presentPathNotFound()
}
