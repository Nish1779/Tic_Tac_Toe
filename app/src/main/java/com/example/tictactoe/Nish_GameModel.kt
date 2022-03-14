package com.example.tictactoe

import android.content.SharedPreferences
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Nish_GameModel
{
    var movesPlayed = Array(10) {i -> "" }
    var whoseTurn = Constants.X
    var whoWon = ""
    var numOfMovesPlayed = 0
    var lastPlayed = ""
    var orderOfMovesPlayed = " "


    // this is the 2D array .... this is how you create 2D array
    val winningcombinations : Array<IntArray> = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9),
        intArrayOf(1,4,7),
        intArrayOf(2,5,8),
        intArrayOf(3,6,9),
        intArrayOf(1,5,9),
        intArrayOf(3,5,7)
        )

    // initialize winning Combination 2D array

    fun processTouch(num: Int) : String {
        orderOfMovesPlayed += num.toString() + ","
        numOfMovesPlayed++
        val playingNow = whoseTurn
        movesPlayed[num] = whoseTurn //next moves
        lastPlayed = whoseTurn
        // toggles the value of turn ...whos going to play next
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
        // otherwise itrate over winningCombinations
        //check if any array element in winningcombinmation
        //has last played in all 3 positions

        for (item in winningcombinations){

            val positions = item as IntArray

            if (movesPlayed[positions[0]] == lastPlayed
                && movesPlayed[positions[1]] == lastPlayed
                && movesPlayed[positions[2]] == lastPlayed)
                    //game is over...!!
                        whoWon = lastPlayed
            //saveGame()
            return true
        }


        if (numOfMovesPlayed == 9)
        {
            //game ended in draw
                //saveGame()
            return true
        }
        return false
    }

    fun saveGame(sharedPref: SharedPreferences)
    {
        var numberofgamesplayed =sharedPref.getInt(Constants.NUMBER_OF_GAMES_PLAYED,0)
        numberofgamesplayed++

        with(sharedPref.edit()){
            putInt(Constants.NUMBER_OF_GAMES_PLAYED,numberofgamesplayed)
            putString(Constants.GAME_RESULT + numberofgamesplayed, whoWon)

            // added time and date....
            val dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("M/d/y H:m:ss"))
            putString(Constants.DATE_TIME + numberofgamesplayed, dateTime)

            putString(Constants.OREDERS_OF_MOVES + numberofgamesplayed, orderOfMovesPlayed)

            apply() //commit()

        }
    }

}

    class Constants
    {
        companion object
        {
            val X = "X"
            val O = "O"
            val NUMBER_OF_GAMES_PLAYED = "numberofgamesplayed"
            val GAME_RESULT = "gameResult"
            val DATE_TIME = "dateTime"
            val OREDERS_OF_MOVES = "oredersOfMoves"
        }
    }

