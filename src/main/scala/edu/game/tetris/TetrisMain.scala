package edu.game.tetris

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.layout.{Pane}
import scalafx.Includes._
import scalafx.scene.paint.Color
import scalafxml.core.{NoDependencyResolver, FXMLView, FXMLLoader}
import javafx.{scene => jfxs}
import scalafx.scene.shape.Rectangle
import scalafx.scene.shape.StrokeType

object TetrisMain extends JFXApp {

    val rootResource = getClass.getResource("view/TetrisGrid.fxml")
    val loader: FXMLLoader = new FXMLLoader(rootResource, NoDependencyResolver)
    loader.load();
    
    val gui: GUIController$Controller = loader.getController();

    val roots = loader.getRoot[jfxs.layout.Pane]
    stage = new PrimaryStage {
        title.value = "Tetris"
        scene = new Scene {
            content = roots
        }
    }

    new GameController(gui)
}