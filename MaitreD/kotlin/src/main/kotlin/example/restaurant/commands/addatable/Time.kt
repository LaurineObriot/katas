package example.restaurant.commands.addatable

import java.time.LocalDateTime

interface Time {
    fun now() : LocalDateTime
}
