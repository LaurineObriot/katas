package example.restaurant.commands.addatable

import example.restaurant.commands.EventPublisher
import example.restaurant.commands.RestaurantRepository

class AddATableCommandHandler (
    private val repository: RestaurantRepository,
    private val eventPublisher: EventPublisher,
    private val time: Time
) {
    operator fun invoke(command: AddATableCommand) {
        val restaurant = repository.fetch(command.restaurantName)

        restaurant.addATable(command.seatings, time.now(), command.isPrivate, command.canBeCombined)

        val domainEvents = restaurant.getDomainEvents()
        repository.store(command.restaurantName, domainEvents)
        eventPublisher.publish(
            domainEvents
        )
    }
}