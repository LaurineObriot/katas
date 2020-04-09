package rover

import rover.domain.*
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class CoordinatesTests : StringSpec({
    "isAt - Two coordinates having same Xs and Ys should return true" {
        val firstCoordinates = RoverCoordinates(8, 8, Orientation.values().random())
        val secondCoordinates = RockCoordinates(8, 8)
        (firstCoordinates isAt secondCoordinates) shouldBe true
        (secondCoordinates isAt firstCoordinates) shouldBe true
    }
    "isAt - Two coordinates having different Xs should return false" {
        val firstCoordinates = RoverCoordinates(8, 7, Orientation.values().random())
        val secondCoordinates = RockCoordinates(8, 8)
        (firstCoordinates isAt secondCoordinates) shouldBe false
        (secondCoordinates isAt firstCoordinates) shouldBe false
    }
    "isAt - Two coordinates having different Ys should return false" {
        val firstCoordinates = RoverCoordinates(7, 8, Orientation.values().random())
        val secondCoordinates = RockCoordinates(8, 8)
        (firstCoordinates isAt secondCoordinates) shouldBe false
        (secondCoordinates isAt firstCoordinates) shouldBe false
    }
    "isAtAny - No coordinates have both same Xs and Ys should return false" {
        val someCoordinates = RockCoordinates(2,2)
        val otherCoordinates = listOf(
            GoalCoordinates(1,1),
            GoalCoordinates(1,2),
            GoalCoordinates(1,3)
        )
        (someCoordinates isAtAny otherCoordinates) shouldBe false
    }
    "isAtAny - Some coordinates have both same Xs and Ys should return false" {
        val someCoordinates = RockCoordinates(2,2)
        val otherCoordinates = listOf(
            GoalCoordinates(1,1),
            GoalCoordinates(2,2),
            GoalCoordinates(2,2)
        )
        (someCoordinates isAtAny otherCoordinates) shouldBe true
    }
})