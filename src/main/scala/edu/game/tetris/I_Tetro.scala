package edu.game.tetris

import scalafx.scene.paint.Color
//2
class I_Tetro() extends Tetromino {
    val matrix = List(
        Array(
            Array(0, 0, 0, 0),
            Array(2, 2, 2, 2),
            Array(0, 0, 0, 0),
            Array(0, 0, 0, 0)
        ),
        Array(
            Array(0, 0, 2, 0),
            Array(0, 0, 2, 0),
            Array(0, 0, 2, 0),
            Array(0, 0, 2, 0)
        ),
        Array(
            Array(0, 0, 0, 0),
            Array(0, 0, 0, 0),
            Array(2, 2, 2, 2),
            Array(0, 0, 0, 0)
        ),
        Array(
            Array(0, 2, 0, 0),
            Array(0, 2, 0, 0),
            Array(0, 2, 0, 0),
            Array(0, 2, 0, 0)
        )
    )

    val color = Color.CYAN
    val code = 2
}