package com.github.cc3002.finalreality.controller.phases.subphases;

import com.github.cc3002.finalreality.controller.phases.InvalidMethodCalledException;
import com.github.cc3002.finalreality.controller.phases.Phase;

import java.util.Objects;

public class EndGamePhase extends Phase {
    public EndGamePhase(String phaseName) {
        super(phaseName);
    }

    @Override
    public void playerWins() throws InvalidMethodCalledException {
        controller.playerWins();
    }

    @Override
    public void playerLoses() throws InvalidMethodCalledException {
        controller.playerLoses();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o){
            return true;}
        if (!(o instanceof EndGamePhase)){
            return false;
        }
        final EndGamePhase that = (EndGamePhase) o;
        return getPhaseName().equals(that.getPhaseName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhaseName());
    }
}
