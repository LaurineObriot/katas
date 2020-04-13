package example.secondseating

import example.InMemoryRestaurantRepositoryForTest
import example.restaurant.commands.EventPublisher
import example.restaurant.commands.makeareservation.MakeAReservationCommand
import example.restaurant.commands.makeareservation.MakeAReservationCommandHandler
import example.restaurant.domain.Quantity
import example.restaurant.domain.ReservationDate
import example.restaurant.domain.RestaurantName
import example.restaurant.domain.events.ReservationAccepted
import example.restaurant.domain.events.ReservationRejected
import example.restaurant.infrastructure.ReservationAcceptedDtoV2
import example.restaurant.infrastructure.TableAddedDtoV1
import example.tablecombination.toTimestampString
import io.kotlintest.specs.StringSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyOrder
import java.time.LocalDateTime

class SecondSeatingTest : StringSpec({
    "When a table is booked at 18:00 and serving is 2h long, it is available at 20:00" {
        // Given
        val repository = InMemoryRestaurantRepositoryForTest()
        repository.setStoredDtoEvents(
            RestaurantName("Haute Cuisine"),
            listOf(
                TableAddedDtoV1(
                    1,
                    2,
                    LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    true
                ),
                TableAddedDtoV1(
                    2,
                    2,
                    LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    true
                ),
                TableAddedDtoV1(
                    3,
                    4,
                    LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    true
                ),
                ReservationAcceptedDtoV2(
                    4,
                    LocalDateTime.of(2023, 10, 22, 18, 0).toTimestampString(),
                    3
                    )
            )
        )
        val eventPublisher = mockk<EventPublisher>()
        every { eventPublisher.publish(any()) } returns Unit
        val makeAReservationCommandHandler = MakeAReservationCommandHandler(repository, eventPublisher)

        // When
        makeAReservationCommandHandler(
            MakeAReservationCommand(
                RestaurantName("Haute Cuisine"),
                Quantity(3),
                ReservationDate(LocalDateTime.of(2023, 10, 22, 20, 0))
            )
        )

        // Then
        verifyOrder {
            eventPublisher.publish(
                listOf(
                    ReservationAccepted(
                        Quantity(3),
                        ReservationDate(LocalDateTime.of(2023, 10, 22, 20, 0)),
                        listOf(3)
                    )
                )
            )
        }
    }
    "When 2 tables for 4 are booked from 18:15 and 19:00 and serving is 2 hours long, they are not available for booking at 20:00" {
        // Given
        val repository = InMemoryRestaurantRepositoryForTest()
        repository.setStoredDtoEvents(
            RestaurantName("Haute Cuisine"),
            listOf(
                TableAddedDtoV1(
                    1,
                    2,
                    LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    true
                ),
                TableAddedDtoV1(
                    2,
                    4,
                    LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    true
                ),
                TableAddedDtoV1(
                    3,
                    4,
                    LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    true
                ),
                ReservationAcceptedDtoV2(
                    2,
                    LocalDateTime.of(2023, 10, 22, 18, 0).toTimestampString(),
                    1
                ),
                ReservationAcceptedDtoV2(
                    1,
                    LocalDateTime.of(2023, 10, 22, 18, 15).toTimestampString(),
                    2
                ),
                ReservationAcceptedDtoV2(
                    2,
                    LocalDateTime.of(2023, 10, 22, 19, 0).toTimestampString(),
                    3
                )
            )
        )
        val eventPublisher = mockk<EventPublisher>()
        every { eventPublisher.publish(any()) } returns Unit
        val makeAReservationCommandHandler = MakeAReservationCommandHandler(repository, eventPublisher)

        // When
        makeAReservationCommandHandler(
            MakeAReservationCommand(
                RestaurantName("Haute Cuisine"),
                Quantity(3),
                ReservationDate(LocalDateTime.of(2023, 10, 22, 20, 0))
            )
        )

        // Then
        verifyOrder {
            eventPublisher.publish(
                listOf(
                    ReservationRejected(
                        ReservationDate(LocalDateTime.of(2023, 10, 22, 20, 0))
                    )
                )
            )
        }
    }
})