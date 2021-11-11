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
 * This class is in charge of show the player the weapon selection screen.
 */

public class EquipWeaponScene extends GameScene{
    int indexWeapon;
    int inventorySize;

    public EquipWeaponScene(View view, int indexWeapon, int invSize) {
        super(view);
        this.indexWeapon = indexWeapon;
        this.inventorySize = invSize;
    }

    public void begin() throws FileNotFoundException {
        GameController controller = view.getController();
        Scene scene = view.getScene();

        BorderPane gameHUD = getGameHUD();
        VBox pane = showWeapon(controller);
        gameHUD.setCenter(pane);
        scene.setRoot(gameHUD);
    }

    private VBox showWeapon(GameController controller) throws FileNotFoundException {
        VBox vbox = new VBox();

        if (inventorySize>0) {

            ImageView weaponSkin = new ImageView(new Image(new FileInputStream(controller.getSkinInventoryWeapons().get(indexWeapon))));
            weaponSkin.setScaleX(2);
            weaponSkin.setScaleY(2);
            weaponSkin.setTranslateY(-10);
            weaponSkin.setTranslateX(-5);

            String weaponName = controller.getNameInventoryWeapons().get(indexWeapon);
            String weaponAtk = controller.getAtkInventoryWeapons().get(indexWeapon).toString();
            String weaponWeight = controller.getWeightInventoryWeapons().get(indexWeapon).toString();

            Font font = new Font("Cambria", 32);
            Label nameText = new Label("\nName: " + weaponName);
            nameText.setStyle("-fx-font-weight: bold");
            Label atkText = new Label("Attack: " + weaponAtk);
            atkText.setStyle("-fx-font-weight: bold");
            Label weightText = new Label("Weight: " + weaponWeight);
            weightText.setStyle("-fx-font-weight: bold");

            HBox hbox = new HBox();
            hbox.setTranslateY(10);
            hbox.setAlignment(Pos.CENTER);

            Button prevButton = new Button("Previous");
            prevButton.setPadding(new Insets(5, 10, 5, 10));
            prevButton.setOnAction((e) -> {
                EquipWeaponScene equipWeaponScene = new EquipWeaponScene(view,getNewIndex(indexWeapon-1,inventorySize),inventorySize);
                try {
                    equipWeaponScene.begin();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            });

            Button equipButton = new Button("Equip Weapon");
            equipButton.setPadding(new Insets(5, 10, 5, 10));
            equipButton.setOnAction((e) -> {
                controller.tryEquipWeapon(controller.getPlayerCharacterInTurn(),controller.getInventory().get(indexWeapon));
                controller.toPlayerBeginTurnPhase();
                PlayerBeginTurnScene playerBeginTurnScene = new PlayerBeginTurnScene(view);
                try {
                    playerBeginTurnScene.begin();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            });

            Button nextButton = new Button("Next");
            nextButton.setPadding(new Insets(5, 10, 5, 10));
            nextButton.setOnAction((e) -> {
                EquipWeaponScene equipWeaponScene = new EquipWeaponScene(view,getNewIndex(indexWeapon+1,inventorySize),inventorySize);
                try {
                    equipWeaponScene.begin();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            });

            hbox.getChildren().addAll(prevButton, equipButton, nextButton);
            hbox.setSpacing(10);
            vbox.getChildren().addAll(weaponSkin, nameText,atkText,weightText, hbox);
        }
        else{
            Label emptyInventoryMessage = new Label("Your inventory is empty!");
            Font font  = new Font("Cambria",32);
            emptyInventoryMessage.setFont(font);
            Button okButton = new Button("Ok");
            okButton.setPadding(new Insets(5,10,5,10));
            okButton.setOnAction((e)->{
                controller.toPlayerBeginTurnPhase();
                PlayerBeginTurnScene playerBeginTurnScene = new PlayerBeginTurnScene(view);
                try {
                    playerBeginTurnScene.begin();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            });
            vbox.getChildren().addAll(emptyInventoryMessage,okButton);
        }
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    int getNewIndex(int index,int n){
        if(index>=0){return index%n;}
        return ((index%n)+n)%n;
    }
}
