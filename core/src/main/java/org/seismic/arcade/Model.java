package org.seismic.arcade;

import java.util.Stack;

/**
 * Model class for the MVC architecture.
 */
public class Model {
    public String currentScreen;

    public String[] symbols;
    public String ticTacToePlayerNow;

    public Stack<Integer>[] towers;
    public int selectedTower;
    public int moves;
    public boolean wonTowersOfHanoi;
    public float secondsSinceWon;
}
