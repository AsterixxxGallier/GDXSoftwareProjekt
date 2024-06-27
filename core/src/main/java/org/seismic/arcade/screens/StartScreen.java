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

public class StartScreen implements Screen {
    Controller controller;

    Texture logoTexture;
    Texture buttonTexture;

    SpriteBatch batch;
    Camera camera;
    Viewport viewport;
    Stage stage;

    public StartScreen(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void show() {
        // Prepare your screen here.

        logoTexture = new Texture("seismic_arcade.png");
        buttonTexture = new Texture("play_button.png");

        batch = new SpriteBatch();
        camera = new PerspectiveCamera();
        viewport = new FitViewport(800, 480, camera);
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        Button playButton = new Button(new TextureRegionDrawable(buttonTexture));
        stage.addActor(playButton);

        float height = 80;
        float aspectRatio = (float) buttonTexture.getWidth() / buttonTexture.getHeight();
        playButton.setHeight(height);
        playButton.setWidth(height * aspectRatio);

        playButton.setPosition(300, 100, Align.center);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Clicked on play button");

                controller.playButtonPressed();
            }
        });
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.

        ScreenUtils.clear(Color.WHITE);

        batch.begin();
        float width = 400;
        float aspectRatio = 948f / 547f;
        float height = width / aspectRatio;
        batch.draw(logoTexture, 100f, 160f, width, height);
        batch.end();

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

        logoTexture.dispose();
        buttonTexture.dispose();
        batch.dispose();
    }
}
