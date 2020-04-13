package example.restaurant.infrastructure

import example.restaurant.commands.RestaurantRepository
import example.restaurant.domain.Quantity
import example.restaurant.domain.ReservationDate
import example.restaurant.domain.Restaurant
import example.restaurant.domain.RestaurantName
import example.restaurant.domain.events.DatedEvent
import example.restaurant.domain.events.ReservationAccepted
import example.restaurant.domain.events.ReservationRejected
import example.restaurant.domain.events.TableAdded
import java.sql.Timestamp
import java.time.LocalDateTime

open class InMemoryRestaurantRepository : RestaurantRepository {
    protected val store = mutableMapOf<RestaurantName, MutableList<Any>>()

    override fun fetch(restaurantName: RestaurantName): Restaurant {
        return store.getOrDefault(restaurantName, mutableListOf()).let { events ->
            Restaurant.fromEvents(
                events.toDomainEvents()
            )
        }
    }

    override fun store(restaurantName: RestaurantName, domainEvents: List<DatedEvent>) {
        if (store[restaurantName] == null) {
            store[restaurantName] = domainEvents.toDTOs().toMutableList()
        } else {
            store[restaurantName]?.addAll(
                domainEvents.toDTOs()
            )
        }
    }

    private fun List<DatedEvent>.toDTOs(): List<Any> {
        return this.map {
            when (it) {
                is ReservationAccepted -> ReservationAcceptedDtoV3(
                    it.quantity.value,
                    it.date.date.toTimestampString(),
                    it.tableNumbers
                )
                is ReservationRejected -> ReservationRejectedDtoV1(
                    it.date.date.toTimestampString()
                )
                is TableAdded -> TableAddedDtoV2(
                    it.tableNumber,
                    it.capacity.value,
                    it.date.date.toTimestampString(),
                    it.isPrivate,
                    it.canBeCombined
                )
                else -> {
                    throw IllegalArgumentException("Unknow type of event")
                }
            }
        }
    }

    private fun List<Any>.toDomainEvents(): List<DatedEvent> {
        return this.map {
            when (it) {
                is ReservationAcceptedDtoV1 -> ReservationAccepted(
                    Quantity(it.quantity),
                    ReservationDate(
                        it.date.toLocalDateTime()
                    ),
                    listOf(1)
                )
                is ReservationAcceptedDtoV2 -> ReservationAccepted(
                    Quantity(it.quantity),
                    ReservationDate(
                        it.date.toLocalDateTime()
                    ),
                    listOf(it.tableNumber)
                )
                is ReservationAcceptedDtoV3 -> ReservationAccepted(
                    Quantity(it.quantity),
                    ReservationDate(
                        it.date.toLocalDateTime()
                    ),
                    it.tableNumbers
                )
                is ReservationRejectedDtoV1 -> ReservationRejected(
                    ReservationDate(
                        it.date.toLocalDateTime()
                    )
                )
                is TableAddedDtoV1 -> TableAdded(
                    ReservationDate(
                        it.date.toLocalDateTime()
                    ),
                    it.tableNumber,
                    Quantity(it.capacity),
                    it.isPrivate,
                    false
                )
                is TableAddedDtoV2 -> TableAdded(
                    ReservationDate(
                        it.date.toLocalDateTime()
                    ),
                    it.tableNumber,
                    Quantity(it.capacity),
                    it.isPrivate,
                    it.canBeCombined
                )
                else -> {
                    throw IllegalArgumentException("Unknown type of event")
                }
            }
        }
    }

    private fun LocalDateTime.toTimestampString(): String {
        return Timestamp.valueOf(this).toString()
    }

    private fun String.toLocalDateTime(): LocalDateTime {
        return Timestamp.valueOf(this).toLocalDateTime()
    }
}