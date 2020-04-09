package rover

import rover.domain.Command.*
import rover.domain.Orientation.*
import rover.domain.RoverCoordinates
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class MoveRoverTests : StringSpec({
    "Rotate right when facing north should face east" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 0, NORTH), RIGHT)
        nextCoordinates.orientation shouldBe EAST
    }
    "Rotate right when facing east should face south" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 0, EAST), RIGHT)
        nextCoordinates.orientation shouldBe SOUTH
    }
    "Rotate right when facing south should face west" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 0, SOUTH), RIGHT)
        nextCoordinates.orientation shouldBe WEST
    }
    "Rotate right when facing west should face north" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 0, WEST), RIGHT)
        nextCoordinates.orientation shouldBe NORTH
    }
    "Rotate left when facing north should face west" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 0, NORTH), LEFT)
        nextCoordinates.orientation shouldBe WEST
    }
    "Rotate left when facing west should face south" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 0, WEST), LEFT)
        nextCoordinates.orientation shouldBe SOUTH
    }
    "Rotate left when facing south should face east" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 0, SOUTH), LEFT)
        nextCoordinates.orientation shouldBe EAST
    }
    "Rotate left when facing east should face north" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 0, EAST), LEFT)
        nextCoordinates.orientation shouldBe NORTH
    }
    "Move forward when facing north and not on the edge should decrease y" {
        val nextCoordinates = move(10)(RoverCoordinates(1, 0, NORTH), FORWARD)
        nextCoordinates.y shouldBe 0
    }
    "Move forward when facing east and not on the edge should increase x" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 0, EAST), FORWARD)
        nextCoordinates.x shouldBe 1
    }
    "Move forward when facing south and not on the edge should increase y" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 0, SOUTH), FORWARD)
        nextCoordinates.y shouldBe 1
    }
    "Move forward when facing west and not on the edge should decrease x" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 1, WEST), FORWARD)
        nextCoordinates.x shouldBe 0
    }
    "Move forward when facing north and on the edge should max y" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 0, NORTH), FORWARD)
        nextCoordinates.y shouldBe 9
    }
    "Move forward when facing east and on the edge should zero x" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 9, EAST), FORWARD)
        nextCoordinates.x shouldBe 0
    }
    "Move forward when facing south and on the edge should zero y" {
        val nextCoordinates = move(10)(RoverCoordinates(9, 0, SOUTH), FORWARD)
        nextCoordinates.y shouldBe 0
    }
    "Move forward when facing west and on the edge should max x" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 0, WEST), FORWARD)
        nextCoordinates.x shouldBe 9
    }
    "Move backward when facing north and not on the edge should increase y" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 0, NORTH), BACKWARD)
        nextCoordinates.y shouldBe 1
    }
    "Move backward when facing east and not on the edge should decrease x" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 1, EAST), BACKWARD)
        nextCoordinates.x shouldBe 0
    }
    "Move backward when facing south and not on the edge should decrease y" {
        val nextCoordinates = move(10)(RoverCoordinates(1, 0, SOUTH), BACKWARD)
        nextCoordinates.y shouldBe 0
    }
    "Move backward when facing west and not on the edge should increase x" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 0, WEST), BACKWARD)
        nextCoordinates.x shouldBe 1
    }
    "Move backward when facing north and on the edge should zero y" {
        val nextCoordinates = move(10)(RoverCoordinates(9, 0, NORTH), BACKWARD)
        nextCoordinates.y shouldBe 0
    }
    "Move backward when facing east and on the edge should max x" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 0, EAST), BACKWARD)
        nextCoordinates.x shouldBe 9
    }
    "Move backward when facing south and on the edge should max y" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 0, SOUTH), BACKWARD)
        nextCoordinates.y shouldBe 9
    }
    "Move backward when facing west and on the edge should zero x" {
        val nextCoordinates = move(10)(RoverCoordinates(0, 9, WEST), BACKWARD)
        nextCoordinates.x shouldBe 0
    }
})