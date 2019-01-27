package com.gregory.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class RecordManager {
    private static Preferences records;
    private static Preferences minimums;

    private static final String RECORDS = "RECORDS";
    private static final String MINIMUM = "MINIMUM";

    public static void init() {
        records = Gdx.app.getPreferences(RECORDS);
        minimums = Gdx.app.getPreferences(MINIMUM);
        // Minimums for puzzles
        minimums.putInteger("1", 15);
        minimums.putInteger("2", 17);
        minimums.putInteger("3", 15);
        minimums.flush();
    }

    public static int getRecordForPuzzle(int puzzleNumber) {
        return records.getInteger(Integer.toString(puzzleNumber), 0);
    }

    public static void saveRecordForPuzzle(int puzzleNumber, int record) {
        int currentRecord = getRecordForPuzzle(puzzleNumber);
        if (currentRecord == 0 || record < currentRecord) {
            records.putInteger(Integer.toString(puzzleNumber), record);
            records.flush();
        }
    }

    public static int getMinimumForPuzzle(int puzzleNumber) {
        return minimums.getInteger(Integer.toString(puzzleNumber), 0);
    }

}
