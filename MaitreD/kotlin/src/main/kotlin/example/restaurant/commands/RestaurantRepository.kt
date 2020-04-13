package example.restaurant.commands

import example.restaurant.domain.Restaurant
import example.restaurant.domain.RestaurantName
import example.restaurant.domain.events.DatedEvent

interface RestaurantRepository {
    fun fetch(restaurantName: RestaurantName) : Restaurant
    fun store(restaurantName: RestaurantName, domainEvents: List<DatedEvent>)
}