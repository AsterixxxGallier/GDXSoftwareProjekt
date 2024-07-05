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

public class TicTacToeScreen implements Screen {
    Controller controller;

    Texture backgroundTexture;
    Texture xTexture;
    Texture oTexture;
    Texture emptyTexture;

    SpriteBatch batch;
    Camera camera;
    Viewport viewport;
    Stage stage;

    // 0 = unten links, 1 = unten mitte, ...
    Button[] symbolButtons;

    public TicTacToeScreen(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void show() {
        // Prepare your screen here.

        backgroundTexture = new Texture("tictactoe.jpg");
        xTexture = new Texture("x.jpg");
        oTexture = new Texture("o.jpg");
        emptyTexture = new Texture("empty.jpg");

        batch = new SpriteBatch();
        camera = new PerspectiveCamera();
        viewport = new FitViewport(800, 480, camera);
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        symbolButtons = new Button[9];

        float bottom_right_x = 196f;
        float bottom_right_y = 63f;

        for (int i = 0; i < 9; i++) {
            int x = i % 3;
            int y = i / 3;

            symbolButtons[i] = new Button(new TextureRegionDrawable(emptyTexture));
            stage.addActor(symbolButtons[i]);

            float sideLength = 126f;
            symbolButtons[i].setHeight(sideLength);
            symbolButtons[i].setWidth(sideLength);

            float withPadding = 130f;

            symbolButtons[i].setPosition(bottom_right_x + withPadding * x, bottom_right_y + withPadding * y, Align.center);

            int finalI = i;
            symbolButtons[i].addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("Clicked on symbol button");

                    controller.symbolButtonPressed(finalI);
                }
            });
        }
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.

        ScreenUtils.clear(Color.WHITE);

        batch.begin();
        float width = 390;
        float aspectRatio = 474f / 613f;
        float height = width / aspectRatio;
        batch.draw(backgroundTexture, 130f, 5f, width, height);
        batch.end();

        for (int i = 0; i < 9; i++) {
            switch (controller.model.symbols[i]) {
                case "x":
                    symbolButtons[i].getStyle().up = new TextureRegionDrawable(xTexture);
                    break;
                case "o":
                    symbolButtons[i].getStyle().up = new TextureRegionDrawable(oTexture);
                    break;
                case "empty":
                    symbolButtons[i].getStyle().up = new TextureRegionDrawable(emptyTexture);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + controller.model.symbols[i]);
            }
        }

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.

        viewport.update(width, height);
        stage.getViewport().update(width, height);
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

        backgroundTexture.dispose();
        xTexture.dispose();
        oTexture.dispose();
        batch.dispose();
    }
}
