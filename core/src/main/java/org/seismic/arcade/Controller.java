package org.seismic.arcade;

import java.util.Stack;

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

        if (screen.equals("towers of hanoi")) {
            // initialize towers of hanoi state
            //noinspection unchecked
            model.towers = new Stack[3];
            model.towers[0] = new Stack<>();
            model.towers[0].push(0);
            model.towers[0].push(1);
            model.towers[0].push(2);
            model.towers[1] = new Stack<>();
            model.towers[2] = new Stack<>();
            model.selectedTower = -1;
            model.moves = 0;
            model.wonTowersOfHanoi = false;
            model.secondsSinceWon = 0;
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

    public void towersOfHanoiButtonPressed() {
        showScreen("towers of hanoi");
    }

    public void towersOfHanoiDone() {
        showScreen("game selection");
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

    public void towersOfHanoiNumberPressed(int tower) {
        if (tower > 2 || 0 > tower) {
            return;
        }
        if (model.wonTowersOfHanoi) {
            return;
        }
        if (model.selectedTower == -1) {
            if (!model.towers[tower].isEmpty()) {
                model.selectedTower = tower;
            }
        } else if (model.selectedTower == tower) {
            deselectTower();
        } else {
            movePlate(model.selectedTower, tower);
            deselectTower();
        }
        if (model.towers[2].size() == 3) {
            model.wonTowersOfHanoi = true;
        }
    }

    private void deselectTower() {
        model.selectedTower = -1;
    }

    private void movePlate(int from, int to) {
        Stack<Integer> fromTower = model.towers[from];
        Stack<Integer> toTower = model.towers[to];
        if (fromTower.isEmpty()) {
            return;
        }
        if (!toTower.isEmpty() && toTower.peek() > fromTower.peek()) {
            return;
        }
        toTower.push(fromTower.pop());
        model.moves++;
    }
}
