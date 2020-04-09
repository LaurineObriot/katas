package example

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec

class DiamondTests : StringSpec({
    "A diamond is never empty" {
        forAll(LetterGenerator()) { a: Char ->
            buildDiamond(a).isNotEmpty()
        }
    }
    "A diamond has a decreasing letter sequence in its upper left quadrant's diagonal" {
        forAll(LetterGenerator()) { letter: Char ->
            val diamond = buildDiamond(letter)
            val lettersInUpperLeftDiagonal = buildUpperLeftDiagonalCoordinates(letter)
                .map { diamond[it.first][it.second] }
            lettersInUpperLeftDiagonal == (letter downTo 'A').toList()
        }
    }
    "A diamond has spaces in its upper left's quadrant non-diagonal"{
        forAll(LetterGenerator()) { letter: Char ->
            val diamond = buildDiamond(letter)
            val lettersInUpperLeftDiagonal = buildUpperLeftNonDiagonalCoordinates(letter)
                .map { diamond[it.first][it.second] }
            lettersInUpperLeftDiagonal.all { it == ' ' }
        }
    }
    "A diamond is symmetrical vertically" {
        forAll(LetterGenerator()) { letter: Char ->
            val diamond = buildDiamond(letter)
            diamond.all { it == it.reversed() }
        }
    }
    "A diamond is symmetrical horizontally" {
        forAll(LetterGenerator()) { letter: Char ->
            val diamond = buildDiamond(letter)
            diamond == diamond.reversed()
        }
    }
    "A diamond has width == height == 2 x letterIndex - 1 "{
        forAll(LetterGenerator()) { letter: Char ->
            val diamond = buildDiamond(letter)
            val letterIndex = ('A'..letter).count()
            diamond.size == 2 * letterIndex - 1
                && diamond.size == diamond[0].length

        }
    }
})

class LetterGenerator : Gen<Char> {
    private val letters = 'A'..'Z'
    override fun constants() = emptyList<Char>()
    override fun random() = generateSequence {
        letters.random()
    }
}

fun buildUpperLeftNonDiagonalCoordinates(letter: Char): List<Pair<Int, Int>> {
    val lastLetterIndex = ('A'..letter).count() - 1
    val coordinates = 0..lastLetterIndex
    return coordinates.zip(coordinates).minus(
        buildUpperLeftDiagonalCoordinates(letter)
    )
}

fun buildUpperLeftDiagonalCoordinates(letter: Char): List<Pair<Int, Int>> {
    val lastLetterIndex = ('A'..letter).count() - 1
    val yCoordinates = lastLetterIndex downTo 0
    val xCoordinates = 0..lastLetterIndex
    return yCoordinates.zip(xCoordinates)
}