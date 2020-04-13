package example.restaurant.infrastructure

data class ReservationAcceptedDtoV1 (
    val quantity : Int,
    val date: String
)

data class ReservationAcceptedDtoV2 (
    val quantity : Int,
    val date: String,
    val tableNumber: Int
)

data class ReservationAcceptedDtoV3 (
    val quantity : Int,
    val date: String,
    val tableNumbers: List<Int>
)
