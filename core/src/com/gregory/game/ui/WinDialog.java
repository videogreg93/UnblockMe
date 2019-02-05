package com.gregory.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.gregory.game.screens.PuzzleScreen;

public class WinDialog extends Actor {
    Sprite sprite;
    PuzzleScreen puzzleScreen;
    private float alpha;
    private float elapsedTime;
    private float displayTime;
    private float transitionTime;

    public WinDialog(PuzzleScreen puzzleScreen, int x, int y, float ratio) {
        alpha = 1;
        elapsedTime = 0;
        displayTime = 3;
        transitionTime = 1;

        this.puzzleScreen = puzzleScreen;

        sprite = new Sprite(new Texture("winDialog.png"));
        float width = Gdx.graphics.getWidth() * ratio;
        float height = width * sprite.getHeight() / sprite.getWidth();
        float posX = x - width / 2;
        float posY = y - height / 2;

        sprite.setPosition(posX, posY);
        sprite.setBounds(posX,posY, width, height);
        this.setWidth(width);
        this.setHeight(height);
        this.setBounds(posX,posY,width,height);
        this.setPosition(posX,posY);
        sprite.setOriginCenter();
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(elapsedTime >= displayTime && elapsedTime < displayTime + transitionTime) {
            alpha = (transitionTime - (elapsedTime - displayTime)) / transitionTime;
            sprite.setAlpha(alpha);
        }
        else if(elapsedTime >= displayTime + transitionTime) {
            this.remove();
            puzzleScreen.grabInput();
            puzzleScreen.changePuzzle(true);
        }

        elapsedTime += Gdx.graphics.getDeltaTime();

        sprite.draw(batch);
    }
}
