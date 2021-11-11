package com.github.cc3002.finalreality.gui;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.gui.scenes.PlayerCharacterCreationScene;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

/**
 * Main entry point for the application.
 * <p>
 * <Complete here with the details of the implemented application>
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */

/**
 * This class represents the view of the game.
 */

public class View extends Application {
  private GameController controller;
  private Scene scene;

  /**
   * This main method runs the game.
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws FileNotFoundException {
    setController(new GameController());
    primaryStage.setTitle("Final reality");
    primaryStage.setResizable(false);
    showWelcomeScreen();
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * This method sets the first screen of the game.
   */
  public void showWelcomeScreen(){
    VBox pane = new VBox();
    Label welcomeText = new Label("Welcome to Final Reality!");
    Font font = new Font("Cambria",32);
    welcomeText.setFont(font);
    welcomeText.setTranslateY(-20);
    Button okButton = new Button("Play");
    okButton.setPadding(new Insets(5,30,5,30));
    okButton.setOnAction((e) -> {
      controller.gameSetUp();
      PlayerCharacterCreationScene playerCharacterCreationScene = new PlayerCharacterCreationScene(this);
      playerCharacterCreationScene.begin();
    });
    pane.getChildren().add(welcomeText);
    pane.getChildren().add(okButton);
    pane.setAlignment(Pos.CENTER);
    scene = new Scene(pane,1280, 720);
  }

  public GameController getController() {
    return controller;
  }

  public Scene getScene() {
    return scene;
  }

  public void setController(GameController controller){
    this.controller = controller;
    controller.setView(this);
  }
}
