package edu.game.tetris

import scalafx.scene.paint.Color
//3
class J_Tetro() extends Tetromino {
    val matrix = List(
        Array(
            Array(3, 0, 0),
            Array(3, 3, 3),
            Array(0, 0, 0)
        ),
        Array(
            Array(0, 3, 3),
            Array(0, 3, 0),
            Array(0, 3, 0)
        ),
        Array(
            Array(0, 0, 0),
            Array(3, 3, 3),
            Array(0, 0, 3)
        ),
        Array(
            Array(0, 3, 0),
            Array(0, 3, 0),
            Array(3, 3, 0)
        )
    )

    val color = Color.BLUE
    val code = 3
}