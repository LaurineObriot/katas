package rover

import rover.domain.RouteComputation.*
import rover.domain.*
import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import kotlin.random.Random

class ComputeRouteTests : StringSpec() {
    companion object {
        private const val NUMBER_OF_TESTS = 10
        private const val GRID_SIZE = 10
        private const val NUMBER_OF_ROCKS = 10
    }

    init {
        "A route to goal is found on randomly generated grids" {
            forAll(NUMBER_OF_TESTS, GridGenerator()) { grid : Grid ->
                val presenter = CommandLinePresenter()
                presenter.presentGrid(buildGrid(grid)(grid.roverInitialCoordinates))
                computeRoute(grid, move(GRID_SIZE)) is Route
            }
        }
    }

    class GridGenerator : Gen<Grid> {
        override fun constants() = emptyList<Grid>()
        override fun random() = generateSequence {
            generateRandomGrid(
                GRID_SIZE,
                NUMBER_OF_ROCKS,
                { Random.nextInt(10) },
                { Orientation.values().random() }
            )
        }
    }
}
