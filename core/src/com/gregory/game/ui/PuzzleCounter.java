package com.gregory.game.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Displays the move counter and the record counter
 */
public class PuzzleCounter extends Actor {
    BitmapFont font = new BitmapFont();

    int puzzle;
    int max;

    public PuzzleCounter(int max) {
        font.getData().setScale(5f);
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
        font.draw(batch, "Puzzle ", 260, 1630);
        font.draw(batch, numberString(), 350, 1550);

    }
}
