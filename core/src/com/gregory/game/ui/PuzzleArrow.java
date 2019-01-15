package com.gregory.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gregory.game.screens.PuzzleScreen;

public class PuzzleArrow extends Actor {
    PuzzleScreen puzzleScreen;
    boolean isRight;
    boolean isEnabled = true;
    Sprite sprite;

    public PuzzleArrow(final PuzzleScreen puzzleScreen, final boolean isRight, int x, int y, int puzzleNumber) {
        this.puzzleScreen = puzzleScreen;
        if (isRight)
            sprite = new Sprite(new Texture("arrowRight.png"));
        else
            sprite = new Sprite(new Texture("arrowLeft.png"));
        sprite.setPosition(x,y);
        sprite.setBounds(x, y, sprite.getWidth(), sprite.getHeight());
        this.setWidth(sprite.getWidth());
        this.setHeight(sprite.getHeight());
        this.setBounds(x,y,getWidth(),getHeight());
        this.setPosition(x,y);
        sprite.setOriginCenter();

        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (isEnabled) {
                    puzzleScreen.changePuzzle(isRight);
                    return true;
                }
                return false;
            }
        });

        if ((isRight && puzzleNumber == 3) || (!isRight && puzzleNumber == 1)) {
            isEnabled = false;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isEnabled)
            sprite.draw(batch);
    }




}
