package com.gregory.game.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MoveCounter extends Actor {
    BitmapFont font = new BitmapFont();
    int moves = 0;

    public MoveCounter() {
        font.getData().setScale(5f);
    }

    public void addMove() {
        moves++;
    }

    public void decrementMove() {
        moves--;
    }

    public void resetMoves() {
        moves = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, "Moves", 750, 1630);
        font.draw(batch, "" + moves, 800, 1550);

    }
}
