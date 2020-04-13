package example.restaurant.domain

data class Quantity (val value : Int) {
    operator fun compareTo(other: Quantity) : Int {
        return this.value.compareTo(other.value)
    }

    operator fun minus(other: Quantity) : Quantity {
        return Quantity(this.value - other.value)
    }

    operator fun plus(other: Quantity) : Quantity {
        return Quantity(this.value + other.value)
    }
}
