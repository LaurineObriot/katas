package test.kotlin

import main.kotlin.Book
import main.kotlin.Purchase
import main.kotlin.PurchaseTransaction
import main.kotlin.Volume
import org.junit.Assert
import org.junit.Test

class PurchaseBookTest {

    @Test
    fun `check volume was created`() {
        val volume = Volume(1)
        Assert.assertEquals(1, volume.value)
    }

    @Test
    fun `check book was created`() {
        val volume = Volume(1)
        val book = Book(8, volume)
        Assert.assertEquals(BOOK_COST, book.cost)
        Assert.assertEquals(1, book.volume.value)
    }

    @Test
    fun `when one book is buyed doesn't have discount`() {
        val volume = Volume(1)
        val book = Book(BOOK_COST, volume)

        val purchase = Purchase(listOf(book))
        val action = PurchaseTransaction(purchase)

        val invoice = action()

        Assert.assertEquals(NO_PERCENT_DISCOUNT_TOTAL, invoice.total)
    }

    @Test
    fun `when 2 books are buyed have 5 percent discount`() {
        val volume = Volume(1)
        val volume2 = Volume(2)
        val book = Book(BOOK_COST, volume)
        val book2 = Book(BOOK_COST, volume2)
        val purchase = Purchase(listOf(book, book2))
        val action = PurchaseTransaction(purchase)

        val invoice = action()

        Assert.assertEquals(FIVE_PERCENT_DISCOUNT_TOTAL, invoice.total)
    }

    @Test
    fun `when 3 books are buyed have 10 percent discount`() {
        val volume = Volume(1)
        val volume2 = Volume(2)
        val volume3 = Volume(3)
        val book = Book(BOOK_COST, volume)
        val book2 = Book(BOOK_COST, volume2)
        val book3 = Book(BOOK_COST, volume3)
        val purchase = Purchase(listOf(book, book2, book3))
        val action = PurchaseTransaction(purchase)

        val invoice = action()

        Assert.assertEquals(TEN_PERCENT_DISCOUNT_TOTAL, invoice.total)
    }


    @Test
    fun `when 4 books are buyed have 15 percent discount`() {
        val volume = Volume(1)
        val volume2 = Volume(2)
        val volume3 = Volume(3)
        val volume4 = Volume(4)
        val book = Book(BOOK_COST, volume)
        val book2 = Book(BOOK_COST, volume2)
        val book3 = Book(BOOK_COST, volume3)
        val book4 = Book(BOOK_COST, volume4)
        val purchase = Purchase(listOf(book, book2, book3, book4))
        val action = PurchaseTransaction(purchase)

        val invoice = action()

        Assert.assertEquals(FIVETEEN_PERCENT_DISCOUNT_TOTAL, invoice.total)
    }

    companion object {
        val BOOK_COST = 8L
        val NO_PERCENT_DISCOUNT_TOTAL = 8.0F
        val FIVE_PERCENT_DISCOUNT_TOTAL = 15.2F
        val TEN_PERCENT_DISCOUNT_TOTAL = 21.6F
        val FIVETEEN_PERCENT_DISCOUNT_TOTAL = 27.2F
    }
}
