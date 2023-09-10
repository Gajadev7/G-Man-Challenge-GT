package com.example.geektrust;

import com.example.geektrust.DirectionEnum.Direction;
public class Coordinate {
    private int x;
    private int y;

    Direction direction;

    public Coordinate(){
        this.x = -1;
        this.y = -1;
        this.direction = null;
    }
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Coordinate(int x, int y, Direction direction){
        this(x,y);
        this.direction = direction;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
