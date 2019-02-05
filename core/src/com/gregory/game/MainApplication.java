package com.gregory.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.gregory.game.Utils.FontGenerator;
import com.gregory.game.Utils.Screens;
import com.gregory.game.objects.Truck;
import com.gregory.game.screens.AboutScreen;
import com.gregory.game.screens.MenuScreen;
import com.gregory.game.screens.PuzzleScreen;


public class MainApplication extends Game {

    private BitmapFont font;

    @Override
    public void create() {
        this.font = FontGenerator.getFont();
        loadTextures();
        initManagers();
        setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        getScreen().render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        font.dispose();
    }

    private void loadTextures() {
        Truck.loadTextures();
    }

    private void initManagers() {
        RecordManager.init();
    }


    public void changeScreen(Screens screen){
        switch(screen){
            case MENU:
                this.setScreen(new MenuScreen(this));
                break;
            case ABOUT:
                this.setScreen(new AboutScreen(this));
                break;
            case APPLICATION:
                this.setScreen(new PuzzleScreen(this));
                break;
        }
    }

    public BitmapFont getFont()
    {
        return this.font;
    }
}
