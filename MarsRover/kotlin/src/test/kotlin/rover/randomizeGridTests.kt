package rover

import rover.domain.*
import rover.domain.Orientation.*
import io.kotlintest.TestCase
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.mockk.every
import io.mockk.mockk

class RandomizeGridTests : StringSpec() {
    private lateinit var randomInt: (Int) -> Int
    private lateinit var randomOrientation: () -> Orientation
    private val gridSize = 10

    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)
        randomInt = mockk()
        randomOrientation = mockk()
        every { randomOrientation.invoke() } returns NORTH
    }

    init {
        "Random grid with no rock" {
            every {
                randomInt.invoke(gridSize)
            } answers {
                1
            } andThen {
                2
            } andThen {
                3
            } andThen { 4 }

            generateRandomGrid(
                gridSize,
                0,
                randomInt,
                randomOrientation
            ) shouldBe Grid(
                gridSize,
                RoverCoordinates(1, 2, NORTH),
                GoalCoordinates(3, 4),
                emptyList()
            )
        }
        "Random grid with no rock and goal at rover position" {
            every {
                randomInt.invoke(gridSize)
            } answers {
                1
            } andThen {
                2
            } andThen {
                1
            } andThen {
                2
            } andThen {
                3
            } andThen {
                4
            }

            generateRandomGrid(
                gridSize,
                0,
                randomInt,
                randomOrientation
            ) shouldBe Grid(
                gridSize,
                RoverCoordinates(1, 2, NORTH),
                GoalCoordinates(3, 4),
                emptyList()
            )
        }
        "Random grid with one rock" {
            every {
                randomInt.invoke(gridSize)
            } answers {
                1
            } andThen {
                2
            } andThen {
                3
            } andThen {
                4
            } andThen {
                5
            } andThen {
                6
            }

            generateRandomGrid(
                gridSize,
                1,
                randomInt,
                randomOrientation
            ) shouldBe Grid(
                gridSize,
                RoverCoordinates(1, 2, NORTH),
                GoalCoordinates(3, 4),
                listOf(
                    RockCoordinates(5, 6)
                )
            )
        }
        "Random grid with one rock at rover position" {
            every {
                randomInt.invoke(gridSize)
            } answers {
                1
            } andThen {
                2
            } andThen {
                3
            } andThen {
                4
            } andThen {
                1
            } andThen {
                2
            } andThen {
                5
            } andThen {
                6
            }

            generateRandomGrid(
                gridSize,
                1,
                randomInt,
                randomOrientation
            ) shouldBe Grid(
                gridSize,
                RoverCoordinates(1, 2, NORTH),
                GoalCoordinates(3, 4),
                listOf(
                    RockCoordinates(5, 6)
                )
            )
        }
        "Random grid with one rock at goal position" {
            every {
                randomInt.invoke(gridSize)
            } answers {
                1
            } andThen {
                2
            } andThen {
                3
            } andThen {
                4
            } andThen {
                3
            } andThen {
                4
            } andThen {
                5
            } andThen {
                6
            }

            generateRandomGrid(
                gridSize,
                1,
                randomInt,
                randomOrientation
            ) shouldBe Grid(
                gridSize,
                RoverCoordinates(1, 2, NORTH),
                GoalCoordinates(3, 4),
                listOf(
                    RockCoordinates(5, 6)
                )
            )
        }
        "Random grid with two rocks at goal position" {
            every {
                randomInt.invoke(gridSize)
            } answers {
                1
            } andThen {
                2
            } andThen {
                3
            } andThen {
                4
            } andThen {
                5
            } andThen {
                6
            } andThen {
                7
            } andThen {
                8
            }

            generateRandomGrid(
                gridSize,
                2,
                randomInt,
                randomOrientation
            ) shouldBe Grid(
                gridSize,
                RoverCoordinates(1, 2, NORTH),
                GoalCoordinates(3, 4),
                listOf(
                    RockCoordinates(5, 6),
                    RockCoordinates(7, 8)
                )
            )
        }
    }
}