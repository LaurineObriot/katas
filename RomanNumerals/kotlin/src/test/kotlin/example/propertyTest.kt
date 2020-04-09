package example

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import kotlin.random.Random

class PropertyTest : StringSpec({
    "Conversion from Int to RomanNumeral is inverse of the one from RomanNumeral to Int" {
        forAll(IntBelow3000Generator()) { int ->
            int.toRomanNumeral().toDecimal() == int
        }
    }
})

class IntBelow3000Generator : Gen<Int> {
    override fun constants() = emptyList<Int>()
    override fun random() = generateSequence {
        Random.nextInt(1, 3000)
    }
}