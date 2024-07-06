package org.seismic.arcade.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.seismic.arcade.Controller;

import java.util.Stack;

public class TowersOfHanoiScreen implements Screen {
    Controller controller;

    ShapeRenderer shape;
    SpriteBatch batch;
    Camera camera;
    Viewport viewport;

    BitmapFont font;

    public TowersOfHanoiScreen(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void show() {
        // Prepare your screen here.

        shape = new ShapeRenderer();
        batch = new SpriteBatch();
        camera = new PerspectiveCamera();
        viewport = new FitViewport(800, 480, camera);

        font = new BitmapFont();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Comic Sans MS.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 100;
        font = generator.generateFont(parameter);

        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.

        ScreenUtils.clear(Color.SKY);

        int[] towerXs = new int[]{155, 305, 455};
        int[] plateYs = new int[]{130, 180, 230};
        int[] plateWidths = new int[]{120, 100, 80};

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.BROWN);
        shape.rect(towerXs[0] - 15, 120, 30, 200);
        shape.end();

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.BROWN);
        shape.rect(towerXs[1] - 15, 120, 30, 200);
        shape.end();

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.BROWN);
        shape.rect(towerXs[2] - 15, 120, 30, 200);
        shape.end();

        for (int i = 0; i < 3; i++) {
            Stack<Integer> plates = controller.model.towers[i];
            int x = towerXs[i];

            for (int j = 0; j < plates.size(); j++) {
                int y = plateYs[j];
                int width = plateWidths[plates.get(j)];

                shape.begin(ShapeRenderer.ShapeType.Filled);
                if (controller.model.selectedTower == i && j == plates.size() - 1) {
                    shape.setColor(Color.LIME);
                } else {
                    shape.setColor(Color.OLIVE);
                }
                //noinspection IntegerDivisionInFloatingPointContext
                shape.rect(x - width / 2, y, width, 40);
                shape.end();
            }
        }

        if (controller.model.wonTowersOfHanoi) {
            batch.begin();
            String message = "You won in " + controller.model.moves + " moves!";
            font.draw(batch, message, 50, 350, 500, Align.center, true);
            batch.end();

            controller.model.secondsSinceWon += delta;

            if (controller.model.secondsSinceWon >= 5) {
                controller.towersOfHanoiDone();
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            controller.towersOfHanoiNumberPressed(0);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            controller.towersOfHanoiNumberPressed(1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            controller.towersOfHanoiNumberPressed(2);
        }
    }

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.

        viewport.update(width, height);
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.

        shape.dispose();
        batch.dispose();
    }
}
