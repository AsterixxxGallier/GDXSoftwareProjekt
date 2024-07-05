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
            model.symbols = new String[9];
            for (int i = 0; i < 9; i++) {
                model.symbols[i] = "empty";
            }
            model.ticTacToePlayerNow = "x";
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
        showScreen("game selection");
    }

    public void tictactoeButtonPressed() {
        showScreen ("tic tac toe");
    }

    public void lunarlanderButtonPressed(){
        showScreen ("lunar lander");
    }

    public void turmevonhanoiButtonPressed () {
        showScreen("turme von hanoi");
    }

    public void symbolButtonPressed(int i) {
        if (!model.symbols[i].equals("empty")) {
            System.err.println("PLAYER EVIL");
            return;
        }

        model.symbols[i] = model.ticTacToePlayerNow;

        System.out.print(i);
        System.out.print(": ");
        System.out.println(model.symbols[i]);

        switch (model.ticTacToePlayerNow) {
            case "x":
                model.ticTacToePlayerNow = "o";
                break;
            case "o":
                model.ticTacToePlayerNow = "x";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + model.ticTacToePlayerNow);
        }
    }
}
