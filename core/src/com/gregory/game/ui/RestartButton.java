package com.gregory.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gregory.game.screens.PuzzleScreen;

public class RestartButton extends Actor {
    Sprite sprite;
    PuzzleScreen puzzleScreen;
    Color defaultColor;

    public RestartButton(final PuzzleScreen puzzleScreen, int x, int y) {
        this.puzzleScreen = puzzleScreen;
        sprite = new Sprite(new Texture("restartButton.png"));
        sprite.setPosition(x, y);
        sprite.setBounds(x, y, sprite.getWidth(), sprite.getHeight());
        this.setWidth(sprite.getWidth());
        this.setHeight(sprite.getHeight());
        this.setBounds(x,y,getWidth(),getHeight());
        this.setPosition(x,y);
        sprite.setOriginCenter();

        defaultColor = sprite.getColor();

        this.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("RestartButton", "Clicked Restart Button");
                puzzleScreen.restart();
                return true;
            }
        });


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (PuzzleScreen.previousStates.isEmpty())
            sprite.setColor(Color.GRAY);
        else
            sprite.setColor(defaultColor);
        sprite.draw(batch);
    }

}
