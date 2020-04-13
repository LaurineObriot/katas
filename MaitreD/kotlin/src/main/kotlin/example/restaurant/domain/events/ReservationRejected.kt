package example.restaurant.domain.events

import example.restaurant.domain.ReservationDate

data class ReservationRejected(override val date: ReservationDate) : DatedEvent