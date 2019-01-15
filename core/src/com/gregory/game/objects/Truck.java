package com.gregory.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.gregory.game.screens.PuzzleScreen;

public class Truck extends Car {

    public Truck(PuzzleScreen puzzleScreen, int x, int y, ORIENTATION orientation) {
        super(puzzleScreen, textures[orientation.ordinal()], x, y, orientation);
    }

    public Truck(PuzzleScreen puzzleScreen, int x, int y, ORIENTATION orientation, int length) {
        super(puzzleScreen, textures[orientation.ordinal() + (length-2)*2], x, y, orientation);
    }



    private static Texture[] textures;

    public static void loadTextures() {
        textures = new Texture[ORIENTATION.values().length + 2];
        textures[ORIENTATION.RIGHT.ordinal()] = new Texture("truck.png");
        textures[ORIENTATION.UP.ordinal()] = new Texture("truckUp.png");
        textures[ORIENTATION.RIGHT.ordinal()+2] = new Texture("truckBig.png");
        textures[ORIENTATION.UP.ordinal()+2] = new Texture("truckBigUp.png");
    }


}
