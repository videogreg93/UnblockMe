package com.gregory.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.gregory.game.Utils.Screens;
import com.gregory.game.objects.Truck;
import com.gregory.game.screens.AboutScreen;
import com.gregory.game.screens.MenuScreen;
import com.gregory.game.screens.PuzzleScreen;

public class MainApplication extends Game {

    private MenuScreen menuScreen;
    private PuzzleScreen puzzleScreen;
    private AboutScreen aboutScreen;

    @Override
    public void create() {
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
                if(menuScreen == null) menuScreen = new MenuScreen(this);
                this.setScreen(menuScreen);
                break;
            case ABOUT:
                if(aboutScreen == null) aboutScreen = new AboutScreen(this);
                this.setScreen(new AboutScreen(this));
                break;
            case APPLICATION:
                if(puzzleScreen == null) puzzleScreen = new PuzzleScreen(this);
                this.setScreen(puzzleScreen);
                break;
        }
    }
}
