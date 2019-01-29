package com.gregory.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MenuButton extends Actor {
    Sprite sprite;
    Color defaultColor;

    public MenuButton(int x, int y, float ratio) {

        sprite = new Sprite(new Texture("menuButton.png"));

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

        defaultColor = sprite.getColor();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setColor(defaultColor);
        sprite.draw(batch);
    }
}
