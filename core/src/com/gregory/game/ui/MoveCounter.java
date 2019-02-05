package com.gregory.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.gregory.game.Utils.FontGenerator;
import com.gregory.game.screens.PuzzleScreen;

/**
 * Displays the move counter and the record counter
 */
public class MoveCounter extends Actor {
    BitmapFont font;
    int moves = 0;
    int record = 0;
    int minimum = 0;
    private int rightOffset = 340;
    private int topOffset = 140;

    public MoveCounter(BitmapFont parentFont, int recordForPuzzle, int minimumForPuzzle) {
        this.font = parentFont;
        this.font.getData().setScale(0.75f);
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
        font.draw(batch, "Moves", Gdx.graphics.getWidth() - rightOffset, Gdx.graphics.getHeight() - topOffset);
        font.draw(batch, "" + moves, Gdx.graphics.getWidth() - rightOffset, Gdx.graphics.getHeight() - topOffset - font.getLineHeight(), 225, Align.center, true);
        font.draw(batch, recordString(), Gdx.graphics.getWidth() - rightOffset, Gdx.graphics.getHeight() - topOffset - 2*font.getLineHeight(), 225, Align.center, true);
    }
}
