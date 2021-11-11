package com.github.cc3002.finalreality.gui.scenes;

import com.github.cc3002.finalreality.gui.View;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * This class is in charge of show the player the win screen.
 */

public class PlayerWinsScene extends GameScene{

    public PlayerWinsScene(View view) {
        super(view);
    }

    public void begin(){
        Scene scene = view.getScene();

        BorderPane pane = new BorderPane();
        Label winMessage = generateText("You Win!");
        winMessage.setAlignment(Pos.CENTER);
        pane.setCenter(winMessage);

        scene.setRoot(pane);
    }
}
