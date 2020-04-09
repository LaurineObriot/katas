package example

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class FromIntToRomanNumeralsTest : StringSpec({
    "1 should be I" {
        1.toRomanNumeral() shouldBe("I")
    }
    "2 should be II" {
        2.toRomanNumeral() shouldBe("II")
    }
    "3 should be III" {
        3.toRomanNumeral() shouldBe("III")
    }
    "4 should be IV" {
        4.toRomanNumeral() shouldBe("IV")
    }
    "5 should be V" {
        5.toRomanNumeral() shouldBe("V")
    }
    "6 should be VI" {
        6.toRomanNumeral() shouldBe("VI")
    }
    "7 should be VII" {
        7.toRomanNumeral() shouldBe("VII")
    }
    "8 should be VIII" {
        8.toRomanNumeral() shouldBe("VIII")
    }
    "9 should be IX" {
        9.toRomanNumeral() shouldBe("IX")
    }
    "10 should be X" {
        10.toRomanNumeral() shouldBe("X")
    }
    "11 should be XI" {
        11.toRomanNumeral() shouldBe("XI")
    }
    "12 should be XII" {
        12.toRomanNumeral() shouldBe("XII")
    }
    "40 should be XL" {
        40.toRomanNumeral() shouldBe("XL")
    }
    "50 should be L" {
        50.toRomanNumeral() shouldBe("L")
    }
    "57 should be LVII" {
        57.toRomanNumeral() shouldBe("LVII")
    }
    "90 should be XC" {
        90.toRomanNumeral() shouldBe("XC")
    }
    "98 should be XCVIII" {
        98.toRomanNumeral() shouldBe("XCVIII")
    }
    "100 should be C" {
        100.toRomanNumeral() shouldBe("C")
    }
    "194 should be CXCIV" {
        194.toRomanNumeral() shouldBe("CXCIV")
    }
    "457 should be CDLVII" {
        457.toRomanNumeral() shouldBe("CDLVII")
    }
    "500 should be D"{
        500.toRomanNumeral() shouldBe("D")
    }
    "564 should be DLXIV" {
        564.toRomanNumeral() shouldBe("DLXIV")
    }
    "1000 should be M" {
        1000.toRomanNumeral() shouldBe("M")
    }
    "1998 should be MCMXCVIII" {
        1998.toRomanNumeral() shouldBe("MCMXCVIII")
    }
    "2583 should be MMDLXXXIII" {
        2583.toRomanNumeral() shouldBe("MMDLXXXIII")
    }
})