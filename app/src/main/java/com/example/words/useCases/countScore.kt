package com.example.words.useCases

import androidx.activity.ComponentActivity
import java.io.File
import java.io.FileInputStream

class countScore {



    fun getRandomWord(text: String): String {
        val words = text.split("\\s+".toRegex())

        var randomIndex = (0 until words.size).random()

        return words[randomIndex]
    }




    fun processWord(word1: String, word2: String, wordsList: MutableList<String>): Int {
        val cleanWord1 = word1.replace(" ", "").toLowerCase()
        val cleanWord2 = word2.replace(" ", "").toLowerCase()

        if (cleanWord1 == cleanWord2) {
            println(cleanWord1.length * -1)
            return cleanWord1.length * -1
        }

        if (wordsList.contains(cleanWord2)) {
            println(cleanWord2.length * -1)
            return cleanWord2.length * -1
        }

        val charList1 = cleanWord1.toMutableList()
        val charList2 = cleanWord2.toMutableList()

        var matchedCount = 0
        for (char in charList2) {
            if (charList1.contains(char)) {
                charList1.remove(char)
                matchedCount++
            }
        }


        if (matchedCount == cleanWord2.length) {
            println(matchedCount)
            return matchedCount
        }

        println((charList2.size - matchedCount) * -1)
        return (charList2.size - matchedCount) * -1
    }

}