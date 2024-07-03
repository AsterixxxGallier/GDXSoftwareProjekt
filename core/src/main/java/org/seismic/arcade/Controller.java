package org.seismic.arcade;

/**
 * Controller class for the MVC architecture.
 */
public class Controller {
    // The controller manages both the model and the view.

    public Model model;
    public View view;

    public Controller() {
        model = new Model();
        view = new View(this);
    }

    private void showScreen(String screen) {
        model.currentScreen = screen;

        if (screen.equals("tic tac toe")) {
            // initialize tic tac toe state
            model.symbol = "x";
        }

        view.showScreen(screen);
    }

    /**
     * Called when the game has been created.
     * Shows the start screen.
     */
    public void gameCreated() {
        showScreen("start");
    }

    /**
     * Called when the play button has been pressed.
     * Shows the game selection screen.
     */
    public void playButtonPressed() {
//         showScreen("game selection");
        showScreen("GameSelectionScreen");
    }

    public void tictactoeButtonPressed() {
        showScreen ("TicTacToeScreen");
    }

    public void lunarlanderButtonPressed(){
        showScreen ("LunarLanderScreen");
    }

    public void turmevonhanoiButtonPressed () {
        showScreen("Türme von Hanoi Screen");
    }

    public void symbolButtonPressed() {
        switch (model.symbol) {
            case "x":
                model.symbol = "o";
                break;
            case "o":
                model.symbol = "x";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + model.symbol);
        }

        System.out.println(model.symbol);
    }
}
