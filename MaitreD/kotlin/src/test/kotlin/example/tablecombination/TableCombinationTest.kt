package example.tablecombination

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
import example.restaurant.infrastructure.TableAddedDtoV2
import io.kotlintest.specs.StringSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyOrder
import java.time.LocalDateTime

class TableCombinationTest : StringSpec({
    "When 2 table of 2 are available, they are combine to accept a reservation for 4" {
        // Given
        val repository = InMemoryRestaurantRepositoryForTest()
        repository.setStoredDtoEvents(
            RestaurantName("Haute Cuisine"),
            listOf(
                TableAddedDtoV2(
                    tableNumber = 1,
                    capacity = 2,
                    date = LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    isPrivate = true,
                    canBeCombined = true
                ),
                TableAddedDtoV2(
                    tableNumber = 2,
                    capacity = 2,
                    date = LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    isPrivate = true,
                    canBeCombined = true
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
                Quantity(4),
                ReservationDate(LocalDateTime.of(2023, 10, 22, 20, 0))
            )
        )

        // Then
        verifyOrder {
            eventPublisher.publish(
                listOf(
                    ReservationAccepted(
                        Quantity(4),
                        ReservationDate(LocalDateTime.of(2023, 10, 22, 20, 0)),
                        listOf(1, 2)
                    )
                )
            )
        }
    }
    "When one of 2 tables has an overlapping seating (= not available), should reject reservation" {
        // Given
        val repository = InMemoryRestaurantRepositoryForTest()
        repository.setStoredDtoEvents(
            RestaurantName("Haute Cuisine"),
            listOf(
                TableAddedDtoV2(
                    tableNumber = 1,
                    capacity = 2,
                    date = LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    isPrivate = true,
                    canBeCombined = true
                ),
                TableAddedDtoV2(
                    tableNumber = 2,
                    capacity = 2,
                    date = LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    isPrivate = true,
                    canBeCombined = true
                ),
                ReservationAcceptedDtoV2(
                    quantity = 1,
                    date = LocalDateTime.of(2023, 10, 22, 19, 0).toTimestampString(),
                    tableNumber = 1
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
                Quantity(4),
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
    "Should combine smallest available tables" {
        // Given
        val repository = InMemoryRestaurantRepositoryForTest()
        repository.setStoredDtoEvents(
            RestaurantName("Haute Cuisine"),
            listOf(
                TableAddedDtoV2(
                    tableNumber = 1,
                    capacity = 2,
                    date = LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    isPrivate = true,
                    canBeCombined = true
                ),
                TableAddedDtoV2(
                    tableNumber = 2,
                    capacity = 3,
                    date = LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    isPrivate = true,
                    canBeCombined = true
                ),
                TableAddedDtoV2(
                    tableNumber = 3,
                    capacity = 2,
                    date = LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    isPrivate = true,
                    canBeCombined = true
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
                Quantity(4),
                ReservationDate(LocalDateTime.of(2023, 10, 22, 20, 0))
            )
        )

        // Then
        verifyOrder {
            eventPublisher.publish(
                listOf(
                    ReservationAccepted(
                        Quantity(4),
                        ReservationDate(LocalDateTime.of(2023, 10, 22, 20, 0)),
                        listOf(1, 3)
                    )
                )
            )
        }
    }
    "Should combine N table" {
        // Given
        val repository = InMemoryRestaurantRepositoryForTest()
        repository.setStoredDtoEvents(
            RestaurantName("Haute Cuisine"),
            listOf(
                TableAddedDtoV2(
                    tableNumber = 1,
                    capacity = 1,
                    date = LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    isPrivate = true,
                    canBeCombined = true
                ),
                TableAddedDtoV2(
                    tableNumber = 2,
                    capacity = 2,
                    date = LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    isPrivate = true,
                    canBeCombined = true
                ),
                TableAddedDtoV2(
                    tableNumber = 3,
                    capacity = 3,
                    date = LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    isPrivate = true,
                    canBeCombined = true
                ),
                TableAddedDtoV2(
                    tableNumber = 4,
                    capacity = 4,
                    date = LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    isPrivate = true,
                    canBeCombined = true
                ),
                TableAddedDtoV2(
                    tableNumber = 5,
                    capacity = 5,
                    date = LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    isPrivate = true,
                    canBeCombined = true
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
                Quantity(15),
                ReservationDate(LocalDateTime.of(2023, 10, 22, 20, 0))
            )
        )

        // Then
        verifyOrder {
            eventPublisher.publish(
                listOf(
                    ReservationAccepted(
                        Quantity(15),
                        ReservationDate(LocalDateTime.of(2023, 10, 22, 20, 0)),
                        listOf(1, 2, 3, 4, 5)
                    )
                )
            )
        }
    }
})

