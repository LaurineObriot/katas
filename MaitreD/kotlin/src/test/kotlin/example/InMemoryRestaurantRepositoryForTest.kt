package example

import example.restaurant.domain.RestaurantName
import example.restaurant.infrastructure.InMemoryRestaurantRepository

class InMemoryRestaurantRepositoryForTest : InMemoryRestaurantRepository() {
    fun setStoredDtoEvents(restaurantName: RestaurantName, events: List<Any>) {
        store[restaurantName] = events.toMutableList()
    }
}