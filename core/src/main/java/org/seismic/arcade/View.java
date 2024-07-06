package org.seismic.arcade;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import org.seismic.arcade.screens.*;

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
    Screen towersOfHanoiScreen;

    public View(Controller controller) {
        this.controller = controller;

        game = new SeismicArcadeGame(controller);
        startScreen = new StartScreen(controller);
        gameSelectionScreen = new GameSelectionScreen(controller);
        ticTacToeScreen = new TicTacToeScreen(controller);
        lunarLanderScreen = new LunarLanderScreen(controller);
        towersOfHanoiScreen = new TowersOfHanoiScreen(controller);
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
            case "towers of hanoi":
                game.setScreen(towersOfHanoiScreen);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + screen);
        }
    }
}
