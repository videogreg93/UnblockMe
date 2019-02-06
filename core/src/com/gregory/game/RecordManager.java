package com.gregory.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class RecordManager {
    private static Preferences records;
    private static Preferences minimums;

    private static final String RECORDS = "RECORDS";
    private static final String MINIMUM = "MINIMUM";

    /**
     * Loads the records from the app storage and set the mininum possible scores
     */
    public static void init() {
        records = Gdx.app.getPreferences(RECORDS);
        minimums = Gdx.app.getPreferences(MINIMUM);
        // Minimums for puzzles
        minimums.putInteger("1", 15);
        minimums.putInteger("2", 17);
        minimums.putInteger("3", 15);
        minimums.flush();
    }

    /**
     * Gets the record for a puzzle
     * @param puzzleNumber
     * @return
     */
    public static int getRecordForPuzzle(int puzzleNumber) {
        return records.getInteger(Integer.toString(puzzleNumber), 0);
    }

    /**
     * Saves the record for a puzzle
     * @param puzzleNumber
     * @param record
     */
    public static void saveRecordForPuzzle(int puzzleNumber, int record) {
        int currentRecord = getRecordForPuzzle(puzzleNumber);
        if (currentRecord == 0 || record < currentRecord) {
            records.putInteger(Integer.toString(puzzleNumber), record);
            records.flush();
        }
    }

    /**
     * Gets the mininum possible moves count for a puzzle
     * @param puzzleNumber
     * @return
     */
    public static int getMinimumForPuzzle(int puzzleNumber) {
        return minimums.getInteger(Integer.toString(puzzleNumber), 0);
    }

}
