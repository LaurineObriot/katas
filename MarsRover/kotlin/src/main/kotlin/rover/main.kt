package rover

import rover.domain.Orientation
import kotlin.random.Random

fun main() {
    val gridSize = 10
    val grid = generateRandomGrid(
        size = gridSize,
        numberOfRocks = 30,
        randomInt = {until -> Random.nextInt(until)},
        randomOrientation = { Orientation.values().random()}
    )
    val move = move(gridSize)
    val buildGrid = buildGrid(grid)

    run(
        grid,
        ::computeRoute,
        move,
        buildGrid,
        CommandLinePresenter()
    )
}

class CommandLinePresenter : Presenter {

    companion object {
        private const val DELAY_BETWEEN_TWO_FRAMES_MS = 400L
    }

    override fun presentGrid(grid: List<String>) {
        grid.forEach { println(it) }
        println()
        waitBetweenFrames()
    }

    private fun waitBetweenFrames() {
        Thread.sleep(DELAY_BETWEEN_TWO_FRAMES_MS)
    }

    override fun presentRockEncounteredMessage() {
        println("Mars rover to Houston : Mission aborted STOP. Rocks on the route STOP. Houston Do you copy ? OVER.")
    }

    override fun presentGoalReachedMessage() {
        println("Mars rover to Houston : Mission achieved OVER.")
        println("Houston to Mars rover : Well played Mars Rover STOP. Congratulations OVER AND OUT.")
    }

    override fun presentPathNotFound() {
        println("Mars rover to Houston : Mission aborted STOP. Could not find path to target STOP. Houston Do you copy ? OVER.")
    }
}