package example.hautecuisine

import example.InMemoryRestaurantRepositoryForTest
import example.restaurant.commands.EventPublisher
import example.restaurant.commands.makeareservation.MakeAReservationCommand
import example.restaurant.commands.makeareservation.MakeAReservationCommandHandler
import example.restaurant.domain.Quantity
import example.restaurant.domain.ReservationDate
import example.restaurant.domain.RestaurantName
import example.restaurant.domain.events.ReservationAccepted
import example.restaurant.domain.events.ReservationRejected
import example.restaurant.infrastructure.ReservationAcceptedDtoV1
import example.restaurant.infrastructure.TableAddedDtoV1
import example.tablecombination.toTimestampString
import io.kotlintest.specs.StringSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import java.time.LocalDateTime

class HauteCuisineTest : StringSpec({
    "When no table booked and enough availability, should accept reservation" {
        // Given
        val repository = InMemoryRestaurantRepositoryForTest()
        repository.setStoredDtoEvents(
            RestaurantName("Haute Cuisine"),
            listOf(
                TableAddedDtoV1(
                    tableNumber = 1,
                    capacity = 12,
                    date = LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    isPrivate = true
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
                Quantity(1),
                ReservationDate(LocalDateTime.of(2020, 2, 7, 0, 0))
            )
        )

        // Then
        verifyOrder {
            eventPublisher.publish(
                listOf(
                    ReservationAccepted(
                        Quantity(1),
                        ReservationDate(LocalDateTime.of(2020, 2, 7, 0, 0)),
                        listOf(1)
                    )
                )
            )
        }
    }
    "When no table booked but not enough capability, should reject reservation" {
        // Given
        val repository = InMemoryRestaurantRepositoryForTest()
        val eventPublisher = mockk<EventPublisher>()
        every { eventPublisher.publish(any()) } returns Unit
        repository.setStoredDtoEvents(
            RestaurantName("Haute Cuisine"),
            listOf(
                TableAddedDtoV1(
                    tableNumber = 1,
                    capacity = 12,
                    date = LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    isPrivate = true
                )
            )
        )
        val commandHandler = MakeAReservationCommandHandler(repository, eventPublisher)

        // When
        commandHandler(
            MakeAReservationCommand(
                RestaurantName("Haute Cuisine"),
                Quantity(13),
                ReservationDate(LocalDateTime.of(2020, 2, 7, 0, 0))
            )
        )

        // Then
        verify {
            eventPublisher.publish(
                listOf(
                    ReservationRejected(
                        ReservationDate(LocalDateTime.of(2020, 2, 7, 0, 0))
                    )
                )
            )
        }

    }
    "When some table booked and no table available, should reject reservation" {
        // Given
        val repository = InMemoryRestaurantRepositoryForTest()
        val eventPublisher = mockk<EventPublisher>()
        every { eventPublisher.publish(any()) } returns Unit
        val commandHandler = MakeAReservationCommandHandler(repository, eventPublisher)
        repository.setStoredDtoEvents(
            RestaurantName("Haute Cuisine"),
            listOf(
                ReservationAcceptedDtoV1(
                    quantity = 5,
                    date = LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString()
                ),
                TableAddedDtoV1(
                    tableNumber = 1,
                    capacity = 12,
                    date = LocalDateTime.of(2020, 3, 7, 0, 0).toTimestampString(),
                    isPrivate = true
                )
            )
        )

        // When
        commandHandler(
            MakeAReservationCommand(
                RestaurantName("Haute Cuisine"),
                Quantity(10),
                ReservationDate(LocalDateTime.of(2020, 2, 7, 0, 0))
            )
        )

        // Then
        verifyOrder {
            eventPublisher.publish(
                listOf(
                    ReservationRejected(
                        ReservationDate(LocalDateTime.of(2020, 2, 7, 0, 0))
                    )
                )
            )
        }

    }
    "When some table booked but tables available and capable enough, should accept reservation" {
        // Given
        val repository = InMemoryRestaurantRepositoryForTest()
        val eventPublisher = mockk<EventPublisher>()
        every { eventPublisher.publish(any()) } returns Unit
        repository.setStoredDtoEvents(
            RestaurantName("Haute Cuisine"),
            listOf(
                TableAddedDtoV1(
                    tableNumber = 1,
                    capacity = 7,
                    date = LocalDateTime.of(2020, 3, 7, 0, 0).toTimestampString(),
                    isPrivate = true
                ),
                TableAddedDtoV1(
                    tableNumber = 2,
                    capacity = 5,
                    date = LocalDateTime.of(2020, 3, 7, 0, 0).toTimestampString(),
                    isPrivate = true
                ),
                ReservationAcceptedDtoV1(
                    quantity = 5,
                    date = LocalDateTime.of(2020, 4, 7, 0, 0).toTimestampString()
                )
            )
        )
        val commandHandler = MakeAReservationCommandHandler(repository, eventPublisher)

        // When
        commandHandler(
            MakeAReservationCommand(
                RestaurantName("Haute Cuisine"),
                Quantity(7),
                ReservationDate(LocalDateTime.of(2020, 2, 7, 0, 0))
            )
        )

        // Then
        verifyOrder {
            eventPublisher.publish(
                listOf(
                    ReservationAccepted(
                        Quantity(7),
                        ReservationDate(LocalDateTime.of(2020, 2, 7, 0, 0)),
                        listOf(1)
                    )
                )
            )
        }
    }
    "When some table booked and no tables available with enough capacity, should reject reservation" {
        // Given
        val repository = InMemoryRestaurantRepositoryForTest()
        val eventPublisher = mockk<EventPublisher>()
        every { eventPublisher.publish(any()) } returns Unit
        repository.setStoredDtoEvents(
            RestaurantName("Haute Cuisine"),
            listOf(
                TableAddedDtoV1(
                    tableNumber = 1,
                    capacity = 5,
                    date = LocalDateTime.of(2020, 3, 7, 0, 0).toTimestampString(),
                    isPrivate = true
                ),
                TableAddedDtoV1(
                    tableNumber = 2,
                    capacity = 5,
                    date = LocalDateTime.of(2020, 3, 7, 0, 0).toTimestampString(),
                    isPrivate = true
                ),
                ReservationAcceptedDtoV1(
                    quantity = 5,
                    date = LocalDateTime.of(2020, 4, 7, 0, 0).toTimestampString()
                )
            )
        )
        val commandHandler = MakeAReservationCommandHandler(repository, eventPublisher)

        // When
        commandHandler(
            MakeAReservationCommand(
                RestaurantName("Haute Cuisine"),
                Quantity(7),
                ReservationDate(LocalDateTime.of(2020, 2, 7, 0, 0))
            )
        )

        // Then
        verifyOrder {
            eventPublisher.publish(
                listOf(
                    ReservationRejected(
                        ReservationDate(LocalDateTime.of(2020, 2, 7, 0, 0))
                    )
                )
            )
        }
    }
})