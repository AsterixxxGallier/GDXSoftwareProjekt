package org.seismic.arcade;

import static java.lang.Math.PI;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.MathUtils;

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

        if (screen.equals("lunar lander")) {
            model.pos = new Vector2(MathUtils.random(30, 210), 0);
            model.vel = new Vector2(0, 0);
            model.force = new Vector2(0, 0);
            model.boost = false;
            model.r = 14;
            model.angle = -PI / 2;
            model.landed = false;
            model.crashed = false;
            float angle2 = MathUtils.map(MathUtils.random(1, 100), 1, 100, -0.6f, 0.6f);
            model.angle += angle2;
        }

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


    public boolean isTooFast(){
        return model.vel.y > 2;
    }

    public boolean hasTooSteepAngle() {
        return Math.abs(model.angle + PI / 2) > 0.1;
    }

    public boolean isOnPlatform(int px, int pw) {
        return model.pos.x - model.r > px && model.pos.x + model.r < px + pw;
    }

    public boolean isTouchingPlatform(int px, int pw) {
        return model.pos.x > px && model.pos.x < px + pw;
    }

    public Vector2 edges(Vector2 pos, int size) {
        if (model.pos.x > 240 + size) {
            model.pos.x = -size;
        } else if (model.pos.x < -size) {
            model.pos.x = 240 + size;
        }
        if (model.pos.y > 210 - size) {
             model.pos.y = 210 - size;
            if (isTooFast() || hasTooSteepAngle()) {
                model.crashed = true;
            } else {
                model.landed = true;
            }
        }
        return model.pos;
    }

    public void calc() {
        if (model.boost) {
            model.force.setAngleRad((float) model.angle);
            model.force.scl(model.BOOST);
            model.vel.add(model.force);
            model.boost = false;
            model.landed = false;
        }
        model.vel.add(model.gravity);
        model.pos.add(model.vel);
        model.vel.scl(0.95f);
        if (model.landed) {
            model.vel.set(0, 0);
            model.force.set(0, 0);
        }
        model.pos = edges(model.pos, model.r);
    }


}
