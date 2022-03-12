package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class GameBoard : AppCompatActivity() {


    var gamemodel = Nish_GameModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_board)

        setTitle("New Game ") // change karyu title page nu .....
    }



    fun squareTouched(square: View)
    {

        if ((square as Button).text.length > 0){
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

    }


}