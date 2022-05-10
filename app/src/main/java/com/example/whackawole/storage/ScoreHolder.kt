package com.example.whackawole.storage

class ScoreHolder {

    var highScore = 0
    var score = 0

    fun updateScore(sc: Int) : Int {
        score = sc
        return score
    }

    fun updateHighScore(score: Int) : Int {
        if (score > highScore)
            highScore = score
        return highScore
    }
}