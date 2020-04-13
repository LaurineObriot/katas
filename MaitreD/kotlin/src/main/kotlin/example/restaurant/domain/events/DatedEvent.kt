package example.restaurant.domain.events

import example.restaurant.domain.ReservationDate

interface DatedEvent {
    val date : ReservationDate
}
