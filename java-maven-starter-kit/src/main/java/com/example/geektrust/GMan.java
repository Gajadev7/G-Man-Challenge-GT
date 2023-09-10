package com.example.geektrust;

import java.util.*;

import com.example.geektrust.DirectionEnum.Direction;

public class GMan {
    private Map<Direction, Coordinate> directionMap = Constants.DIRECTION_COORDINATE_HASH_MAP;
    private Map<Direction, HashSet<Direction>> directionNeighborMap = Constants.DIRECTION_NEIGHBOR_HASHMAP;
    private int gridSize = Constants.GRID_SIZE;




    public void printPower(Coordinate source, Coordinate destination){
        System.out.println("POWER "+calculateShortPathPower(source, destination));
    }

    private int calculateShortPathPower(Coordinate source, Coordinate destination){

        //To keep track of visited grids
        boolean[][] visited = new boolean[gridSize+1][gridSize+1];

        //Queue for BFS operation
        Queue<Node> bfsQueue = new LinkedList<>();
        bfsQueue.offer(new Node(source, Constants.INITIAL_POWER));
        visited[source.getX()][source.getY()] = true;

        int maxPower = 0;

        while(!bfsQueue.isEmpty()){
            Node currentNode = bfsQueue.poll();
            for(Map.Entry<Direction, Coordinate> entry : directionMap.entrySet()){
                int nextStepX = currentNode.getCoordinate().getX() + entry.getValue().getX();
                int nextStepY = currentNode.getCoordinate().getY() + entry.getValue().getY();
                int currentPower = currentNode.getPower();

                if(!Constants.withinBoundary(nextStepX, nextStepY)){
                    continue;
                }
                if(visited[nextStepX][nextStepY]){
                    continue;
                }

                visited[nextStepX][nextStepY] = true;
                currentPower-=Constants.MOVE_ENERGY;
                //Reduce the power by 10, on every move within the grid


                Direction currentDirection = currentNode.getCoordinate().getDirection();
                Direction nextDirection = entry.getKey();

                currentPower = calculateTurnEnergy(currentPower, currentDirection, nextDirection);

                if(nextStepX == destination.getX() && nextStepY == destination.getY()){
                    maxPower = Math.max(currentPower,maxPower);
                }
                Coordinate nextStepCoordinate  = new Coordinate(nextStepX, nextStepY, entry.getKey());
                bfsQueue.add(new Node(nextStepCoordinate, currentPower));
            }

        }

        return maxPower;

    }

    private int calculateTurnEnergy(int currentPower, Direction nextDirection, Direction currentDirection ){
        if(!nextDirection.equals(currentDirection)){
            //If the G-man changing his direction, reduce the power by 5;
            currentPower-=Constants.TURN_ENERGY;

            if(!directionNeighborMap.get(currentDirection).contains(nextDirection)){
                //If the G-man is changing the direction by 180 degrees
                currentPower-=Constants.TURN_ENERGY;
            }
        }

        return currentPower;
    }


}
