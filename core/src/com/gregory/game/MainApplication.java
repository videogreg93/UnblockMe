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

/**
 * MainApplication is the entry point of the application.
 * It's the parent class of all the screens in the application.
 */
public class MainApplication extends Game {

    private BitmapFont font;

    /**
     * Loads the app
     */
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

    /**
     * Loads the truck textures
     */
    private void loadTextures() {
        Truck.loadTextures();
    }

    /**
     * Loads the scores and records
     */
    private void initManagers() {
        RecordManager.init();
    }


    /**
     * Loads the app screens
     * @param screen
     */
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

    /**
     * Font getter
     * @return
     */
    public BitmapFont getFont()
    {
        return this.font;
    }
}
