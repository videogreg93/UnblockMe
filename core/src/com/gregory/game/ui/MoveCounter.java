package com.gregory.game.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Displays the move counter and the record counter
 */
public class MoveCounter extends Actor {
    BitmapFont font = new BitmapFont();
    int moves = 0;
    int record = 0;
    int minimum = 0;

    public MoveCounter(int recordForPuzzle, int minimumForPuzzle) {
        font.getData().setScale(5f);
        record = recordForPuzzle;
        minimum = minimumForPuzzle;
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

    public int getMoves() {
        return moves;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    private String recordString() {
        return record == 0 ? "--/"+minimum : (record + "/"+minimum);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, "Moves", 750, 1630);
        font.draw(batch, "" + moves, 800, 1550);
        font.draw(batch, recordString(), 750, 1480);

    }
}
