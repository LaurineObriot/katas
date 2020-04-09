package rover.domain

abstract class YXCoordinates(
    open val y: Int,
    open val x: Int
) {
    infix fun isAt(other: YXCoordinates) =
        this.isAt(other.y, other.x)

    fun isAt(y: Int, x: Int) =
        y == this.y && x == this.x

    infix fun isAtAny(others: List<YXCoordinates>) =
        others.any { it isAt this }
}

data class RockCoordinates(override val y: Int, override val x: Int) : YXCoordinates(y, x)
data class GoalCoordinates(override val y: Int, override val x: Int) : YXCoordinates(y, x)

data class RoverCoordinates(
    override val y: Int,
    override val x: Int,
    val orientation: Orientation
) : YXCoordinates(y, x)

enum class Orientation {
    NORTH,
    SOUTH,
    EAST,
    WEST

}
