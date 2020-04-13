package example.restaurant.infrastructure

data class TableAddedDtoV1(
    val tableNumber: Int,
    val capacity: Int,
    val date: String,
    val isPrivate: Boolean
)

data class TableAddedDtoV2(
    val tableNumber: Int,
    val capacity: Int,
    val date: String,
    val isPrivate: Boolean,
    val canBeCombined:Boolean
)