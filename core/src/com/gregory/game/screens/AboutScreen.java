package com.gregory.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gregory.game.MainApplication;
import com.gregory.game.Utils.FontGenerator;
import com.gregory.game.ui.MenuButton;

import static com.gregory.game.Utils.Screens.MENU;

public class AboutScreen implements Screen {


    private Stage stage;
    private BitmapFont font;
    private SpriteBatch batch;
    private MainApplication parent;

    public AboutScreen(MainApplication mainApplication) {
        parent = mainApplication;
        /// create stage and set it as input processor
    }


    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        batch = new SpriteBatch();
        font = FontGenerator.getInstance().font;

        MenuButton menuButton = new MenuButton(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 10, (float)1/4);
        menuButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                parent.changeScreen(MENU);
                return true;
            }
        });
        stage.addActor(menuButton);
    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(66 / 255f, 77 / 255f, 100 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        CharSequence str = "Gregory Fournier\nErwan Bouet\nTheo Moffelein";

        batch.begin();
        font.draw(batch, str, 0 , 2 * Gdx.graphics.getHeight() / 3f, Gdx.graphics.getWidth(), Align.center, true);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // change the stage's viewport when teh screen size is changed
        stage.getViewport().update(width, height, true);
    }


    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        // dispose of assets when not needed anymore
        stage.dispose();
        batch.dispose();
    }
}
