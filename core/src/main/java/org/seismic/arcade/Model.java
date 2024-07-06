package org.seismic.arcade;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import static java.lang.Math.PI;

import java.util.Stack;

/**
 * Model class for the MVC architecture.
 */
public class Model {
    public String currentScreen;

    public String[] symbols;
    public String ticTacToePlayerNow;

    public Vector2 pos;
    public Vector2 vel;
    public Vector2 force;
    public int r;
    public float angle;
    public boolean boost;
    public boolean landed;
    public boolean crashed;

    final float PI = MathUtils.PI;
    final float BOOST = 10.0f;
    final Vector2 gravity = new Vector2(0, -0.1f);

    public Stack<Integer>[] towers;
    public int selectedTower;
    public int moves;
    public boolean wonTowersOfHanoi;
    public float secondsSinceWon;
}
