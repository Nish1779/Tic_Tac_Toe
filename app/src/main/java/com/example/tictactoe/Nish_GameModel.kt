package com.example.tictactoe

class Nish_GameModel
{
    var movesPlayed = Array(10) {i -> "" }
    var whoseTurn = Constants.X
    var whoWon = ""
    var numOfMovesPlayed = 0

    // this is the 2D array .... this is how you create 2D array
    val winningcombinations : Array<IntArray> = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 8),
        intArrayOf(1,4,7),
        intArrayOf(2,5,8),
        intArrayOf(3,6,9),
        intArrayOf(1,5,9),
        intArrayOf(3,5,7)
        )

    // initialize winning Combination 2D array

    fun processTouch(num: Int) : String {
        numOfMovesPlayed++
        val playingNow = whoseTurn
        movesPlayed[num] = whoseTurn

        if (whoseTurn == Constants.X)
        {
            whoseTurn = Constants.O
        }
        else
        {
            whoseTurn = Constants.X
        }
        return playingNow
    }

    fun isGameFinished() : Boolean{
        if (numOfMovesPlayed < 5)
        {
            return false
        }
        // otherwise itreate over winningCombinations

    }

}

    class Constants
    {
        companion object
        {
            val X = "X"
            val O = "O"
        }
    }

