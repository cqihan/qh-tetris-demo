package edu.game.tetris

import scalafx.scene.paint.Color
//7
class Z_Tetro() extends Tetromino {
    val matrix = List(
        Array(
            Array(7, 7, 0),
            Array(0, 7, 7),
            Array(0, 0, 0)
        ),
        Array(
            Array(0, 0, 7),
            Array(0, 7, 7),
            Array(0, 7, 0)
        ),
        Array(
            Array(0, 0, 0),
            Array(7, 7, 0),
            Array(0, 7, 7)
        ),
        Array(
            Array(0, 7, 0),
            Array(7, 7, 0),
            Array(7, 0, 0)
        )
    )

    val color = Color.RED
    val code = 7
}