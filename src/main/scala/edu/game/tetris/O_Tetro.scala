package edu.game.tetris

import scalafx.scene.paint.Color

class O_Tetro() extends Tetromino {
    val matrix = List(
        Array(
            Array(0, 1, 1, 0),
            Array(0, 1, 1, 0),
            Array(0, 0, 0, 0),
            Array(0, 0, 0, 0)
        )
    )

    val color = Color.YELLOW
    val code = 1
}