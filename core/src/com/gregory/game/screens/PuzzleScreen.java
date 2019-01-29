package com.gregory.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.gregory.game.MainApplication;
import com.gregory.game.RecordManager;
import com.gregory.game.Utils.Background;
import com.gregory.game.Utils.CarState;
import com.gregory.game.ui.MenuButton;
import com.gregory.game.ui.MoveCounter;
import com.gregory.game.ui.PuzzleArrow;
import com.gregory.game.ui.PuzzleCounter;
import com.gregory.game.ui.RestartButton;
import com.gregory.game.ui.UndoButton;
import com.gregory.game.ui.WinDialog;
import com.gregory.game.objects.Car;
import com.gregory.game.objects.PlayerCar;
import com.gregory.game.objects.Truck;
import java.util.ArrayList;

import static com.gregory.game.Utils.Screens.MENU;

public class PuzzleScreen extends ScreenAdapter implements Screen {
    public static final int BLOCKSIZE = 170;
    public static final int offsetX = 30;
    public static final int offsetY = Gdx.graphics.getHeight() - 1430;
    public static final int GRID_WIDTH = 6;
    public static final int GRID_HEIGHT = 6;

    MainApplication parent;
    Stage stage;
    public static Group cars;
    public static ArrayList<ArrayList<CarState>> previousStates;
    PlayerCar playerCar;
    MoveCounter moveCounter;
    PuzzleCounter puzzleCounter;

    public PuzzleScreen(MainApplication mainApplication) {
        parent = mainApplication;
        puzzleCounting();
    }

    @Override
    public void show() {
        init();
    }

    private void puzzleCounting() {
        FileHandle fileHandle = Gdx.files.internal("puzzles");
        FileHandle[] list = fileHandle.list();
        puzzleCounter = new PuzzleCounter(list.length);
    }

    private void init() {
        stage = new Stage();
        cars = new Group();
        previousStates = new ArrayList<ArrayList<CarState>>();

        moveCounter = new MoveCounter(RecordManager.getRecordForPuzzle(puzzleCounter.getCounter()),
                RecordManager.getMinimumForPuzzle(puzzleCounter.getCounter()));

        Gdx.input.setInputProcessor(stage);
        stage.addActor(new Background("background.png"));
        loadPuzzle(puzzleCounter.getCounter());
        stage.addActor(cars);
        // Setup UI
        MenuButton menuButton = new MenuButton(Gdx.graphics.getWidth() / 4, 200, (float)1/5);
        UndoButton undoButton = new UndoButton(this, 2 * Gdx.graphics.getWidth() / 4, 200, (float)1/5);

        menuButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                parent.changeScreen(MENU);
                return true;
            }
        });
        stage.addActor(menuButton);
        stage.addActor(undoButton);
        stage.addActor(new RestartButton(this,3 * Gdx.graphics.getWidth() / 4, 200, (float)1/5));
        stage.addActor(moveCounter);
        stage.addActor(puzzleCounter);
        showPuzzleChooser();
    }

    private void loadPuzzle(int puzzleNumber) {
        JsonReader jsonReader = new JsonReader();
        JsonValue jsonValue = jsonReader.parse(Gdx.files.internal("puzzles/puzzle" + ((Integer) puzzleNumber).toString() + ".json"));
        JsonValue playerCarValue = jsonValue.get(0);
        int xCar = playerCarValue.getInt("x") * BLOCKSIZE;
        int yCar = playerCarValue.getInt("y") * BLOCKSIZE;
        playerCar = new PlayerCar(this, xCar + offsetX, yCar + offsetY);
        cars.addActor(playerCar);

        for (int i = 1; i < jsonValue.size; i++) {
            JsonValue truck = jsonValue.get(i);
            int x = truck.getInt("x") * BLOCKSIZE;
            int y = truck.getInt("y") * BLOCKSIZE;
            Car.ORIENTATION orientation;
            if (truck.getString("orientation").equals("right"))
                orientation = Car.ORIENTATION.RIGHT;
            else
                orientation = Car.ORIENTATION.UP;
            cars.addActor(new Truck(this, x + offsetX, y + offsetY, orientation, truck.getInt("size")));
        }
    }

    private void showPuzzleChooser() {
        PuzzleArrow leftArrow = new PuzzleArrow(this, false, offsetX + 10 , Gdx.graphics.getHeight() - 300, puzzleCounter.getCounter());
        PuzzleArrow rightArrow = new PuzzleArrow(this, true, offsetX - 30 + (3*BLOCKSIZE) , Gdx.graphics.getHeight() - 300, puzzleCounter.getCounter());
        stage.addActor(leftArrow);
        stage.addActor(rightArrow);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    // Button Handlers
    public void undo() {
        if (!previousStates.isEmpty()) {
            cars.clearChildren();
            for (CarState state : previousStates.remove(previousStates.size() - 1)) {
                Car car = state.car;
                car.setPosition(state.position.x, state.position.y);
                cars.addActor(car);
            }

            decrementMove();
        }
    }

    public void restart() {
        init();
    }

    public void addMove() {
        moveCounter.addMove();
    }

    public void decrementMove() {
        moveCounter.decrementMove();
    }


    public void onWin() {
        stage.addActor(new WinDialog(this, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/2));
        RecordManager.saveRecordForPuzzle(puzzleCounter.getCounter(),moveCounter.getMoves());
        moveCounter.setRecord(RecordManager.getRecordForPuzzle(puzzleCounter.getCounter()));
        puzzleCounter.increment();
    }

    public void grabInput() {
        Gdx.input.setInputProcessor(stage);
    }

    public void changePuzzle(boolean isRight) {
        if (puzzleCounter.getCounter() == 3 && isRight)
            return;
        if (isRight) puzzleCounter.increment();
        else puzzleCounter.decrement();
        init();
    }
}
