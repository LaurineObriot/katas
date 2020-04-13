package example.restaurant.domain

abstract class Table(
    val tableNumber: Int,
    val capacity: Quantity
) {
    abstract fun canBeBookedFor(reservationDate: ReservationDate, quantity: Quantity): Boolean
    abstract fun bookFor(reservationDate: ReservationDate, quantity: Quantity)
}

class SharedTable(
    tableNumber: Int,
    capacity: Quantity
) : Table(tableNumber, capacity) {
    private val schedule = mutableListOf<Pair<Seating, Quantity>>()

    override fun canBeBookedFor(reservationDate: ReservationDate, quantity: Quantity): Boolean {
        val seating = Seating.from(reservationDate, 2)
        val overlapingSeatings = schedule.filter { seating.doesOverlaps(it.first) }
        val occupiedSeats = Quantity(overlapingSeatings.sumBy { it.second.value })
        return quantity <= (capacity - occupiedSeats)
    }

    override fun bookFor(reservationDate: ReservationDate, quantity: Quantity) {
        schedule.add(Seating.from(reservationDate, 2) to quantity)
    }
}

class PrivateTable(
    tableNumber: Int,
    capacity: Quantity,
    val canBeCombined : Boolean
) : Table(tableNumber, capacity) {
    private val schedule = mutableListOf<Seating>()

    override fun canBeBookedFor(reservationDate: ReservationDate, quantity: Quantity): Boolean {
        return capacity >= quantity && !isBookedOn(reservationDate)
    }

    override fun bookFor(reservationDate: ReservationDate, quantity: Quantity) {
        schedule.add(Seating.from(reservationDate, 2))
    }

    fun isBookedOn(reservationDate: ReservationDate): Boolean {
        val seating = Seating.from(reservationDate, 2)
        return schedule.any { seating.doesOverlaps(it) }
    }
}
