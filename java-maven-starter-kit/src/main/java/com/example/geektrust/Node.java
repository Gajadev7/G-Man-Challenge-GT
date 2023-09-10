package com.example.geektrust;

public class Node {
    Coordinate coordinate;
    int power;

    public Node(Coordinate coordinate, int power){
        this.coordinate = coordinate;
        this.power = power;
    }
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int getPower() {
        return power;
    }


}
