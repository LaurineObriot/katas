package main.kotlin

class PurchaseTransaction(private val purchase: Purchase) {
    operator fun invoke(): Invoice {
        val discount = calculateDiscount()
        val total = calculateTotal(discount)
        return Invoice(total, discount)
    }

    private fun calculateDiscount(): Float {
        return when(checkDifferentVolumes()){
            1 -> 0.05F
            2 -> 0.1F
            3 -> 0.15F
            4 -> 0.2F
            else -> 0F
        }
    }

    private fun calculateTotal(discount: Float): Float {
        var total = 0L
        purchase.basket.map { total += it.cost }
        return total - (total * discount)
    }

    private fun checkDifferentVolumes(): Int {
        var different = mutableListOf<Book>()
        purchase.basket.sortedBy { book -> book.volume.value }
        purchase.basket.forEachIndexed { index, book ->
            if ( (index+1)<purchase.basket.size && book.volume.value != purchase.basket[index+1].volume.value){
                different.add(book)
            }
        }
        return different.size
    }
}
