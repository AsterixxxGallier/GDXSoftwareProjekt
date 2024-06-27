package org.seismic.arcade;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import org.seismic.arcade.screens.GameSelectionScreen;
import org.seismic.arcade.screens.LunarLanderScreen;
import org.seismic.arcade.screens.StartScreen;
import org.seismic.arcade.screens.TicTacToeScreen;

/**
 * View class for the MVC architecture.
 */
public class View {
    Controller controller;

    public Game game;

    Screen startScreen;
    Screen gameSelectionScreen;
    Screen ticTacToeScreen;
    Screen lunarLanderScreen;

    public View(Controller controller) {
        this.controller = controller;

        game = new SeismicArcadeGame(controller);
        startScreen = new StartScreen(controller);
        gameSelectionScreen = new GameSelectionScreen(controller);
        ticTacToeScreen = new TicTacToeScreen(controller);
        lunarLanderScreen = new LunarLanderScreen(controller);
    }

    /**
     * Shows the specified screen.
     */
    void showScreen(String screen) {
        switch (screen) {
            case "start":
                game.setScreen(startScreen);
                break;
            case "game selection":
                game.setScreen(gameSelectionScreen);
                break;
            case "tic tac toe":
                game.setScreen(ticTacToeScreen);
                break;
            case "lunar lander":
                game.setScreen(lunarLanderScreen);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + screen);
        }
    }
}
