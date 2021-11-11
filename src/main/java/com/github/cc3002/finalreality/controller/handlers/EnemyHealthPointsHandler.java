package com.github.cc3002.finalreality.controller.handlers;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.model.character.ICharacter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EnemyHealthPointsHandler implements PropertyChangeListener{
    private final GameController controller;

    public EnemyHealthPointsHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.onEnemyDeath((ICharacter) evt.getNewValue());
    }
}
