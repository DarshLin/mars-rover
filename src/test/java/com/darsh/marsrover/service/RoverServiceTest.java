package com.darsh.marsrover.service;

import com.darsh.marsrover.model.Mars;
import com.darsh.marsrover.model.Plateau;
import com.darsh.marsrover.model.Rover;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoverServiceTest {

    @Autowired
    RoverService roverService;

    @Test
    void moveRover_NoInstructions_AssertsNoChange() {
        Mars mars = new Mars();
        Plateau plateau = new Plateau(5, 5);

        Rover rover = new Rover(3, 3, 'N', "");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final int expected = 3;
        int actualX = mars.getRovers().get(0).getPosX();
        int actualY = mars.getRovers().get(0).getPosY();

        assertEquals(expected, actualX);
        assertEquals(expected, actualY);
    }

    @Test
    void moveRover_NonsenseInstructions_AssertsNoChange() {
        Mars mars = new Mars();
        Plateau plateau = new Plateau(5, 5);

        Rover rover = new Rover(3, 3, 'N', "ABCDEFGHI:;;''  ```>>>>>>");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final int expectedX = 3;
        final int expectedY = 3;
        final char expectedDir = 'N';

        int actualX = mars.getRovers().get(0).getPosX();
        int actualY = mars.getRovers().get(0).getPosY();
        char actualDir = mars.getRovers().get(0).getDirection();

        assertEquals(expectedX, actualX);
        assertEquals(expectedY, actualY);
        assertEquals(expectedDir, actualDir);
    }

    @Test
    void moveRover_MovePastIntLimitOnX_AssertsMaxInt() {
        final int MAX = Integer.MAX_VALUE;
        Mars mars = new Mars();
        Plateau plateau = new Plateau(MAX, MAX);

        Rover rover = new Rover(MAX, MAX, 'N', "MMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final int expectedX = MAX;
        int actualX = mars.getRovers().get(0).getPosX();

        assertEquals(expectedX, actualX);
    }

    @Test
    void moveRover_MovePastIntLimitOnY_AssertsMaxInt() {
        final int MAX = Integer.MAX_VALUE;
        Mars mars = new Mars();
        Plateau plateau = new Plateau(MAX, MAX);

        Rover rover = new Rover(MAX, MAX, 'E', "MMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final int expectedY = MAX;
        int actualY = mars.getRovers().get(0).getPosY();

        assertEquals(expectedY, actualY);
    }


    @Test
    void moveRover_MoveN_AssertsYPlus1() {
        Mars mars = new Mars();
        Plateau plateau = new Plateau(0, 1);

        Rover rover = new Rover(0, 0, 'N', "M");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final int expected = 1;
        int actual = mars.getRovers().get(0).getPosY();

        assertEquals(expected, actual);
    }

    @Test
    void moveRover_CannotMoveNPastTop_AssertsPlateauY() {
        Mars mars = new Mars();
        Plateau plateau = new Plateau(0, 5);

        Rover rover = new Rover(0, 5, 'N', "M");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final int expected = 5;
        int actual = mars.getRovers().get(0).getPosY();

        assertEquals(expected, actual);
    }

    @Test
    void moveRover_MoveS_AssertsYMinus1() {
        Mars mars = new Mars();
        Plateau plateau = new Plateau(0, 1);

        Rover rover = new Rover(0, 1, 'S', "M");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final int expected = 0;
        int actual = mars.getRovers().get(0).getPosY();

        assertEquals(expected, actual);
    }

    @Test
    void moveRover_CannotMoveSPastBottom_AssertsYEquals0() {
        Mars mars = new Mars();
        Plateau plateau = new Plateau(0, 1);

        Rover rover = new Rover(0, 0, 'S', "M");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final int expected = 0;
        int actual = mars.getRovers().get(0).getPosY();

        assertEquals(expected, actual);
    }

    @Test
    void moveRover_MoveE_AssertsYPlus1() {
        Mars mars = new Mars();
        Plateau plateau = new Plateau(1, 1);

        Rover rover = new Rover(0, 0, 'E', "M");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final int expected = 1;
        int actual = mars.getRovers().get(0).getPosX();

        assertEquals(expected, actual);
    }

    @Test
    void moveRover_CannotMoveEPastRight_AssertsPlateauY() {
        Mars mars = new Mars();
        Plateau plateau = new Plateau(5, 0);

        Rover rover = new Rover(5, 0, 'E', "M");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final int expected = 5;
        int actual = mars.getRovers().get(0).getPosX();

        assertEquals(expected, actual);
    }

    @Test
    void moveRover_CannotMoveWPastLeft_AssertsXEquals0() {
        Mars mars = new Mars();
        Plateau plateau = new Plateau(1, 1);

        Rover rover = new Rover(0, 0, 'W', "M");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final int expected = 0;
        int actual = mars.getRovers().get(0).getPosX();

        assertEquals(expected, actual);
    }

    @Test
    void moveRover_MoveW_AssertsYMinus1() {
        Mars mars = new Mars();
        Plateau plateau = new Plateau(1, 1);

        Rover rover = new Rover(1, 0, 'W', "M");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final int expected = 0;
        int actual = mars.getRovers().get(0).getPosX();

        assertEquals(expected, actual);
    }

    @Test
    void moveRover_NRightTurn_AssertsE() {
        Mars mars = new Mars();
        Plateau plateau = new Plateau(0, 1);

        Rover rover = new Rover(0, 0, 'N', "R");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final char expected = 'E';
        int actual = mars.getRovers().get(0).getDirection();

        assertEquals(expected, actual);
    }

    @Test
    void moveRover_NLeftTurn_AssertsW() {
        Mars mars = new Mars();
        Plateau plateau = new Plateau(0, 1);

        Rover rover = new Rover(0, 0, 'N', "L");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final char expected = 'W';
        int actual = mars.getRovers().get(0).getDirection();

        assertEquals(expected, actual);
    }

    @Test
    void moveRover_ERightTurn_AssertsS() {
        Mars mars = new Mars();
        Plateau plateau = new Plateau(0, 1);

        Rover rover = new Rover(0, 0, 'E', "R");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final char expected = 'S';
        int actual = mars.getRovers().get(0).getDirection();

        assertEquals(expected, actual);
    }

    @Test
    void moveRover_ELeftTurn_AssertsN() {
        Mars mars = new Mars();
        Plateau plateau = new Plateau(0, 1);

        Rover rover = new Rover(0, 0, 'E', "L");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final char expected = 'N';
        int actual = mars.getRovers().get(0).getDirection();

        assertEquals(expected, actual);
    }

    @Test
    void moveRover_SRightTurn_AssertsW() {
        Mars mars = new Mars();
        Plateau plateau = new Plateau(0, 1);

        Rover rover = new Rover(0, 0, 'S', "R");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final char expected = 'W';
        int actual = mars.getRovers().get(0).getDirection();

        assertEquals(expected, actual);
    }

    @Test
    void moveRover_SRightTurn_AssertsE() {
        Mars mars = new Mars();
        Plateau plateau = new Plateau(0, 1);

        Rover rover = new Rover(0, 0, 'S', "L");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final char expected = 'E';
        int actual = mars.getRovers().get(0).getDirection();

        assertEquals(expected, actual);
    }

    @Test
    void moveRover_WRightTurn_AssertsN() {
        Mars mars = new Mars();
        Plateau plateau = new Plateau(0, 1);

        Rover rover = new Rover(0, 0, 'W', "R");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final char expected = 'N';
        int actual = mars.getRovers().get(0).getDirection();

        assertEquals(expected, actual);
    }

    @Test
    void moveRover_WRightTurn_AssertsS() {
        Mars mars = new Mars();
        Plateau plateau = new Plateau(0, 1);

        Rover rover = new Rover(0, 0, 'W', "L");
        List<Rover> rovers = new ArrayList<>();
        rovers.add(rover);

        mars.setPlateau(plateau);
        mars.setRovers(rovers);

        roverService.moveRover(mars);

        final char expected = 'S';
        int actual = mars.getRovers().get(0).getDirection();

        assertEquals(expected, actual);
    }
}
