package org.seismic.arcade;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class SeismicArcadeGame extends Game {
    Controller controller;

    public SeismicArcadeGame(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void create() {
        controller.gameCreated();
    }
}
