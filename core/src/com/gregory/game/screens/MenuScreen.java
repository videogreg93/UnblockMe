package com.gregory.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gregory.game.MainApplication;

public class MenuScreen implements Screen {


    private MainApplication parent;
    private Stage stage;

    public MenuScreen(MainApplication mainApplication){

        parent = mainApplication;
        /// create stage and set it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);


        //create buttons
        Texture playTexture = new Texture("playButton.png");
        Drawable playDrawable = new TextureRegionDrawable(new TextureRegion(playTexture));
        ImageButton playButton = new ImageButton(playDrawable);

        Texture aboutTexture = new Texture("aboutButton.png");
        Drawable aboutDrawable = new TextureRegionDrawable(new TextureRegion(aboutTexture));
        ImageButton aboutButton = new ImageButton(aboutDrawable);

        Texture exitTexture = new Texture("exitButton.png");
        Drawable exitDrawable = new TextureRegionDrawable(new TextureRegion(exitTexture));
        ImageButton exitButton = new ImageButton(exitDrawable);



        //add buttons to table
        table.add(playButton).fillX().uniformX();
        table.row().pad(20, 0, 20, 0);
        table.add(aboutButton).fillX().uniformX();
        table.row().pad(0, 0, 20, 0);
        table.add(exitButton).fillX().uniformX();

        // create button listeners
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(MainApplication.APPLICATION);
            }
        });

//        preferences.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                parent.changeScreen(Box2DTutorial.PREFERENCES);
//            }
//        });

        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        // clear the screen ready for next set of images to be drawn
        Gdx.gl.glClearColor(66/255f, 77/255f, 100/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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
    }
}
