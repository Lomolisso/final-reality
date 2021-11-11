package com.github.cc3002.finalreality.gui.scenes;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.gui.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.FileNotFoundException;

/**
 * This class is in charge of show the player the end creation screen.
 */

public class PlayerCharacterCreationScene {
    View view;
    int characterCount = 0;

    public PlayerCharacterCreationScene(View view) {
        this.view = view;
    }

    public void begin(){
        GameController controller = view.getController();
        Scene scene = view.getScene();
        VBox root = getCreationScreen(controller);
        scene.setRoot(root);
    }

    private VBox getCreationScreen(GameController controller) {
        VBox vBox = new VBox();
        Label creationMessage1 = new Label("In order to play the game, you must create 5 characters!");
        Font font1 = new Font("Cambria", 32);
        creationMessage1.setFont(font1);
        creationMessage1.setTranslateY(-10);

        Label creationMessage2 = new Label("Choose a name and class for each one of your heroes!");
        Font font2 = new Font("Cambria", 20);
        creationMessage2.setFont(font2);
        creationMessage2.setTranslateY(-10);

        FlowPane flowPane = new FlowPane();
        TextField nameReader = new TextField();

        nameReader.setMaxSize(700, 30);
        nameReader.setEditable(true);

        Button createKnight = new Button("Knight");
        createKnight.setPadding(new Insets(20, 30, 20, 30));
        createKnight.setOnAction((e) -> {
            String knightName = nameReader.getText();
            if (knightName.equals("")) {
                knightName = "Knight";
            }
            controller.createKnight(knightName, 200, 50);
            characterCount++;
            try {
                runNextScene();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        Button createWhiteMage = new Button("White Mage");
        createWhiteMage.setPadding(new Insets(20, 30, 20, 30));
        createWhiteMage.setOnAction((e) -> {
            String whiteMageName = nameReader.getText();
            if (whiteMageName.equals("")) {
                whiteMageName = "White Mage";
            }
            controller.createWhiteMage(whiteMageName, 80, 20);
            characterCount++;
            try {
                runNextScene();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        Button createThief = new Button("Thief");
        createThief.setPadding(new Insets(20, 30, 20, 30));
        createThief.setOnAction((e) -> {
            String thiefName = nameReader.getText();
            if (thiefName.equals("")) {
                thiefName = "Thief";
            }
            controller.createThief(thiefName, 90, 25);
            characterCount++;
            try {
                runNextScene();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        Button createBlackMage = new Button("Black Mage");
        createBlackMage.setPadding(new Insets(20, 30, 20, 30));
        createBlackMage.setOnAction((e) -> {
            String blackMageName = nameReader.getText();
            if (blackMageName.equals("")) {
                blackMageName = "Black Mage";
            }
            controller.createBlackMage(blackMageName, 80, 20);
            characterCount++;
            try {
                runNextScene();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        Button createEngineer = new Button("Engineer");
        createEngineer.setPadding(new Insets(20, 30, 20, 30));
        createEngineer.setOnAction((e) -> {
            String engineerName = nameReader.getText();
            if (engineerName.equals("")) {
                engineerName = "Engineer";
            }
            controller.createEngineer(engineerName, 100, 30);
            characterCount++;
            try {
                runNextScene();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        flowPane.getChildren().addAll(createKnight, createWhiteMage, createThief, createBlackMage, createEngineer);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setVgap(10);
        flowPane.setHgap(10);
        flowPane.setTranslateY(15);
        vBox.getChildren().addAll(creationMessage1, creationMessage2, nameReader, flowPane);
        vBox.setAlignment(Pos.CENTER);

        return vBox;
    }

    private void runNextScene() throws FileNotFoundException {
        if(characterCount>=5){
            GameController controller = view.getController();
            Scene scene = view.getScene();

            controller.tryStartTurns();
            VBox pane = waitingScreen();

            scene.setRoot(pane);
        }
        else{
            begin();
        }
    }

    private VBox waitingScreen(){
        VBox vbox = new VBox();
        Label waitingMessage = new Label("Please wait a moment...");
        Font font = new Font("Cambria",32);
        waitingMessage.setFont(font);
        vbox.getChildren().add(waitingMessage);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }
}
