package com.github.cc3002.finalreality.gui.scenes;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.gui.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * This class is in charge of show the player the enemy turn screen.
 */

public class EnemyTurnScene extends GameScene{

    public EnemyTurnScene(View view) {
        super(view);
    }

    public void begin() throws FileNotFoundException {
        GameController controller = view.getController();
        Scene scene = view.getScene();

        BorderPane gameHUD = getGameHUD();
        VBox pane = enemyTurnMessage(controller);
        gameHUD.setCenter(pane);
        scene.setRoot(gameHUD);
    }

    private VBox enemyTurnMessage(GameController controller) throws FileNotFoundException {
        VBox vbox = new VBox();

        String playerCharacterName = controller.getNameCharacterInTurn();
        Label turnText = new Label("It's " +playerCharacterName+"'s Turn !");
        Font font1 = new Font("Cambria",32);
        turnText.setFont(font1);
        turnText.setTranslateY(-20);

        LinkedList info = controller.tryActionAttackRandomPlayerCharacter(controller.getTurnsQueue().peek());

        int inflictedDamage = (int) info.get(0);
        String targetName = (String) info.get(1);
        String attackerName = controller.getNameCharacterInTurn();

        Label attackMessage = new Label(attackerName+" inflicted " + inflictedDamage+" points of damage to " + targetName);
        attackMessage.setTranslateY(-10);
        Font font2 = new Font("Cambria",20);
        attackMessage.setFont(font2);

        Button okButton = new Button("Ok");
        okButton.setPadding(new Insets(5,10,5,10));
        okButton.setOnAction((e)->{
            if(controller.getGameStatus()!=-1) {
                controller.toEndTurnPhase();
                controller.tryEndTurn(controller.getTurnsQueue().peek());
            }
            else{
                PlayerLosesScene playerLosesScene = new PlayerLosesScene(view);
                    playerLosesScene.begin();
            }
        });

        vbox.getChildren().addAll(turnText,attackMessage,okButton);
        vbox.setAlignment(Pos.CENTER);

        return vbox;
    }
}
