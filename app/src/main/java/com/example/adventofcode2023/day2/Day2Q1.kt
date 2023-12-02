package com.example.adventofcode2023.day2

/**
 * DAY 2 QUESTION 1
 * ANSWER: 2348
 */
object Day2Q1 {
    val maxRed = 12
    val maxGreen = 13
    val maxBlue = 14

    fun run() {
        println("day 2: TOTAL ${parseRoundStrings(GameData.games)}")
    }

    //for each game

    // iterate through string, when you find a digit, add to digit string
        // if the next is also a digit, append
        // get first letter after space, check if r, b, or g
        // compare digit to max value
    // check if contains red, blue, or green
        // if contains color, check if number more than max
            //if yes, return
            //else continue
        // Add game number to grand total if passes


    private fun parseRoundStrings(list: ArrayList<String>): Int {
        var roundNumberTotal = 0

        list.forEachIndexed { index, s ->
//            println("day 2 index $index")

            val afterTitle = s.replaceBefore(":", "")
            if (checkNumbers(afterTitle)) {
                roundNumberTotal += (index + 1)
            }
//            println("day 2 round ${index + 1} total $roundNumberTotal")
        }
        return roundNumberTotal
    }

    private fun checkNumbers(afterTitle: String): Boolean {
        var digitString = ""
        var prevChar: Char
        var currChar = 'a'
        afterTitle.forEach { char ->
            prevChar = currChar
            currChar = char
            if (char.isDigit()) {
                if (digitString.isEmpty()) {
                    digitString = char.toString()
                } else {
                    digitString += char.toString()
                }
            } else if (prevChar == ' ') {
                if (!isPossible(digitString.toInt(), currChar)) {
//                    println("day 2 $digitString is too many $currChar")
                    digitString = ""
                    return false
                }
                digitString = ""
            }
        }
        return true
    }
    private fun isPossible(numCubes: Int, char: Char): Boolean {
        return when (char) {
            'r' -> numCubes <= maxRed
            'b' -> numCubes <= maxBlue
            'g' -> numCubes <= maxGreen
            // if a different block color is there
            else -> false
        }
    }

}
