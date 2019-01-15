package com.gregory.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gregory.game.screens.PuzzleScreen;

public class WinDialog extends Actor implements InputProcessor {
    Sprite background;
    PuzzleScreen puzzleScreen;

    public WinDialog(PuzzleScreen puzzleScreen, int x, int y) {
        background = new Sprite(new Texture("winDialog.png"));
        background.setPosition(x,y);
        this.puzzleScreen = puzzleScreen;
        Gdx.input.setInputProcessor(this);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
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
        this.remove();
        puzzleScreen.grabInput();
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
