package edu.game.tetris

import scalafxml.core.macros.sfxml
import scalafx.scene.layout.GridPane
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.shape.StrokeType
import scalafx.scene.paint.Paint
import scalafx.Includes._
import scalafx.animation.Timeline
import scalafx.animation.KeyFrame
import scalafx.scene.input.{KeyEvent, KeyCode}
import scalafx.scene.control.Label
import scalafx.beans.property.StringProperty

@sfxml
class GUIController(private val boardPane: GridPane, private val tetroPane: GridPane, private var lineLabel: Label, private val previewPane: GridPane) {

    def renderBoard() = {
        boardPane.children.clear()
        for((i, m) <- TetrisBoard.boardGrid.zipWithIndex; (j,n) <- i.zipWithIndex) {
            val rec = new Rectangle {
                width = TetrisBoard.blockSize
                height = TetrisBoard.blockSize
                fill = if (j == 0) Color.TRANSPARENT else paintTetromino(j)
                stroke = Color.rgb(84,84,84)
                strokeWidth = 0.5
                strokeType = StrokeType.Inside
            }
            boardPane.add(rec, n, m)
        }
    }

    def paintTetromino(code: Int) = {
        var color: Color = TetrominoState.tetromino.color
        for(i <- TetrisBoard.tetrominoes if i.code == code) {
            color = i.color
        }
        color
    }

    def renderTetro() = {
        tetroPane.children.clear()
        for((i,m) <- TetrominoState.tetromino.matrix(TetrominoState.rotate).zipWithIndex; (j,n) <- i.zipWithIndex) {
            val rec = new Rectangle {
                width = TetrisBoard.blockSize
                height = TetrisBoard.blockSize
                fill = if (j == 0) Color.TRANSPARENT else TetrominoState.tetromino.color
            }
            tetroPane.add(rec, n, m)
        }
    }

    def renderNextTetro() = {
        previewPane.children.clear
        for((i,m) <- (TetrisBoard.tetroList.last).matrix(0).zipWithIndex; (j,n) <- i.zipWithIndex) {
            val rec = new Rectangle {
                width = 40
                height = 40
                fill = if (j == 0) Color.TRANSPARENT else (TetrisBoard.tetroList.last).color
            }
            previewPane.add(rec, n, m)
        }
    }

    def startGravity() = {
        TetrisBoard.setTetromino
        renderTetro
        renderNextTetro
        locateTetromino
        
        val timeLine = new Timeline {
            cycleCount = Timeline.Indefinite
            keyFrames = Seq(
                KeyFrame(400 ms, onFinished = () => {
                    dropDown

                    if(TetrisBoard.checkEnd) {
                        this.stop
                    }

                    lineLabel.text <== new StringProperty((Assessment.line).toString)
                })
            )
        }
        timeLine.play
    }

    def dropDown() = {
        if(TetrisBoard.onGravity) {
            renderTetro
            renderNextTetro 
        } else {
            renderBoard
            TetrisBoard.generateTetromino
        }
        locateTetromino
    }

    def moveRight() = {
        if(TetrisBoard.onRight) {
            renderTetro
        }
        locateTetromino
    }

    def moveLeft() = {
        if(TetrisBoard.onLeft) {
            renderTetro
        }
        locateTetromino
    }

    def moveDown() = {
        if(TetrisBoard.onDown) {
            renderTetro
        }
        locateTetromino
    }

    def moveRotate() = {
        if(TetrisBoard.onRotate) {
            renderTetro
        }
    }

    def locateTetromino() = {
        boardPane.layoutX() = 0
        boardPane.layoutY() = 0
        tetroPane.layoutX() = boardPane.layoutX.toDouble + TetrominoState.xOffset * tetroPane.vgap.toDouble + TetrominoState.xOffset * TetrisBoard.blockSize
        tetroPane.layoutY() = boardPane.layoutY.toDouble + TetrominoState.yOffset * tetroPane.hgap.toDouble + TetrominoState.yOffset * TetrisBoard.blockSize
    }

    boardPane.onKeyPressed = k => k.code match {     
        case KeyCode.Right => moveRight
        case KeyCode.Left => moveLeft
        case KeyCode.Down => moveDown
        case KeyCode.Up => moveRotate
        case _ => println("Nono")
    }
}