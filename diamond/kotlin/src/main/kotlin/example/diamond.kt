package example

fun buildDiamond(untilLetter: Char): List<String> {
    val letters: CharRange = 'A'..untilLetter
    val numberOfLetters = letters.count()
    val maxNumberOfSpacesPerLine = ' '.replicate(numberOfLetters - 1)
    val rightSpacesByLine: List<List<Char>> = inits(maxNumberOfSpacesPerLine)
    val leftSpacesByLine: List<List<Char>> = rightSpacesByLine.reversed()

    val upperLeftQuadrant = leftSpacesByLine
        .zip(letters) { leftSpaces: List<Char>, letter: Char ->
            leftSpaces.plus(letter)
        }.zip(rightSpacesByLine) { leftSpacesAndLetter: List<Char>, rightSpaces: List<Char> ->
            leftSpacesAndLetter.plus(rightSpaces)
        }

    val topHalf = upperLeftQuadrant.map {
        it.mirror()
    }

    return topHalf.mirror().map {
        it.joinToString("")
    }
}

private fun <T> List<T>.mirror() = this.plus(this.reversed().drop(1))

private infix fun <T> T.replicate(length: Int) = List(length) { this }

private inline fun <reified T> inits(list: List<T>) = (0..list.size).map {
    list.take(it)
}