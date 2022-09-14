package edu.game.tetris

import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.shape.StrokeType
import scalafx.beans.property.DoubleProperty
import scala.collection.mutable.Queue

import java.util.concurrent.ThreadLocalRandom

object TetrisBoard {
    val numBlock_col: Int = 10
    val numBlock_row: Int = 20
    val boardGrid = Array.ofDim[Int](numBlock_row, numBlock_col)
    val blockSize: Int = 40
    var currentShape: Int = 0
    var iniOffsetX: Int = 4
    var iniOffsetY: Int = -3
    var tetroList = new Queue[Tetromino]

    val tetrominoes: List[Tetromino] = List(new I_Tetro, new J_Tetro, new L_Tetro, new O_Tetro, new S_Tetro, new T_Tetro, new Z_Tetro)

    def generateTetromino() = {
        setTetromino

        TetrominoState.tetromino = tetroList.dequeue
        TetrominoState.xOffset = this.iniOffsetX
        TetrominoState.yOffset = this.iniOffsetY
        TetrominoState.rotate = this.currentShape
    }

    def setTetromino() = {
        val newTetromino: Tetromino = tetrominoes(ThreadLocalRandom.current().nextInt(tetrominoes.size))
        tetroList.enqueue(newTetromino)
    }

    def isEnd(): Boolean = {
        var bottom = 0
        for((i,m) <- TetrominoState.tetromino.matrix(TetrominoState.rotate).zipWithIndex; (j,n) <- i.zipWithIndex) {
            if(j != 0) {
                if(m > bottom) {
                    bottom = m
                }
            }
        }
        TetrominoState.yOffset + bottom >= numBlock_row - 1
    }

    def isEndRight(): Boolean = {
        var side = 0
        for((i,m) <- TetrominoState.tetromino.matrix(TetrominoState.rotate).zipWithIndex; (j,n) <- i.zipWithIndex) {
            if(j != 0) {
                if(n > side) {
                    side = n
                }
            }
        }
        TetrominoState.xOffset + side >= numBlock_col - 1
    }

    def isEndLeft(): Boolean = {
        var side = numBlock_col
        for((i,m) <- TetrominoState.tetromino.matrix(TetrominoState.rotate).zipWithIndex; (j,n) <- i.zipWithIndex) {
            if(j != 0) {
                if(n < side) {
                    side = n
                }
            }
        }
        TetrominoState.xOffset + side <= 0
    }

    def isCollide(): Boolean = {
        var collide = false
        for((i,m) <- TetrominoState.tetromino.matrix(TetrominoState.rotate).zipWithIndex; (j,n) <- i.zipWithIndex) {
            if(j != 0) {
                if(TetrominoState.yOffset + m + 1 >= 0 && TetrominoState.yOffset + m + 1 <= (numBlock_row - 1)) {
                    if(boardGrid(TetrominoState.yOffset + m + 1)(TetrominoState.xOffset + n) != 0) {
                        collide = true
                    }
                }
            }
        }

        collide
    }

    def isCollideRight(): Boolean = {
        var collide = false
        for((i,m) <- TetrominoState.tetromino.matrix(TetrominoState.rotate).zipWithIndex; (j,n) <- i.zipWithIndex) {
            if(j != 0) {
                if(TetrominoState.yOffset >= 0 && TetrominoState.xOffset + n + 1 >= 0 && TetrominoState.xOffset + n + 1 < numBlock_col) {
                    if((boardGrid(TetrominoState.yOffset + m)(TetrominoState.xOffset + n + 1)) != 0) {
                        collide = true    
                    }
                }
            }
        }
        
        collide
    }

    def isCollideLeft(): Boolean = {
        var collide = false
        for((i,m) <- TetrominoState.tetromino.matrix(TetrominoState.rotate).zipWithIndex; (j,n) <- i.zipWithIndex) {
            if(j != 0) {
                if(TetrominoState.yOffset >= 0 && TetrominoState.xOffset - 1 >= 0 && TetrominoState.xOffset - 1 < numBlock_col) {
                    if((boardGrid(TetrominoState.yOffset + m)(TetrominoState.xOffset - 1)) != 0) {
                        collide = true  
                    }
                }
            }
        }

        collide
    }

    def isEndRotate(): Boolean = {
        var sideLeft = numBlock_col
        var sideRight = 0
        var brick = TetrominoState.tetromino.matrix(0)
        if(TetrominoState.rotate != TetrominoState.tetromino.matrix.length - 1) {
            brick = TetrominoState.tetromino.matrix(TetrominoState.rotate + 1)
        }
        
        for((i,m) <- brick.zipWithIndex; (j,n) <- i.zipWithIndex) {
            if(j != 0) {
                if(n < sideLeft) {
                    sideLeft = n
                }

                if(n > sideRight) {
                    sideRight = n
                }
            }
        }
        (TetrominoState.xOffset + sideLeft < 0) || (TetrominoState.xOffset + sideRight > numBlock_col - 1)
    }

    def isCollideRotate(): Boolean = {
        var collideRight = false
        var collideLeft = false
        var brick = TetrominoState.tetromino.matrix(0)
        if(TetrominoState.rotate != TetrominoState.tetromino.matrix.length - 1) {
            brick = TetrominoState.tetromino.matrix(TetrominoState.rotate + 1)
        }

        for((i,m) <- brick.zipWithIndex; (j,n) <- i.zipWithIndex) {
            if(j != 0) {
                if(TetrominoState.yOffset >= 0 && TetrominoState.xOffset + n >= 0 && TetrominoState.xOffset + n < numBlock_col) {
                    if((boardGrid(TetrominoState.yOffset + m)(TetrominoState.xOffset + n)) != 0) {
                        collideRight = true    
                    }
                }

                if(TetrominoState.yOffset >= 0 && TetrominoState.xOffset >= 0 && TetrominoState.xOffset < numBlock_col) {
                    if((boardGrid(TetrominoState.yOffset + m)(TetrominoState.xOffset)) != 0) {
                        collideLeft = true  
                    }
                }
            }
        }

        collideRight || collideLeft
    }

    def onGravity(): Boolean = {
        if(! isEnd && ! isCollide) {
            TetrominoState.yOffset += 1
            true
        } else {
            stickTetromino
            clearLine
            false
        }
    }

    def clearLine() = {
        var clear = 0
        var block = 0
        for((i,m) <- boardGrid.zipWithIndex) {
            clear = 0
            for((j,n) <- i.zipWithIndex if (j != 0)) {
                clear = clear + 1
            }

            if(clear >= numBlock_col) {
                for((j,n) <- i.zipWithIndex) {
                    boardGrid(m)(n) = 0
                }

                for(k <- (m - 1) to 0 by -1) {
                    for(p <- 0 until numBlock_col if (boardGrid(k)(p) != 0)) {
                        boardGrid(k + 1)(p) = boardGrid(k)(p)
                        boardGrid(k)(p) = 0
                    }
                } 
                Assessment.line = Assessment.line + 1
            }
        }    
    }

    def stickTetromino() = {
        for((i,m) <- TetrominoState.tetromino.matrix(TetrominoState.rotate).zipWithIndex; (j,n) <- i.zipWithIndex) {
            if (j != 0) {
                if(TetrominoState.yOffset + m <= 0) {
                    boardGrid(0)(TetrominoState.xOffset + n) = TetrominoState.tetromino.code
                } else {
                    boardGrid(TetrominoState.yOffset + m)(TetrominoState.xOffset + n) = TetrominoState.tetromino.code
                }
            }
        }
    }

    def onRight(): Boolean = {
        if(! isEndRight && ! isCollideRight) {
            TetrominoState.xOffset += 1
            true
        } else {
            false
        }
    }

    def onLeft(): Boolean = {
        if(! isEndLeft && ! isCollideLeft) {
            TetrominoState.xOffset -= 1
            true
        } else {
            false
        }
    }

    def onDown(): Boolean = {
        if(! isEnd && ! isCollide) {
            TetrominoState.yOffset += 1
            true
        } else {
            false
        }
    }

    def onRotate(): Boolean = {
        if(! isEndRotate && ! isCollideRotate) {
            if(TetrominoState.rotate == TetrominoState.tetromino.matrix.length - 1) {
                TetrominoState.rotate = 0
            } else {
                TetrominoState.rotate += 1
            }
            true
        } else {
            false
        }
    }

    def checkEnd(): Boolean = {
        var lineReach = 0
        for(i <- (boardGrid.length - 1) to 0 by -1) {
            var total = 0
            for(j <- 0 until boardGrid(i).length) {
                total += (boardGrid(i)(j))
            }

            if(total > 0) {
                lineReach += 1
            }
        }
        if(lineReach >= 20) {
            true    
        } else {
            false
        }
    }
}