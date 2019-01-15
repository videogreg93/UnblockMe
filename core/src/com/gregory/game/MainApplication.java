package com.gregory.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gregory.game.objects.Truck;
import com.gregory.game.screens.PuzzleScreen;

public class MainApplication extends Game {


    @Override
    public void create() {
        loadTextures();
        setScreen(new PuzzleScreen());


    }

    @Override
    public void render() {
        getScreen().render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {

    }

    private void loadTextures() {
        Truck.loadTextures();
    }
}
