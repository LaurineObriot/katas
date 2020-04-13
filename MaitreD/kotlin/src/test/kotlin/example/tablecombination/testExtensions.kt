package example.tablecombination

import java.sql.Timestamp
import java.time.LocalDateTime

fun LocalDateTime.toTimestampString(): String {
    return Timestamp.valueOf(this).toString()
}