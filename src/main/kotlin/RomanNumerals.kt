package org.example.app

class RomanNumerals{
        val validNumerals: HashMap<Char, Int> = hashMapOf('I' to 1, 'V' to 5, 'X' to 10, 'L' to 50, 'C' to 100)
        get() = field

    private fun isNumeral(c: Char): Boolean{
        return c in validNumerals
    }

    fun isRomanNumber(s: String): Boolean{
        if(s.isEmpty())
            return false

        var last = 0
        var lastCounter = 0

        var i = 0
        while (i < s.length) {

            var current = validNumerals.getOrDefault(s[i], 0)
            var currentChar = s[i]

            if(!isNumeral(s[i])) return false
            if((current > last) && (last != 0)) return false

            if(current == last) {
                lastCounter++;
                when (currentChar) {
                    'V', 'L' -> if (lastCounter >=2) return false
                    else -> if (lastCounter >=4) return false
                }
            }
            else {
                if((currentChar in charArrayOf('I', 'X')) and (i != s.lastIndex)) {
                    var next = validNumerals.getOrDefault(s[i+1], 0)
                    if(next <= current) {
                        last = current
                        lastCounter = 1
                    }
                    else {
                        last = next - current
                        lastCounter = 1
                        i++
                    }
                }
                else {
                    last = current
                    lastCounter = 1
                }
            }
            i++
        }

        return true
    }
}


fun main() {

    println("Hello World!")
}