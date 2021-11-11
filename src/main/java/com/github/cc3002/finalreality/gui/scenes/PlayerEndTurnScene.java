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

/**
 * This class is in charge of show the player the end turn screen.
 */

public class PlayerEndTurnScene extends GameScene{
    int inflictedDamage;
    String attackerName;
    String targetName;

    public PlayerEndTurnScene(View view, int inflictedDamage, String attackerName, String targetName) {
        super(view);
        this.attackerName = attackerName;
        this.inflictedDamage = inflictedDamage;
        this.targetName = targetName;
    }

    public void begin() throws FileNotFoundException {
        GameController controller = view.getController();
        Scene scene = view.getScene();

        BorderPane gameHUD = getGameHUD();
        VBox pane = showEndTurnMessage(controller);
        gameHUD.setCenter(pane);
        scene.setRoot(gameHUD);
    }

    private VBox showEndTurnMessage(GameController controller) {
        VBox vbox = new VBox();

        Font font = new Font("Cambria",32);
        Label message = new Label(attackerName+" inflicted "+ inflictedDamage + " points of damage to " + targetName+".");
        message.setFont(font);
        message.setAlignment(Pos.CENTER);

        Button okButton = new Button("Ok");
        okButton.setPadding(new Insets(5,10,5,10));
        okButton.setOnAction((e)->{
            controller.toEndTurnPhase();
            controller.tryEndTurn(controller.getTurnsQueue().peek());
        });

        vbox.getChildren().addAll(message,okButton);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }
}
