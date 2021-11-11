package com.github.cc3002.finalreality.controller.handlers;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.model.character.ICharacter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EnemyTurnHandler implements PropertyChangeListener{
    private final GameController controller;

    public EnemyTurnHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.onEnemyTurn((ICharacter) evt.getNewValue());
    }
}
