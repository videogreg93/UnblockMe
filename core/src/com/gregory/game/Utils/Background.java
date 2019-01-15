package com.gregory.game.Utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background extends Actor {
    Texture texture;

    public Background(String filename) {
        texture = new Texture(filename);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture,0,0);
    }
}
