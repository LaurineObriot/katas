package example.restaurant.domain.events

import example.restaurant.domain.Quantity
import example.restaurant.domain.ReservationDate

data class ReservationAccepted(
    val quantity: Quantity,
    override val date: ReservationDate,
    val tableNumbers: List<Int>
) : DatedEvent