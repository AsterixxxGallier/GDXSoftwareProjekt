package org.seismic.arcade.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.seismic.arcade.Controller;
import com.badlogic.gdx.math.MathUtils;

public class LunarLanderScreen implements Screen {
    Controller controller;

    ShapeRenderer shape;
    Camera camera;
    Viewport viewport;
    SpriteBatch batch;
    Stage stage;

    Texture LunarLanderBackgroundTexture;

    public LunarLanderScreen(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void show() {
        // Prepare your screen here.

        LunarLanderBackgroundTexture = new Texture("LunarLanderBackground.png");

        shape = new ShapeRenderer();
        camera = new PerspectiveCamera();
        viewport = new FitViewport(800, 480, camera);
        batch = new SpriteBatch();



        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        batch.draw(LunarLanderBackgroundTexture, 0, 0);
        batch.end();

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.identity();
        shape.translate(controller.model.pos.x, controller.model.pos.y, 0);
        shape.rotate(0, 0, 1, (float) controller.model.angle * MathUtils.radiansToDegrees + 90);

        shape.setColor(Color.BLACK);
        shape.rect(-controller.model.r, -controller.model.r + 15, 28, controller.model.r);

        if (controller.model.boost) {
            shape.setColor(Color.RED);
            shape.circle(0, controller.model.r, 10);
            shape.setColor(new Color(1, 224 / 255f, 32 / 255f, 1));
            shape.circle(0, controller.model.r, 4);
        }
        shape.setColor(Color.GRAY);
        shape.rect((float) -controller.model.r / 2, -controller.model.r + 10, controller.model.r, controller.model.r + 4);
        shape.setColor(Color.LIGHT_GRAY);
        shape.rect(-controller.model.r + 10, -controller.model.r, (controller.model.r * 2) - 20, controller.model.r * 2);

        shape.end();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            controller.moveRocketLeft();
            controller.calc();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
           controller.moveRocketRight();
           controller.calc();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            controller.moveRocketUp();
            controller.calc();
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
        LunarLanderBackgroundTexture.dispose();
    }
}
