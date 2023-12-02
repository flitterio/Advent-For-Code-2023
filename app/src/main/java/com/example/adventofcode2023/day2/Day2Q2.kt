package com.example.adventofcode2023.day2

object Day2Q2 {
    fun run() {
        println("d2p2 ${parseRounds(GameData.games)}")
    }

    // for each game find max red, blue, and green
    // multiply all 3
    // add to total

    private fun parseRounds(list: ArrayList<String>) : Int {
        var powerSum = 0
        list.forEach { s ->
            val afterTitle = s.replaceBefore(":", "")
            powerSum += getPowerOfMaxValues(afterTitle)
        }
        return powerSum
    }

    private fun getPowerOfMaxValues(afterTitle: String) : Int {
        var maxRed = 1
        var maxBlue = 1
        var maxGreen = 1
        var prevChar: Char
        var currChar = 'a'
        var digitString = ""

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
                when (currChar) {
                    'r' -> maxRed = getMaxValue(maxRed, digitString.toInt())
                    'b' -> maxBlue = getMaxValue(maxBlue, digitString.toInt())
                    'g' -> maxGreen = getMaxValue(maxGreen, digitString.toInt())
                }
                digitString = ""
            }
        }
        println("d2p2 red $maxRed , blue $maxBlue, green $maxGreen ")
        return maxRed * maxBlue * maxGreen
    }

    private fun getMaxValue(currMax: Int, numToCheck: Int): Int {
        return if (numToCheck > currMax) {
            numToCheck
        } else {
            currMax
        }
    }
}
