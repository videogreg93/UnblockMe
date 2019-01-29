package com.gregory.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.gregory.game.Utils.FontGenerator;

/**
 * Displays the move counter and the record counter
 */
public class PuzzleCounter extends Actor {
    BitmapFont font;

    int puzzle;
    int max;

    public PuzzleCounter(int max) {
        font = FontGenerator.getInstance().font;
        font.getData().setScale(0.8f);
        this.puzzle = 1;
        this.max = max;
    }

    public void setPuzzle(int puzzle) {
        this.puzzle = puzzle;
    }

    public void increment() {
        if(puzzle == max) return;
        puzzle++;
    }

    public void decrement() {
        if (puzzle == 0) return;
        puzzle--;
    }

    public int getCounter() {
        return puzzle;
    }

    public String numberString() { return puzzle+""; }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, "Puzzle", 175 , Gdx.graphics.getHeight() - 175, Gdx.graphics.getWidth() / 3f, Align.center, true);
        font.draw(batch, numberString(), 175, Gdx.graphics.getHeight() - 275, Gdx.graphics.getWidth() / 3f, Align.center, true);
    }
}
