package com.example.whackawole.storage

object ScoreHolder {

    var highScore = 0
    var score = 0

    fun updateHighScore(score: Int) : Int {
        if (score > highScore)
            highScore = score
        return score
    }
}