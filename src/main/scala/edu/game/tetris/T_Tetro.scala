package edu.game.tetris

import scalafx.scene.paint.Color
//6
class T_Tetro() extends Tetromino {
    val matrix = List(
        Array(
            Array(0, 6, 0),
            Array(6, 6, 6),
            Array(0, 0, 0)
        ),
        Array(
            Array(0, 6, 0),
            Array(0, 6, 6),
            Array(0, 6, 0)
        ),
        Array(
            Array(0, 0, 0),
            Array(6, 6, 6),
            Array(0, 6, 0)
        ),
        Array(
            Array(0, 6, 0),
            Array(6, 6, 0),
            Array(0, 6, 0)
        )
    )

    val color = Color.PURPLE
    val code = 6
}