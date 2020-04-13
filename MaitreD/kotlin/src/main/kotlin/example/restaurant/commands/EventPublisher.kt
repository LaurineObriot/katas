package example.restaurant.commands

import example.restaurant.domain.events.DatedEvent

interface EventPublisher {
    fun publish(events: List<DatedEvent>)
}