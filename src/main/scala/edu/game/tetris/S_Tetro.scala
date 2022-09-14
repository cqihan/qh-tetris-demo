package edu.game.tetris

import scalafx.scene.paint.Color
//5
class S_Tetro() extends Tetromino {
    val matrix = List(
        Array(
            Array(0, 5, 5),
            Array(5, 5, 0),
            Array(0, 0, 0)
        ),
        Array(
            Array(0, 5, 0),
            Array(0, 5, 5),
            Array(0, 0, 5)
        ),
        Array(
            Array(0, 0, 0),
            Array(0, 5, 5),
            Array(5, 5, 0)
        ),
        Array(
            Array(5, 0, 0),
            Array(5, 5, 0),
            Array(0, 5, 0)
        )
    )

    val color = Color.GREEN
    val code = 5
}