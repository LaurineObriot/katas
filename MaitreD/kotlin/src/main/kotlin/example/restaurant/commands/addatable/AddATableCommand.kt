package example.restaurant.commands.addatable

import example.restaurant.domain.Quantity
import example.restaurant.domain.RestaurantName

data class AddATableCommand(
    val restaurantName: RestaurantName,
    val seatings: Quantity,
    val isPrivate: Boolean,
    val canBeCombined: Boolean
)