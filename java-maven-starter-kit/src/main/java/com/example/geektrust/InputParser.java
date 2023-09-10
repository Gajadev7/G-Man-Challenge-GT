package com.example.geektrust;

import com.example.geektrust.CommandEnum.Command;
import com.example.geektrust.DirectionEnum.Direction;
public class InputParser {

    private Coordinate source = new Coordinate();
    private Coordinate destination = new Coordinate();
    private GMan gman;
    private boolean isSourceSet = false, isDestinationSet = false;

    public InputParser(GMan gman){
        this.gman = gman;
    }
    public void parseAndProcessInput(String line){
        String[] arguments = line.split("\\s+");
        Command command = parseCommand(arguments[0]);
        if(command.equals(Command.PRINT_POWER)){
            processPrintCommand();
        }
        else{
            setCoordinates(arguments);
        }
    }

    private Command parseCommand(String commandArg){
        Command command;
        try{
            command = Command.valueOf(commandArg);
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Illegal command : " + commandArg);
        }
        return command;
    }

    private void processPrintCommand(){
        if(isSourceSet && isDestinationSet){
            gman.printPower(source, destination);
        }
        else{
            throw new IllegalArgumentException("Please set the source and destination coordinates first");
        }
    }

    private void setCoordinates(String[] arguments){
        int[] coordinates = new int[2];
        for(int i=1; i<=2; i++){
            try {
                coordinates[i-1] = Integer.parseInt(arguments[i]);
            }
            catch (NumberFormatException e){
                throw new IllegalArgumentException("Invalid coordinates provided at index " + i + " : " + arguments[i]);
            }
        }
        withinBoundary(coordinates);
        if(Command.valueOf(arguments[0]).equals(Command.SOURCE)){
            Direction direction = parseDirection(arguments[3]);
            setSource(coordinates, direction);

        }
        else{
            setDestination(coordinates);
        }
    }

    private Direction parseDirection(String directionArg){
        Direction direction;
        try{
            direction = Direction.valueOf(directionArg);
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Illegal direction : " + directionArg);
        }
        return direction;
    }

    private void withinBoundary(int[] coordinates){
        if(!Constants.withinBoundary(coordinates[0], coordinates[1])){
            throw new IllegalArgumentException("Please provide index within the range of 0-6");
        }
    }

    private void setSource(int[] coordinates, Direction direction){
        source = new Coordinate(coordinates[0], coordinates[1], direction);
        isSourceSet = true;
    }

    private void setDestination(int[] coordinates){
        destination = new Coordinate(coordinates[0], coordinates[1]);
        isDestinationSet = true;
    }
}
