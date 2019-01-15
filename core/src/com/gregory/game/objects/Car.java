package com.gregory.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.gregory.game.Utils.CarState;
import com.gregory.game.Utils.Position;
import com.gregory.game.screens.PuzzleScreen;

import java.util.ArrayList;

import static com.gregory.game.screens.PuzzleScreen.BLOCKSIZE;
import static com.gregory.game.screens.PuzzleScreen.cars;
import static com.gregory.game.screens.PuzzleScreen.offsetX;
import static com.gregory.game.screens.PuzzleScreen.offsetY;
import static com.gregory.game.screens.PuzzleScreen.previousStates;

public class Car extends Actor {
    Sprite sprite;
    ORIENTATION orientation;
    Position previousPosition;
    PuzzleScreen puzzleScreen;

    public Car(PuzzleScreen puzzleScreen, Texture texture, int x, int y) {
        this(puzzleScreen, texture, x, y, ORIENTATION.RIGHT);
    }

    public Car(final PuzzleScreen puzzleScreen, Texture texture, int x, int y, final ORIENTATION orientation) {
        this.puzzleScreen = puzzleScreen;
        sprite = new Sprite(texture);
        sprite.setPosition(x, y);
        this.orientation = orientation;

        sprite.setBounds(x, y, sprite.getWidth(), sprite.getHeight());
        this.setPosition(x, y);
        previousPosition = new Position(x, y);

        this.setWidth(sprite.getWidth());
        this.setHeight(sprite.getHeight());
        sprite.setOriginCenter();

        this.addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Player", "Player Clicked");
                previousPosition.x = getX();
                previousPosition.y = getY();
                // Get new state
                ArrayList<CarState> carstates = new ArrayList<CarState>();
                for (Actor actor : cars.getChildren()) {
                    Car car = (Car) actor;
                    CarState carState = new CarState(car, new Position(car.getX(), car.getY()));
                    carstates.add(carState);
                }
                previousStates.add(carstates);
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                //todo make this less janky
                x -= getWidth() / 2;
                y -= getHeight() / 2;
                // clamp values to avoid passing over other cars
                x = Math.min(BLOCKSIZE, x);
                y = Math.min(BLOCKSIZE, y);
                if (orientation == ORIENTATION.RIGHT) {
                    moveBy(x, 0);
                    if (outOfBounds() || isColliding())
                        moveBy(-x, 0);
                } else {
                    moveBy(0, y);
                    if (outOfBounds() || isColliding())
                        moveBy(0, -y);
                }
                Gdx.app.log("GregDrag", "" + x);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // TODO make this round to nearest block instead of rounding always down
                setX((Math.round((getX() - offsetX) / BLOCKSIZE) * BLOCKSIZE) + offsetX);
                setY((Math.round((getY() - offsetY) / BLOCKSIZE) * BLOCKSIZE) + offsetY);
                Position newPosition = new Position(getX(), getY());
                // TODO compare with old position to see if it counts as a move
                if (previousPosition.equals(newPosition)) {
                    previousStates.remove(previousStates.size() - 1);
                } else {
                    puzzleScreen.addMove();
                }
                previousPosition = newPosition;
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }

    private boolean outOfBounds() {
        // TODO Account for size
        return (getX() > offsetX + 4 * BLOCKSIZE || getY() > offsetY + 4 * BLOCKSIZE
                || getX() < offsetX || getY() < offsetY);
    }

    /**
     * @return true if there is a collision, false otherwise
     */
    private boolean isColliding() {
        Array<Actor> actors = cars.getChildren();
        for (Actor actor : actors) {
            try {
                Car other = (Car) actor;
                if (!other.equals(this) && other.sprite.getBoundingRectangle().overlaps(this.sprite.getBoundingRectangle())) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void moveBy(float x, float y) {
        super.moveBy(x, y);
        sprite.translate(x, y);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        sprite.setPosition(x, y);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        sprite.setX(x);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        sprite.setY(y);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return sprite.getBoundingRectangle().equals(car.sprite.getBoundingRectangle());
    }

    @Override
    public int hashCode() {
        return sprite.hashCode();
    }

    public enum ORIENTATION {
        UP,
        RIGHT
    }
}
