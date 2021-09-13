package com.darsh.marsrover.service;

import com.darsh.marsrover.model.Mars;
import com.darsh.marsrover.model.Rover;
import org.springframework.stereotype.Service;

@Service
public class RoverService {

    private final char NORTH = 'N';
    private final char EAST = 'E';
    private final char SOUTH = 'S';
    private final char WEST = 'W';

    /**
     * Uses loop that only takes correct inputs for instructions (L,R,M) lower and upper
     * Ignores all other characters
     * @param mars has all the rovers with their instructions and positions
     */
    public void moveRover(Mars mars) {

        int boundaryX = mars.getPlateau().getSizeX();
        int boundaryY = mars.getPlateau().getSizeY();

        for (Rover r : mars.getRovers()) {
            //move, turn, and record where they end up
            for (char instruction : r.getInstructions().toCharArray()) {
                if (instruction == 'm' || instruction == 'M') {
                    move(r, boundaryX, boundaryY);
                } else if (instruction == 'l' || instruction == 'L') {
                    leftTurn(r);
                } else if (instruction == 'r' || instruction == 'R') {
                    rightTurn(r);
                }
            }
        }
    }

    /**
     * Moves based on direction using a switch case
     * @param rover
     * @param boundaryX furthest right
     * @param boundaryY furthest up
     */
    private void move(Rover rover, int boundaryX, int boundaryY) {

        switch (Character.toUpperCase(rover.getDirection())) {
            case NORTH:
                if (!outOfBounds(boundaryY, rover.getPosY() + 1)) {
                    rover.setPosY(rover.getPosY() + 1);
                }
                break;
            case EAST:
                if (!outOfBounds(boundaryX, rover.getPosX() + 1)) {
                    rover.setPosX(rover.getPosX() + 1);
                }
                break;
            case SOUTH:
                if (!outOfBounds(boundaryY, rover.getPosY() - 1)) {
                    rover.setPosY(rover.getPosY() - 1);
                }
                break;
            case WEST:
                if (!outOfBounds(boundaryX, rover.getPosX() - 1)) {
                    rover.setPosX(rover.getPosX() - 1);
                }
                break;
            default:
                break;
        }

    }

    /**
     * Turns cardinal direction counterclockwise
     * @param rover
     */
    private void leftTurn(Rover rover) {

        switch (Character.toUpperCase(rover.getDirection())) {
            case NORTH:
                rover.setDirection(WEST);
                break;
            case EAST:
                rover.setDirection(NORTH);
                break;
            case SOUTH:
                rover.setDirection(EAST);
                break;
            case WEST:
                rover.setDirection(SOUTH);
                break;
            default:
                break;
        }
    }

    /**
     * Turns cardinal direction clockwise
     * @param rover
     */
    private void rightTurn(Rover rover) {

        switch (Character.toUpperCase(rover.getDirection())) {
            case NORTH:
                rover.setDirection(EAST);
                break;
            case EAST:
                rover.setDirection(SOUTH);
                break;
            case SOUTH:
                rover.setDirection(WEST);
                break;
            case WEST:
                rover.setDirection(NORTH);
                break;
            default:
                break;
        }
    }

    /**
     * Makes sure rover is in bound
     * @param boundary most top and right rover can go
     * @param coordinate rovers coordinate after movement
     * @return true or false that it is out of bounds
     */
    private boolean outOfBounds(int boundary, int coordinate) {
        return boundary < coordinate || coordinate < 0;
    }
}
