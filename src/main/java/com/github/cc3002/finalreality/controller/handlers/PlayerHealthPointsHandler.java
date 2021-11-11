package com.github.cc3002.finalreality.controller.handlers;

import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.model.character.ICharacter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayerHealthPointsHandler implements PropertyChangeListener {
    private final GameController controller;

    public PlayerHealthPointsHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.onPlayerDeath((ICharacter) evt.getNewValue());
    }
}
