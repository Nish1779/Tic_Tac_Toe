package com.example.tictactoe

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_game_board.*

class GameBoard : AppCompatActivity() {

    var gamemodel = Nish_GameModel()
    var gameOver = false

    var isPastGame = false
    var pastGameMoves = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_board)


        if (intent.getBooleanExtra(Constants.IS_PAST_GAME, false))
        {
            pastGameMoves = intent.getStringExtra(Constants.OREDERS_OF_MOVES)!!

            setTitle("Past Game")

            var moves = pastGameMoves.split(",")
            var count:Long = 0
            for (i in moves) {
                if (i.length > 0)
                {
                    count++
                    val buttonNumber = "button" + i
                    // findviewbyID<View> (R.id....
                    var button = findViewById<View>(
                        resources.getIdentifier(
                            buttonNumber,
                            "id",
                            this.packageName
                        )
                    )
                    // call with a delay ...
                    Handler().postDelayed({
                        squareTouched(button)
                    },500 * count)
                }
            }
        }
        else {
            setTitle("New Game ") // change karyu title page nu .....
        }
    }


    fun squareTouched(square: View)
    {

        if ((square as Button).text.length > 0 || gameOver){
            // check kare 6 k X or O 6 k nai em iff hoy to go back )
            return
        }
        val id = square.id
        var squareIndex = 0
        when (id){
            R.id.button1 -> {squareIndex = 1}
            R.id.button2 -> {squareIndex = 2}
            R.id.button3 -> {squareIndex = 3}
            R.id.button4 -> {squareIndex = 4}
            R.id.button5 -> {squareIndex = 5}
            R.id.button6 -> {squareIndex = 6}
            R.id.button7 -> {squareIndex = 7}
            R.id.button8 -> {squareIndex = 8}
            R.id.button9 -> {squareIndex = 9}
        }

        val movePlayed = gamemodel.processTouch(squareIndex)
        (square as Button).text = movePlayed

        val gameOver = gamemodel.isGameFinished()
        if (gameOver)
        {
            gamemodel.saveGame(this.getSharedPreferences("gameData", Context.MODE_PRIVATE))
            if (gamemodel.whoWon.length > 0 )
            {
            // display in the text view
            textView.text = "Game Over - "+gamemodel.lastPlayed+" Won!!"
            }
            else
            {
                textView.text = "Draw !"
            }
        }
        else
        {
            textView.text = gamemodel.whoseTurn+ "'s Turn"
        }

    }


}