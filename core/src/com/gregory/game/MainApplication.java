package com.gregory.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.gregory.game.objects.Truck;
import com.gregory.game.screens.MenuScreen;
import com.gregory.game.screens.PuzzleScreen;

public class MainApplication extends Game {

    public final static int MENU = 0;
    public final static int APPLICATION = 1;
    public final static int ABOUT = 2;
    public final static int ENDGAME = 3;

    //private AboutScreen aboutScreen;
    private MenuScreen menuScreen;
    private PuzzleScreen puzzleScreen;
    //private EndScreen endScreen;

    @Override
    public void create() {
        loadTextures();
        //setScreen(new PuzzleScreen());
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


    public void changeScreen(int screen){
        switch(screen){
            case MENU:
                if(menuScreen == null) menuScreen = new MenuScreen(this);
                this.setScreen(menuScreen);
                break;
//            case ABOUT:
//                if(aboutScreen == null) aboutScreen = new AboutScreen();
//                this.setScreen(aboutScreen);
//                break;
            case APPLICATION:
                if(puzzleScreen == null) puzzleScreen = new PuzzleScreen();
                this.setScreen(puzzleScreen);
                break;
//            case ENDGAME:
//                if(endScreen == null) endScreen = new EndScreen();
//                this.setScreen(endScreen);
//                break;
        }
    }
}
