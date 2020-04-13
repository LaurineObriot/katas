package example.restaurant.commands.makeareservation

import example.restaurant.domain.Quantity
import example.restaurant.domain.ReservationDate
import example.restaurant.domain.RestaurantName

data class MakeAReservationCommand (
    val restaurantName: RestaurantName,
    val quantity : Quantity,
    val date: ReservationDate
)