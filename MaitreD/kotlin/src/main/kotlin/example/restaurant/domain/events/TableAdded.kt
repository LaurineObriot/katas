package example.restaurant.domain.events

import example.restaurant.domain.Quantity
import example.restaurant.domain.ReservationDate

data class TableAdded (
    override val date: ReservationDate,
    val tableNumber : Int,
    val capacity: Quantity,
    val isPrivate: Boolean,
    val canBeCombined: Boolean
) : DatedEvent
