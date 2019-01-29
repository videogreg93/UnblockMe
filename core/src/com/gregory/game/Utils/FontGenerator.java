package com.gregory.game.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontGenerator {

    public BitmapFont font;
    private FontGenerator(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Roboto-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 100;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789./-";

        font = generator.generateFont(parameter);
        generator.dispose();
    }

    private static FontGenerator INSTANCE = null;
    public static FontGenerator getInstance()
    {
        if (INSTANCE == null)
        {   INSTANCE = new FontGenerator();
        }
        return INSTANCE;
    }
}
