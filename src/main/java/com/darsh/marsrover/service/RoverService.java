package com.darsh.marsrover.service;

import com.darsh.marsrover.model.Mars;
import com.darsh.marsrover.model.Rover;
import org.springframework.stereotype.Service;

@Service
public class RoverService {

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
//            System.out.println("X: " + r.getPosX() + " Y: " + r.getPosY() + " Direction: " + r.getDirection());
        }
    }

    private int[] move(Rover rover, int boundaryX, int boundaryY) {

        switch (Character.toUpperCase(rover.getDirection())) {
            case 'N':
                if (!outOfBounds(boundaryY, rover.getPosY() + 1)) {
                    rover.setPosY(rover.getPosY() + 1);
                }
                break;
            case 'E':
                if (!outOfBounds(boundaryX, rover.getPosX() + 1)) {
                    rover.setPosX(rover.getPosX() + 1);
                }
                break;
            case 'S':
                if (!outOfBounds(boundaryY, rover.getPosY() - 1)) {
                    rover.setPosY(rover.getPosY() - 1);
                }
                break;
            case 'W':
                if (!outOfBounds(boundaryX, rover.getPosX() - 1)) {
                    rover.setPosX(rover.getPosX() - 1);
                }
                break;
            default:
                break;
        }

        return new int[]{rover.getPosX(), rover.getPosY()};
    }

    private char leftTurn(Rover rover) {

        switch (Character.toUpperCase(rover.getDirection())) {
            case 'N':
                rover.setDirection('W');
                break;
            case 'E':
                rover.setDirection('N');
                break;
            case 'S':
                rover.setDirection('E');
                break;
            case 'W':
                rover.setDirection('S');
                break;
            default:
                break;
        }
        return rover.getDirection();
    }

    private char rightTurn(Rover rover) {

        switch (Character.toUpperCase(rover.getDirection())) {
            case 'N':
                rover.setDirection('E');
                break;
            case 'E':
                rover.setDirection('S');
                break;
            case 'S':
                rover.setDirection('W');
                break;
            case 'W':
                rover.setDirection('N');
                break;
            default:
                break;
        }
        return rover.getDirection();
    }

    private boolean outOfBounds(int boundary, int coordinate) {
        return boundary < coordinate && coordinate >= 0;
    }
}
