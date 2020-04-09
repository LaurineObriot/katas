package rover.domain

data class Grid(
    val size: Int,
    val roverInitialCoordinates: RoverCoordinates,
    val goalCoordinates: GoalCoordinates,
    val rocksCoordinates: List<RockCoordinates>
)