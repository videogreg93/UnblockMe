package com.gregory.game.Utils;

public class Position {
    public float x;
    public float y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        Position other = (Position) o;
        return (x == other.x && y == other.y);
    }
}
