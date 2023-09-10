package com.example.geektrust;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import com.example.geektrust.DirectionEnum.Direction;

public class Constants {

    public static final int GRID_SIZE = 6;
    public static final int INITIAL_POWER = 200;

    public static final int MOVE_ENERGY = 10;
    public static final int TURN_ENERGY = 5;

    public static boolean withinBoundary(int i, int j){
        return i >= 0 && i <= GRID_SIZE && j >= 0 && j <= GRID_SIZE;
    }
    public static final HashMap<Direction, Coordinate> DIRECTION_COORDINATE_HASH_MAP = initDirectionMap();
    //This map contains the four directions the G-man can take and the respective coordinate move

    public static final HashMap<Direction, HashSet<Direction>> DIRECTION_NEIGHBOR_HASHMAP = initDirectionNeighborMap();
    //This map contains the neighbors for each direction

    private static HashMap<Direction, Coordinate> initDirectionMap(){
        HashMap<Direction, Coordinate> map = new HashMap<>();
        map.put(Direction.N, new Coordinate(0, 1));
        map.put(Direction.E, new Coordinate(1, 0));
        map.put(Direction.W, new Coordinate(-1, 0));
        map.put(Direction.S, new Coordinate(0, -1));
        
        return map;
    }

    private static HashMap<Direction, HashSet<Direction>> initDirectionNeighborMap(){
        HashMap<Direction, HashSet<Direction>> map = new HashMap<>();
        map.put(Direction.N, new HashSet<>(Arrays.asList(Direction.E, Direction.W)));
        map.put(Direction.E, new HashSet<>(Arrays.asList(Direction.N, Direction.S)));
        map.put(Direction.W, new HashSet<>(Arrays.asList(Direction.N, Direction.S)));
        map.put(Direction.S, new HashSet<>(Arrays.asList(Direction.E, Direction.W)));

        return map;
    }
    
    
}
