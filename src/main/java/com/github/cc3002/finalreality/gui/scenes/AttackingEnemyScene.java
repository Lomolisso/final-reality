package com.github.cc3002.finalreality.gui.scenes;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.gui.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This class is in charge of show the player the attack selection screen.
 */

public class AttackingEnemyScene extends GameScene{
    int indexEnemy;
    public AttackingEnemyScene(View view, int indexEnemy) {
        super(view);
        this.indexEnemy = indexEnemy;
    }

    public void begin() throws FileNotFoundException {
        GameController controller = view.getController();
        Scene scene = view.getScene();

        BorderPane gameHUB = getGameHUD();
        VBox pane = showTargetCandidate(controller);
        gameHUB.setCenter(pane);
        scene.setRoot(gameHUB);
    }

    private VBox showTargetCandidate(GameController controller) throws FileNotFoundException {
        VBox vbox = new VBox();

        ImageView weaponSkin = new ImageView(new Image(new FileInputStream(controller.getEnemiesSkins().get(indexEnemy))));
        weaponSkin.setScaleX(2);
        weaponSkin.setScaleY(2);
        weaponSkin.setTranslateY(-10);
        weaponSkin.setTranslateX(-5);

        String enemyName = controller.getNamesEnemiesAlive().get(indexEnemy);
        String enemyHp = controller.getCurrentHpEnemiesAlive().get(indexEnemy).toString();
        String enemyAtk = controller.getAtkEnemiesAlive().get(indexEnemy).toString();
        String enemyDef = controller.getDefEnemiesAlive().get(indexEnemy).toString();

        Font font = new Font("Cambria", 32);
        Label nameText = new Label("\nName: " + enemyName);
        nameText.setStyle("-fx-font-weight: bold");
        Label hpText = new Label("HP: " + enemyHp);
        hpText.setStyle("-fx-font-weight: bold");
        Label defText = new Label("DEF: " + enemyDef);
        defText.setStyle("-fx-font-weight: bold");
        Label atkText = new Label("Attack: " + enemyAtk);
        atkText.setStyle("-fx-font-weight: bold");

        HBox hbox = new HBox();
        hbox.setTranslateY(10);
        hbox.setAlignment(Pos.CENTER);

        Button prevButton = new Button("Previous");
        prevButton.setPadding(new Insets(5, 10, 5, 10));
        prevButton.setOnAction((e) -> {
            int enemiesSize = controller.getEnemies().size();
            int newIndex = getNewIndex(indexEnemy-1,enemiesSize);
            AttackingEnemyScene attackingEnemyScene = new AttackingEnemyScene(view,newIndex);
            try {
                attackingEnemyScene.begin();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        Button equipButton = new Button("Attack");
        equipButton.setPadding(new Insets(5, 10, 5, 10));
        equipButton.setOnAction((e) -> {
            String attackerName = controller.getNameCharacterInTurn();
            String targetName = controller.getNamesEnemiesAlive().get(indexEnemy);
            int inflictedDamage = controller.tryActionAttackCharacter(controller.getTurnsQueue().peek(),controller.getEnemies().get(indexEnemy));
            if(controller.getGameStatus()!=1) {
                PlayerEndTurnScene PLayerEndTurnScene = new PlayerEndTurnScene(view, inflictedDamage, attackerName, targetName);
                controller.toEndTurnPhase();
                try {
                    PLayerEndTurnScene.begin();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
            else{
                PlayerWinsScene playerWinsScene = new PlayerWinsScene(view);
                playerWinsScene.begin();
            }
        });

        Button nextButton = new Button("Next");
        nextButton.setPadding(new Insets(5, 10, 5, 10));
        nextButton.setOnAction((e) -> {
            System.out.println("Antes de apretar el boton"+indexEnemy);
            int enemiesSize = controller.getEnemies().size();
            int newIndex = getNewIndex(indexEnemy+1,enemiesSize);
            System.out.println("Despues de apretar el boton"+newIndex);
            AttackingEnemyScene attackingEnemyScene = new AttackingEnemyScene(view,newIndex);
            try {
                attackingEnemyScene.begin();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        hbox.getChildren().addAll(prevButton, equipButton, nextButton);
        hbox.setSpacing(10);
        vbox.getChildren().addAll(weaponSkin, nameText, hpText, defText, atkText, hbox);

        vbox.setAlignment(Pos.CENTER);

        return vbox;
    }

    int getNewIndex(int index,int n){
        if(index>=0){return index%n;}
        return ((index%n)+n)%n;
    }
}
