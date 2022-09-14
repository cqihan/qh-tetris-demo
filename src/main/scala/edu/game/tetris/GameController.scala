package edu.game.tetris

class GameController(val gui: GUIController$Controller) {
    
    TetrisBoard.generateTetromino
    gui.renderBoard

    gui.startGravity

}