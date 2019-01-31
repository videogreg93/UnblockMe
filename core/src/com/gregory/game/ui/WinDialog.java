package com.gregory.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.gregory.game.screens.PuzzleScreen;

public class WinDialog extends Actor implements InputProcessor {
    Sprite background;
    PuzzleScreen puzzleScreen;
    private float t;
    private float elapsedTime;

    public WinDialog(PuzzleScreen puzzleScreen, int x, int y) {
        background = new Sprite(new Texture("winDialog.png"));
        background.setPosition(x,y);
        this.puzzleScreen = puzzleScreen;
        Gdx.input.setInputProcessor(this);
        t = 1;
        elapsedTime = 0;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        if(elapsedTime >= 3 && t > 0) {

            background.setAlpha(t);
        }
        else if (t <= 0){
            this.remove();
            t = 0;
            puzzleScreen.grabInput();
            puzzleScreen.changePuzzle(true);
        }
        t -= 0.01;

        background.draw(batch);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
