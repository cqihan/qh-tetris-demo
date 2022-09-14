package edu.game.tetris

import scalafx.scene.paint.Color

abstract class Tetromino {
    val matrix: List[Array[Array[Int]]]
    val color: Color
    val code: Int
}