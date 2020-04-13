package example.restaurant.domain

import example.restaurant.domain.events.DatedEvent
import example.restaurant.domain.events.ReservationAccepted
import example.restaurant.domain.events.ReservationRejected
import example.restaurant.domain.events.TableAdded
import java.time.LocalDateTime

class Restaurant {

    private val domainEvents = mutableListOf<DatedEvent>()
    private val tables = mutableListOf<Table>()

    companion object {
        fun fromEvents(events: List<DatedEvent>): Restaurant {
            val restaurant = Restaurant()
            restaurant.tables.addAll(events.filterIsInstance<TableAdded>().map {
                if (it.isPrivate) {
                    PrivateTable(
                        it.tableNumber,
                        it.capacity,
                        it.canBeCombined
                    )
                } else {
                    SharedTable(
                        it.tableNumber,
                        it.capacity
                    )
                }
            })

            events.groupBy { it.date }.forEach {
                it.value
                    .filterIsInstance<ReservationAccepted>()
                    .forEach { reservation ->
                        restaurant.tables.filter{
                            table ->
                            reservation.tableNumbers.contains(table.tableNumber)
                        }.bookFor(reservation.quantity, reservation.date)
                    }
            }
            return restaurant
        }
    }

    fun getDomainEvents(): List<DatedEvent> = domainEvents

    fun makeAReservation(date: ReservationDate, quantity: Quantity) {
        val candidateTable = tables.smallestAvailable(date, quantity)
        if (candidateTable != null) {
            candidateTable.bookFor(date, quantity)
            domainEvents.add(
                ReservationAccepted(
                    quantity, date, listOf(candidateTable.tableNumber)
                )
            )
        } else {
            val candidateTablesCombination = tables.smallestAvailableCombination(date, quantity)
            if (candidateTablesCombination != null) {
                candidateTablesCombination.bookFor(quantity, date)
                domainEvents.add(
                    ReservationAccepted(
                        quantity, date, candidateTablesCombination.map{it.tableNumber}
                    )
                )
            } else {
                domainEvents.add(
                    ReservationRejected(
                        date
                    )
                )
            }
        }
    }

    private fun List<Table>.smallestAvailableCombination(date: ReservationDate, quantity: Quantity): List<Table>? {
        var combinedQuantity = Quantity(0)
        val combination = this.sortedBy { it.capacity.value }
            .filterIsInstance<PrivateTable>()
            .filter { it.canBeCombined && it.isBookedOn(date).not() }
            .takeWhile {
                combinedQuantity += it.capacity
                combinedQuantity <= quantity
            }
        return if (combinedQuantity >= quantity) {
            combination
        } else {
            null
        }
    }

    private fun List<Table>.smallestAvailable(date: ReservationDate, quantity: Quantity) =
        this.sortedBy { it.capacity.value }.firstOrNull { it.canBeBookedFor(date, quantity) }

    fun addATable(seatings: Quantity, now: LocalDateTime, isPrivate: Boolean, canBeCombined: Boolean) {
        val tableNumber = tables.count() + 1
        tables.add(
            if (isPrivate) {
                PrivateTable(
                    tableNumber,
                    seatings,
                    canBeCombined
                )
            } else {
                SharedTable(
                    tableNumber,
                    seatings
                )
            }
        )
        domainEvents.add(
            TableAdded(
                ReservationDate(now),
                tableNumber,
                seatings,
                isPrivate,
                canBeCombined
            )
        )
    }
}

fun List<Table>.bookFor(quantity: Quantity, date: ReservationDate) {
    var remainingSeatsToBook = quantity
    this.forEach {
        if (it.capacity > remainingSeatsToBook) {
            it.bookFor(date, remainingSeatsToBook)
        } else {
            it.bookFor(date, it.capacity)
        }
        remainingSeatsToBook -= it.capacity
    }
}
