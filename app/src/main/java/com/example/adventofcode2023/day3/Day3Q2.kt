package com.example.adventofcode2023.day3

object Day3Q2 {
    var firstGearNumber: Int? = null

    var gearRow: Int = 0
    var gearIndex: Int = 0

    fun run() {
        println("d3q2 ${}")
    }
    private fun parseForGears(list: ArrayList<String>) : Int {
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
            val prevRowIndex = if (rowNumber > 0 ) rowNumber - 1 else null
            val nextRow = if (rowNumber < list.size - 1) list[rowNumber + 1] else null
            val nextRowIndex =  if (rowNumber < list.size - 1) rowNumber + 1 else null

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
                        if ((prevRow?.get(c)) == '*') {
                            if (firstGearNumber == null) {
                                firstGearNumber = numberString.toInt()
                            }
                            prevRowIndex?.let {
                                gearRow = it
                            }
                        } else if (row[c] == '*') {
                            if (firstGearNumber == null) {
                                firstGearNumber = numberString.toInt()
                            }
                        } else if (nextRow?.get(c) == '*') {

                        }
                        if ((prevRow?.get(c)) == '*' ||
                            row[c] == '*' ||
                            nextRow?.get(c) == '*'
                        ) {
                            if (firstGearNumber == null) {
                                firstGearNumber = numberString.toInt()
                            }
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
