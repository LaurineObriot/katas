package example.boutique

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
import example.restaurant.infrastructure.ReservationAcceptedDtoV2
import example.restaurant.infrastructure.TableAddedDtoV1
import example.tablecombination.toTimestampString
import io.kotlintest.specs.StringSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import java.time.LocalDateTime

class LaBoutiqueTest : StringSpec({
    "When no seat booked and enough availability, should accept reservation" {
        // Given
        val repository = InMemoryRestaurantRepositoryForTest()
        repository.setStoredDtoEvents(
            RestaurantName("La Boutique"),
            listOf(
                TableAddedDtoV1(
                    tableNumber = 1,
                    capacity = 12,
                    date = LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    isPrivate = false
                )
            )
        )
        val eventPublisher = mockk<EventPublisher>()
        every { eventPublisher.publish(any()) } returns Unit
        val makeAReservationCommandHandler = MakeAReservationCommandHandler(repository, eventPublisher)

        // When
        makeAReservationCommandHandler(
            MakeAReservationCommand(
                RestaurantName("La Boutique"),
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
    "When no seats booked but not enough capability, should reject reservation" {
        // Given
        val repository = InMemoryRestaurantRepositoryForTest()
        val eventPublisher = mockk<EventPublisher>()
        every { eventPublisher.publish(any()) } returns Unit
        repository.setStoredDtoEvents(
            RestaurantName("La Boutique"),
            listOf(
                TableAddedDtoV1(
                    tableNumber = 1,
                    capacity = 12,
                    date = LocalDateTime.of(2020, 2, 7, 0, 0).toTimestampString(),
                    isPrivate = false
                )
            )
        )
        val commandHandler = MakeAReservationCommandHandler(repository, eventPublisher)

        // When
        commandHandler(
            MakeAReservationCommand(
                RestaurantName("La Boutique"),
                Quantity(13),
                ReservationDate(LocalDateTime.of(2020, 2, 7,0, 0))
            )
        )

        // Then
        verify {
            eventPublisher.publish(
                listOf(
                    ReservationRejected(
                        ReservationDate(LocalDateTime.of(2020, 2, 7,0, 0))
                    )
                )
            )
        }

    }
    "When some seats booked and no seat available, should reject reservation" {
        // Given
        val repository = InMemoryRestaurantRepositoryForTest()
        val eventPublisher = mockk<EventPublisher>()
        every { eventPublisher.publish(any()) } returns Unit
        val commandHandler = MakeAReservationCommandHandler(repository, eventPublisher)
        repository.setStoredDtoEvents(
            RestaurantName("La Boutique"),
            listOf(
                ReservationAcceptedDtoV1(
                    quantity = 5,
                    date = LocalDateTime.of(2020, 2, 7,0, 0).toTimestampString()
                ),
                TableAddedDtoV1(
                    tableNumber = 1,
                    capacity = 12,
                    date = LocalDateTime.of(2020, 3, 7,0, 0).toTimestampString(),
                    isPrivate = false
                )
            )
        )

        // When
        commandHandler(
            MakeAReservationCommand(
                RestaurantName("La Boutique"),
                Quantity(10),
                ReservationDate(LocalDateTime.of(2020, 2, 7,0, 0))
            )
        )

        // Then
        verifyOrder {
            eventPublisher.publish(
                listOf(
                    ReservationRejected(
                        ReservationDate(LocalDateTime.of(2020, 2, 7,0, 0))
                    )
                )
            )
        }

    }
    "When some seats booked but enough seats are available, should accept reservation on same table" {
        // Given
        val repository = InMemoryRestaurantRepositoryForTest()
        val eventPublisher = mockk<EventPublisher>()
        every { eventPublisher.publish(any()) } returns Unit
        repository.setStoredDtoEvents(
            RestaurantName("La Boutique"),
            listOf(
                TableAddedDtoV1(
                    tableNumber = 2,
                    capacity = 12,
                    date = LocalDateTime.of(2020, 3, 7,0, 0).toTimestampString(),
                    isPrivate = false
                ),
                ReservationAcceptedDtoV2(
                    quantity = 5,
                    date = LocalDateTime.of(2020, 4, 7,0, 0).toTimestampString(),
                    tableNumber = 2
                )
            )
        )
        val commandHandler = MakeAReservationCommandHandler(repository, eventPublisher)

        // When
        commandHandler(
            MakeAReservationCommand(
                RestaurantName("La Boutique"),
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
                        ReservationDate(LocalDateTime.of(2020, 2, 7,0, 0)),
                        listOf(2)
                    )
                )
            )
        }
    }
})