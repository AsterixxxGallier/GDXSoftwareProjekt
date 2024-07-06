package org.seismic.arcade.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.seismic.arcade.Controller;

public class GameSelectionScreen implements Screen {
    Controller controller;

    Camera camera;
    Viewport viewport;
    Stage stage;
    SpriteBatch batch;

    Texture tictactoeButtonTexture;
    Texture towersOfHanoiButtonTexture;
    Texture lunarlanderButtonTexture;

    public GameSelectionScreen(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void show() {
        // Prepare your screen here.

        camera = new PerspectiveCamera();
        batch = new SpriteBatch();
        viewport = new FitViewport(800, 480, camera);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        tictactoeButtonTexture = new Texture("tictactoe.png");
        towersOfHanoiButtonTexture = new Texture("Türme von Hanoi.png");
        lunarlanderButtonTexture = new Texture("lunarlander.png");


        Button tictactoeButton = new Button(new TextureRegionDrawable(tictactoeButtonTexture));
        stage.addActor(tictactoeButton);
        Button towersOfHanoiButton = new Button(new TextureRegionDrawable(towersOfHanoiButtonTexture));
        stage.addActor(towersOfHanoiButton);
        Button lunarlanderButton = new Button(new TextureRegionDrawable(lunarlanderButtonTexture));
        stage.addActor(lunarlanderButton);

        {
            float height = 120;
            float aspectRatio = tictactoeButton.getWidth() / tictactoeButton.getHeight();
            tictactoeButton.setHeight(height);
            tictactoeButton.setWidth(height * aspectRatio);

            tictactoeButton.setPosition(125, 250, Align.center);
        }

        {
            float height = 120;
            float aspectRatio = towersOfHanoiButton.getWidth() / towersOfHanoiButton.getHeight();
            towersOfHanoiButton.setHeight(height);
            towersOfHanoiButton.setWidth(height * aspectRatio);

            towersOfHanoiButton.setPosition(325, 250, Align.center);
        }

        {
            float height = 120;
            float aspectRatio = lunarlanderButton.getWidth() / lunarlanderButton.getHeight();
            lunarlanderButton.setHeight(height);
            lunarlanderButton.setWidth(height * aspectRatio);

            lunarlanderButton.setPosition(525, 250, Align.center);
        }

        tictactoeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Clicked on tic tac toe button");

                controller.tictactoeButtonPressed();
            }

        });

        lunarlanderButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Clicked on lunar lander button");

                controller.lunarlanderButtonPressed();
            }

        });

        towersOfHanoiButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Clicked on towers of hanoi button");

                controller.towersOfHanoiButtonPressed();
            }

        });

    }
    public void render (float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        ScreenUtils.clear(new Color(0xFFEEAAFF));

        stage.draw();
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

        batch.dispose();
        lunarlanderButtonTexture.dispose();
        tictactoeButtonTexture.dispose();
        towersOfHanoiButtonTexture.dispose();
        stage.dispose();
    }

}
