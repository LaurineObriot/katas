package example

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class FromRomanNumeralsToIntTest : StringSpec({
    "I should be 1" {
        "I".toDecimal() shouldBe(1)
    }
    "II should be 2" {
        "II".toDecimal() shouldBe(2)
    }
    "III should be 3" {
        "III".toDecimal() shouldBe(3)
    }
    "IV should be 4" {
        "IV".toDecimal() shouldBe(4)
    }
    "V should be 5" {
        "V".toDecimal() shouldBe(5)
    }
    "VI should be 6" {
        "VI".toDecimal() shouldBe(6)
    }
    "VII should be 7" {
        "VII".toDecimal() shouldBe(7)
    }
    "VIII should be 8" {
        "VIII".toDecimal() shouldBe(8)
    }
    "IX should be 9" {
        "IX".toDecimal() shouldBe(9)
    }
    "X should be 10" {
        "X".toDecimal() shouldBe(10)
    }
    "XI should be 11" {
        "XI".toDecimal() shouldBe(11)
    }
    "XIX should be 19" {
        "XIX".toDecimal() shouldBe(19)
    }
    "XL should be 40" {
        "XL".toDecimal() shouldBe(40)
    }
    "L should be 50" {
        "L".toDecimal() shouldBe(50)
    }
    "XC should be 90" {
        "XC".toDecimal() shouldBe(90)
    }
    "XCVIII should be 98" {
        "XCVIII".toDecimal() shouldBe(98)
    }
    "C should be 100" {
        "C".toDecimal() shouldBe(100)
    }
    "CD should be 400" {
        "C".toDecimal() shouldBe(100)
    }
    "CDLVII should be 457" {
        "CDLVII".toDecimal() shouldBe(457)
    }
    "D should be 500" {
        "D".toDecimal() shouldBe(500)
    }
    "CM should be 900" {
        "CM".toDecimal() shouldBe(900)
    }
    "M should be 1000" {
        "M".toDecimal() shouldBe(1000)
    }
    "MMDLXXXIII should be 2583" {
        "MMDLXXXIII".toDecimal() shouldBe(2583)
    }
})