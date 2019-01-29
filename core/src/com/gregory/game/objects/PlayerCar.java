package com.gregory.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gregory.game.screens.PuzzleScreen;

import static com.gregory.game.screens.PuzzleScreen.BLOCKSIZE;
import static com.gregory.game.screens.PuzzleScreen.offsetX;
import static com.gregory.game.screens.PuzzleScreen.offsetY;

public class PlayerCar extends Car {


    public PlayerCar(final PuzzleScreen puzzleScreen, int x, int y) {
        super(puzzleScreen, new Texture("car.png"), x, y, ORIENTATION.RIGHT);

        addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (getX() == offsetX + 4 * BLOCKSIZE &&
                        getY() == offsetY + 3 * BLOCKSIZE) {
                    puzzleScreen.onWin();
                }
            }
        });
    }

}
