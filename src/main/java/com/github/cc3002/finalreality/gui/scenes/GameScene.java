package com.github.cc3002.finalreality.gui.scenes;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.gui.View;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * This class holds the common behaviour of any scene of the game.
 */

public abstract class GameScene {
    View view;

    public GameScene(View view) {
        this.view = view;
    }

    /**
     * This method creates and return a HUD for the game.
     * @return
     * @throws FileNotFoundException
     */
    public BorderPane getGameHUD() throws FileNotFoundException {
        GameController controller = view.getController();
        Scene scene = view.getScene();

        Label gameName = generateText("Final Reality");
        gameName.setAlignment(Pos.CENTER);
        Label characterInTurn = generateText(controller.getNameCharacterInTurn()+" turn!");
        characterInTurn.setAlignment(Pos.CENTER);

        VBox playerCharacters = new VBox();
        int alivePlayerCharacters = controller.getPlayerCharacters().size();
        LinkedList<String> playerCharacterImages = controller.getPlayerCharacterSkins();


        LinkedList<String> playerCharactersNames = controller.getNamesPlayerCharactersAlive();
        LinkedList<Integer> playerCharactersHps = controller.getCurrentHpPlayerCharactersAlive();
        LinkedList<Integer> playerCharactersDefs = controller.getDefPlayerCharactersAlive();
        LinkedList<Integer> playerCharactersAtks = controller.getAtkPlayerCharactersAlive();

        for(int k=0; k < alivePlayerCharacters; k++){
            Font font = new Font("Cambria",18);
            Label pText = new Label(playerCharactersNames.get(k)+"\nHP: "+playerCharactersHps.get(k).toString()+"\nDEF: "+playerCharactersDefs.get(k).toString()+"\nATK: "+playerCharactersAtks.get(k).toString()+"\n \n");
            pText.setFont(font);
            ImageView img = new ImageView(new Image(new FileInputStream(playerCharacterImages.get(k))));
            img.setScaleX(1.5);
            img.setScaleY(1.5);
            pText.setTranslateX(80);
            Group pInfo = new Group(img,pText);
            playerCharacters.getChildren().add(pInfo);
        }
        playerCharacters.setAlignment(Pos.CENTER_LEFT);

        VBox enemies = new VBox();
        int aliveEnemies = controller.getEnemies().size();
        LinkedList<String> enemiesImages = controller.getEnemiesSkins();

        LinkedList<String> enemiesNames = controller.getNamesEnemiesAlive();
        LinkedList<Integer> enemiesHps = controller.getCurrentHpEnemiesAlive();
        LinkedList<Integer> enemiesDefs = controller.getDefEnemiesAlive();
        LinkedList<Integer> enemiesAtks = controller.getAtkEnemiesAlive();

        for(int k=0; k < aliveEnemies; k++){
            Font font = new Font("Cambria",18);
            Label eText = new Label(enemiesNames.get(k)+"\nHP: "+enemiesHps.get(k).toString()+"\nDEF: "+enemiesDefs.get(k).toString()+"\nATK: "+enemiesAtks.get(k).toString()+"\n \n");
            eText.setFont(font);
            ImageView img = new ImageView(new Image(new FileInputStream(enemiesImages.get(k))));
            img.setScaleX(1.5);
            img.setScaleY(1.5);
            eText.setTranslateX(-115);
            Group eInfo = new Group(img,eText);
            enemies.getChildren().add(eInfo);
        }
        enemies.setAlignment(Pos.CENTER_RIGHT);
        BorderPane hud = new BorderPane();
        hud.setTop(gameName);
        hud.setBottom(characterInTurn);
        hud.setLeft(playerCharacters);
        hud.setRight(enemies);
        return hud;
    }

    public Label generateText(String text){
        Label label = new Label(text);
        label.setFont(new Font("Cambria",32));
        label.setAlignment(Pos.CENTER);
        return label;
    }

}
