package edu.game.tetris

import scalafx.scene.paint.Color
//4
class L_Tetro() extends Tetromino {
    val matrix = List(
        Array(
            Array(0, 0, 4),
            Array(4, 4, 4),
            Array(0, 0, 0)
        ),
        Array(
            Array(0, 4, 0),
            Array(0, 4, 0),
            Array(0, 4, 4)
        ),
        Array(
            Array(0, 0, 0),
            Array(4, 4, 4),
            Array(4, 0, 0)
        ),
        Array(
            Array(4, 4, 0),
            Array(0, 4, 0),
            Array(0, 4, 0)
        )
    )

    val color = Color.ORANGE
    val code = 4
}