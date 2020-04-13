package example.restaurant.domain

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import java.time.LocalDateTime

class OverlapsTest : StringSpec({
    "14:00-16:00 overlaps 15:00-17:00" {
        // Given
        val `14h00 - 16h00` = Seating(
            LocalDateTime.of(2020,2,19,14,0),
            LocalDateTime.of(2020,2,19,16,0)
        )
        val `15h00 - 17h00` = Seating(
            LocalDateTime.of(2020,2,19,15,0),
            LocalDateTime.of(2020,2,19,17,0)
        )

        // Then
        `14h00 - 16h00`.doesOverlaps(`15h00 - 17h00`) shouldBe true
    }
    "14:00-15:00 does not overlap 16:00-17:00" {
        // Given
        val `14h00 - 15h00` = Seating(
            LocalDateTime.of(2020,2,19,14,0),
            LocalDateTime.of(2020,2,19,15,0)
        )
        val `16h00 - 17h00` = Seating(
            LocalDateTime.of(2020,2,19,16,0),
            LocalDateTime.of(2020,2,19,17,0)
        )

        // Then
        `14h00 - 15h00`.doesOverlaps(`16h00 - 17h00`) shouldBe false
    }
    "14:00-16:00 does not overlap 16:00-17:00" {
        // Given
        val `14h00 - 16h00` = Seating(
            LocalDateTime.of(2020,2,19,14,0),
            LocalDateTime.of(2020,2,19,16,0)
        )
        val `16h00 - 17h00` = Seating(
            LocalDateTime.of(2020,2,19,16,0),
            LocalDateTime.of(2020,2,19,17,0)
        )

        // Then
        `14h00 - 16h00`.doesOverlaps(`16h00 - 17h00`) shouldBe false
    }
    "15:00-18:00 overlaps 14:00-17:00" {
        // Given
        val `15h00 - 18h00` = Seating(
            LocalDateTime.of(2020,2,19,15,0),
            LocalDateTime.of(2020,2,19,18,0)
        )
        val `14h00 - 17h00` = Seating(
            LocalDateTime.of(2020,2,19,14,0),
            LocalDateTime.of(2020,2,19,17,0)
        )

        // Then
        `15h00 - 18h00`.doesOverlaps(`14h00 - 17h00`) shouldBe true
    }
    "15:00-18:00 does not overlap 14:00-15:00" {
        // Given
        val `15h00 - 18h00` = Seating(
            LocalDateTime.of(2020,2,19,15,0),
            LocalDateTime.of(2020,2,19,18,0)
        )
        val `14h00 - 15h00` = Seating(
            LocalDateTime.of(2020,2,19,14,0),
            LocalDateTime.of(2020,2,19,15,0)
        )

        // Then
        `15h00 - 18h00`.doesOverlaps(`14h00 - 15h00`) shouldBe false
    }
    "15:00-18:00 overlaps itself" {
        // Given
        val `15h00 - 18h00` = Seating(
            LocalDateTime.of(2020,2,19,15,0),
            LocalDateTime.of(2020,2,19,18,0)
        )
        // Then
        `15h00 - 18h00`.doesOverlaps(`15h00 - 18h00`) shouldBe true
    }
})