package example.restaurant.commands.makeareservation

import example.restaurant.commands.EventPublisher
import example.restaurant.commands.RestaurantRepository

class MakeAReservationCommandHandler (
    private val repository: RestaurantRepository,
    private val eventPublisher: EventPublisher
){
    operator fun invoke (command: MakeAReservationCommand) {
        val restaurant = repository.fetch(command.restaurantName)

        restaurant.makeAReservation(command.date, command.quantity)

        val domainEvents = restaurant.getDomainEvents()
        repository.store(command.restaurantName, domainEvents)
        eventPublisher.publish(
            domainEvents
        )
    }
}