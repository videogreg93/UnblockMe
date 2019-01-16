package com.gregory.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gregory.game.screens.PuzzleScreen;

public class MenuButton extends Actor {
    Sprite sprite;
    Color defaultColor;

    public MenuButton(int x, int y) {
        sprite = new Sprite(new Texture("menuButton.png"));
        sprite.setPosition(x, y);
        sprite.setBounds(x, y, sprite.getWidth(), sprite.getHeight());
        this.setWidth(sprite.getWidth());
        this.setHeight(sprite.getHeight());
        this.setBounds(x,y,getWidth(),getHeight());
        this.setPosition(x,y);
        sprite.setOriginCenter();

        defaultColor = sprite.getColor();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setColor(defaultColor);
        sprite.draw(batch);
    }
}
