import com.nhaarman.expect.expect
import org.example.app.RomanNumerals
import org.junit.jupiter.api.Assertions.*
import java.lang.reflect.Array.set

internal class RomanNumeralsTest {
    private val romanNumeralValidator = RomanNumerals()

    @org.junit.jupiter.api.Test
    fun `Blank Input`() {
        expect(romanNumeralValidator.isRomanNumber("")).toBe(false) { "blank string should fail" }
        expect(romanNumeralValidator.isRomanNumber(" ")).toBe(false) { "blank string should fail" }
    }

    @org.junit.jupiter.api.Test
    fun `Non Numeral`() {
        var alphabet = ('A'..'Z').toSet()
        var numerals = romanNumeralValidator.validNumerals.keys;
        for(c in alphabet.minus(numerals)) {
            expect(romanNumeralValidator.isRomanNumber(c.toString())).toBe(false) { "should fail with single non numerical input" }
            expect(romanNumeralValidator.isRomanNumber(c.toString().plus('I'))).toBe(false) { "should fail with non numerical input" }
        }
    }

    @org.junit.jupiter.api.Test
    fun `Decreasing Order`() {
        for (invalidNumeral in setOf("LC", "VL", "VC", "VX", "XXL", "XXC", "IIV", "IIX", "IIL", "IIC")) {
            expect(romanNumeralValidator.isRomanNumber(invalidNumeral)).toBe(false) { "$invalidNumeral should fail with larger numeral following smaller numeral" }
        }
    }

    @org.junit.jupiter.api.Test
    fun `Non Numeral Characters`() {
        var numbers = (0..9).toSet().union(setOf("-", "?", "|"))
        for (invalidNumeral in numbers) {
            expect(romanNumeralValidator.isRomanNumber(invalidNumeral.toString())).toBe(false) { "$invalidNumeral should fail with larger numeral following smaller numeral" }
        }
    }

    @org.junit.jupiter.api.Test
    fun `Too Many Reoccurances`() {
        expect(romanNumeralValidator.isRomanNumber("IIII")).toBe(false) { "should fail with input contains 'I' more than three in a row" }
        expect(romanNumeralValidator.isRomanNumber("CLIIII")).toBe(false) { "should fail with input contains 'I' more than three in a row" }

        expect(romanNumeralValidator.isRomanNumber("XXXX")).toBe(false) { "should fail with input contains 'X' more than three in a row" }
        expect(romanNumeralValidator.isRomanNumber("CLXXXX")).toBe(false) { "should fail with input contains 'X' more than three in a row" }
        expect(romanNumeralValidator.isRomanNumber("CLXXXXI")).toBe(false) { "should fail with input contains 'X' more than three in a row" }

        expect(romanNumeralValidator.isRomanNumber("CCCC")).toBe(false) { "should fail with input contains 'C' more than three in a row" }

        expect(romanNumeralValidator.isRomanNumber("LL")).toBe(false) { "should fail with input contains sequence of 'L'" }
        expect(romanNumeralValidator.isRomanNumber("CLL")).toBe(false) { "should fail with input contains sequence of 'L'" }
        expect(romanNumeralValidator.isRomanNumber("CLLV")).toBe(false) { "should fail with input contains sequence of 'L'" }
        expect(romanNumeralValidator.isRomanNumber("CILLV")).toBe(false) { "should fail with input contains sequence of 'L'" }

        expect(romanNumeralValidator.isRomanNumber("VV")).toBe(false) { "should fail with input contains sequence of 'V'" }
        expect(romanNumeralValidator.isRomanNumber("CVV")).toBe(false) { "should fail with input contains sequence of 'V'" }
        expect(romanNumeralValidator.isRomanNumber("CVVI")).toBe(false) { "should fail with input contains sequence of 'V'" }
        expect(romanNumeralValidator.isRomanNumber("CIVVI")).toBe(false) { "should fail with input contains sequence of 'V'" }
    }

    @org.junit.jupiter.api.Test
    fun `Subtraction`() {
        expect(romanNumeralValidator.isRomanNumber("XC")).toBe(true) { "should succeed with one 'X' before larger numeral" }
        expect(romanNumeralValidator.isRomanNumber("XCL")).toBe(true) { "should succeed with one 'X' before larger numeral" }
        expect(romanNumeralValidator.isRomanNumber("CXL")).toBe(true) { "should succeed with one 'X' before larger numeral" }

        expect(romanNumeralValidator.isRomanNumber("IL")).toBe(true) { "should succeed with one 'I' before larger numeral" }
        expect(romanNumeralValidator.isRomanNumber("LIL")).toBe(true) { "should succeed with one 'I' before larger numeral" }
        expect(romanNumeralValidator.isRomanNumber("CIL")).toBe(true) { "should succeed with one 'I' before larger numeral" }
    }

    @org.junit.jupiter.api.Test
    fun `Valid Numerals`() {
        // test basic numerals repeated
        var validNumerals = setOf("C", "CC", "CCC", "L", "X", "XX", "XXX", "V", "I", "II", "III");
        for (valid in validNumerals) {
            expect(romanNumeralValidator.isRomanNumber(valid.toString())).toBe(true) { "$valid should pass" }
        }

        validNumerals = setOf("IV", "VI", "VII", "VIII", "LVIII", "IX", "XI", "CLXVI", "CLI", "CLV", "CCCLILXXXVIVIII");
        for (valid in validNumerals) {
            expect(romanNumeralValidator.isRomanNumber(valid.toString())).toBe(true) { "$valid should pass" }
        }

    }
}