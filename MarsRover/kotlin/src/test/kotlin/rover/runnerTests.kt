package rover

import rover.domain.RouteComputation.*
import rover.domain.*
import rover.domain.Orientation.NORTH
import io.kotlintest.TestCase
import io.kotlintest.specs.StringSpec
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence

class RunnerTests : StringSpec() {
    private lateinit var move: (RoverCoordinates, Command) -> RoverCoordinates
    private lateinit var buildGrid: (RoverCoordinates) -> List<String>
    private lateinit var computeRoute: (grid: Grid, move: (RoverCoordinates, Command) -> RoverCoordinates) -> RouteComputation
    private lateinit var presenter: Presenter

    private val initialRoverCoordinates = RoverCoordinates(1, 1, NORTH)
    private val goalCoordinates = GoalCoordinates(9, 9)
    private val rocksCoordinates = emptyList<RockCoordinates>()
    private val grid = listOf("grid")
    private val command = Command.LEFT
    private val gridParameters = Grid(
        10,
        initialRoverCoordinates,
        goalCoordinates,
        rocksCoordinates
    )

    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)
        // init computeRoute
        computeRoute = mockk()

        // init move mocks
        move = mockk()
        every {
            move.invoke(initialRoverCoordinates, command)
        } returns initialRoverCoordinates

        // init buildGrid mocks
        buildGrid = mockk()
        every { buildGrid.invoke(initialRoverCoordinates) } returns grid

        // init presenter mocks
        presenter = mockk()
        every { presenter.presentGrid(grid) } returns Unit
        every { presenter.presentPathNotFound() } returns Unit
        every { presenter.presentRockEncounteredMessage() } returns Unit
        every { presenter.presentGoalReachedMessage() } returns Unit
    }

    init {
        "Run when route is not found" {
            // Given
            every { computeRoute(gridParameters, move) } returns RouteNotFound

            // When
            run(
                Grid(
                    10,
                    initialRoverCoordinates,
                    goalCoordinates,
                    rocksCoordinates
                ),
                computeRoute,
                move,
                buildGrid,
                presenter
            )

            // Then
            verifySequence {
                buildGrid.invoke(initialRoverCoordinates)
                presenter.presentGrid(grid)
                presenter.presentPathNotFound()
                move wasNot Called
            }
        }
        "Run with route of 1 command" {
            // Given
            every { computeRoute(gridParameters, move) } returns Route(listOf(Command.LEFT))

            // When
            run(
                Grid(
                    10,
                    initialRoverCoordinates,
                    goalCoordinates,
                    rocksCoordinates
                ),
                computeRoute,
                move,
                buildGrid,
                presenter
            )

            // Then
            verifySequence {
                buildGrid.invoke(initialRoverCoordinates)
                presenter.presentGrid(grid)
                move.invoke(initialRoverCoordinates, command)
                buildGrid.invoke(initialRoverCoordinates)
                presenter.presentGrid(grid)
            }
        }
        "Run with route of 2 commands" {
            // Given
            every { computeRoute(gridParameters, move) } returns Route(
                listOf(
                    Command.LEFT,
                    Command.LEFT
                )
            )

            // When
            run(
                Grid(
                    10,
                    initialRoverCoordinates,
                    goalCoordinates,
                    rocksCoordinates
                ),
                computeRoute,
                move,
                buildGrid,
                presenter
            )

            // Then
            verifySequence {
                buildGrid.invoke(initialRoverCoordinates)
                presenter.presentGrid(grid)

                move.invoke(initialRoverCoordinates, command)
                buildGrid.invoke(initialRoverCoordinates)
                presenter.presentGrid(grid)

                move.invoke(initialRoverCoordinates, command)
                buildGrid.invoke(initialRoverCoordinates)
                presenter.presentGrid(grid)
            }
        }
        "Run with route containing rock should notify and stop" {
            // Given
            val rockCoordinates = RockCoordinates(0, 1)
            val gridParameters = gridParameters.copy(rocksCoordinates = listOf(rockCoordinates))
            val roverCoordinatesAfterMove = initialRoverCoordinates.copy(
                x = rockCoordinates.x,
                y = rockCoordinates.y
            )
            every { computeRoute(gridParameters, move) } returns Route(
                listOf(
                    Command.RIGHT,
                    Command.LEFT
                )
            )
            every {
                move.invoke(initialRoverCoordinates, Command.RIGHT)
            } returns roverCoordinatesAfterMove

            every { buildGrid.invoke(roverCoordinatesAfterMove) } returns grid

            // When
            run(
                gridParameters,
                computeRoute,
                move,
                buildGrid,
                presenter
            )

            // Then
            verifySequence {
                buildGrid.invoke(initialRoverCoordinates)
                presenter.presentGrid(grid)

                move.invoke(initialRoverCoordinates, Command.RIGHT)
                presenter.presentRockEncounteredMessage()
            }
        }
        "Run with route reaching the goal should notify and stop" {
            // Given
            val roverAtGoalCoordinates = initialRoverCoordinates.copy(
                x = goalCoordinates.x,
                y = goalCoordinates.y
            )
            every { computeRoute(gridParameters, move) } returns Route(listOf(Command.RIGHT))
            every {
                move.invoke(initialRoverCoordinates, Command.RIGHT)
            } returns roverAtGoalCoordinates

            every { buildGrid.invoke(roverAtGoalCoordinates) } returns grid

            // When
            run(
                gridParameters,
                computeRoute,
                move,
                buildGrid,
                presenter
            )

            // Then
            verifySequence {
                buildGrid.invoke(initialRoverCoordinates)
                presenter.presentGrid(grid)

                move.invoke(initialRoverCoordinates, Command.RIGHT)
                buildGrid.invoke(roverAtGoalCoordinates)
                presenter.presentGrid(grid)
                presenter.presentGoalReachedMessage()
            }
        }
    }
}
