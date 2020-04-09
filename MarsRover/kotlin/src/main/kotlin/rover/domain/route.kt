package rover.domain

sealed class RouteComputation {
    data class Route(val commands: List<Command>) : RouteComputation()
    object RouteNotFound : RouteComputation()
}

enum class Command {
    FORWARD,
    RIGHT,
    LEFT,
    BACKWARD
}