package example.restaurant.domain

import java.time.LocalDateTime

data class ReservationDate(val date: LocalDateTime)

data class Seating(val start: LocalDateTime, val end: LocalDateTime) {
    companion object {
        fun from(reservationDate: ReservationDate, durationInHours : Int) : Seating {
            return Seating(
                reservationDate.date,
                reservationDate.date.plusHours(durationInHours.toLong())
            )
        }
    }
    fun doesOverlaps(other: Seating) : Boolean {
        return (
            end.isAfter(other.start)
            && start.isBefore(other.end)
        ) || (
            start.isBefore(other.end)
            && end.isAfter(other.start)
        )
    }
}



