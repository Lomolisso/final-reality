package com.github.cc3002.finalreality.gui.scenes;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.gui.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.FileNotFoundException;

/**
 * This class is in charge of show the player the begin turn screen of a player character.
 */

public class PlayerBeginTurnScene extends GameScene{
    VBox vBox = new VBox();

    public PlayerBeginTurnScene(View view) {
        super(view);
    }

    public void begin() throws FileNotFoundException {
        GameController controller = view.getController();
        Scene scene = view.getScene();

        BorderPane gameHUD = getGameHUD();
        VBox pane = playerTurnMessage(controller);
        gameHUD.setCenter(pane);
        scene.setRoot(gameHUD);
    }

    public VBox playerTurnMessage(GameController controller){
        VBox pane = new VBox();
        String playerCharacterName = controller.getNameCharacterInTurn();
        Label turnText = new Label("It's " +playerCharacterName+"'s Turn !");
        Font font1 = new Font("Cambria",32);
        turnText.setFont(font1);
        turnText.setTranslateY(-20);
        turnText.setTranslateX(-35);

        String weaponName = controller.getNameWeaponPlayerInTurn();
        String weaponText;
        if (weaponName==""){
            weaponText = playerCharacterName+" doesn't have a weapon equipped yet!";
        }
        else{
            weaponText = playerCharacterName + " has " + weaponName + " equipped!";
        }
        Label weaponUserMessage = new Label(weaponText);
        Font font2 = new Font("Cambria",20);
        weaponUserMessage.setFont(font2);
        weaponUserMessage.setTranslateY(-15);
        weaponUserMessage.setTranslateX(-35);

        HBox buttonsBox = new HBox();
        buttonsBox.setAlignment(Pos.CENTER);

        Button attackButton = new Button("Attack");
        attackButton.setPadding(new Insets(5,15,5,15));
        attackButton.setTranslateX(-40);
        attackButton.setOnAction((e)->{
            controller.toSelectAttackTargetPhase();
            AttackingEnemyScene attackingEnemyScene = new AttackingEnemyScene(view,0);
            try {
                attackingEnemyScene.begin();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });


        Button equipWeaponButton = new Button("Equip a weapon");
        equipWeaponButton.setPadding(new Insets(5,15,5,15));
        equipWeaponButton.setTranslateX(-30);
        equipWeaponButton.setOnAction((e)->{
            controller.toEquipWeaponPhase();
            EquipWeaponScene equipWeaponScene = new EquipWeaponScene(view,0,controller.getInventory().size());
            try {
                equipWeaponScene.begin();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        buttonsBox.getChildren().add(attackButton);
        buttonsBox.getChildren().add(equipWeaponButton);

        pane.getChildren().add(turnText);
        pane.getChildren().add(weaponUserMessage);
        pane.getChildren().add(buttonsBox);
        pane.setAlignment(Pos.CENTER);

        return pane;
    }
}
