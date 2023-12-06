package com.example.adventofcode2023.day3

object Day3Q1 {
    fun run() {
        println("d3q1 ${checkForNumber(ToolData.actualMap)}")
    }

    // go through each array, if symbol is detected, get i-1, i, and i+1 around it for row above and below
        // if number is detected, check that row from the index, add to the number string,
            // we will have to replace the number so it doesn't get counted twice with a letter
        // Once we hit a ., get the number and add it to the total

    // Might make more sense to find number, then check around that!

    private fun isSymbol(char: Char?) : Boolean {
        return char?.let { !it.isLetterOrDigit() && it != '.' } ?: false
    }

    private fun checkForNumber(list: ArrayList<String>): Int {
        var total = 0
        var numberString = ""
        var startingNumIndex = 1
        var endingNumIndex = 1

        list.forEachIndexed { rowNumber, row ->
            // check for number
                // if number add to string
                // save index of first char that is number, loop to get the other numbers
                // use index range, then get that + and - 1 and check for symbols
                // if symbol found, add to total
                // clear number string
            val prevRow = if (rowNumber > 0 ) list[rowNumber - 1] else null
            val nextRow = if (rowNumber < list.size - 1) list[rowNumber + 1] else null

            row.forEachIndexed { charIndex, char ->
                if (char.isDigit()) {
                    if (numberString == "") {
                        numberString = char.toString()
                        startingNumIndex = charIndex
                    } else {
                        numberString += char.toString()
                    }
                    endingNumIndex = charIndex
                } else if (numberString.isNotEmpty() ) {
                    for (c in (if (startingNumIndex > 0 ) startingNumIndex - 1 else 0)..
                            if (endingNumIndex < row.length - 1) endingNumIndex + 1 else row.length -1
                    ) {
                        if (isSymbol(prevRow?.get(c)) ||
                            isSymbol(row[c]) ||
                            isSymbol(nextRow?.get(c))
                        ) {
                            println("d3q1 number being added $numberString")
                            total += numberString.toInt()
//                            println("d3q1 total $total")
                            continue
                        }
                    }
                    numberString = ""
                }
            }
        }
        println("d3q1 GRAND total $total")
        return total
    }
}
