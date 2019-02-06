package com.gregory.game.Utils;

import com.gregory.game.objects.Car;

/**
 * Saves the state of a car
 */
public class CarState {
    public Car car;
    public Position position;

    public CarState(Car c, Position p) {
        car = c;
        position = p;
    }
}
