package com.darsh.marsrover.service;

import com.darsh.marsrover.model.Mars;
import com.darsh.marsrover.model.Plateau;
import com.darsh.marsrover.model.Rover;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MarsServiceTest {

    @Autowired
    MarsService marsService;

    @Test
    void populateMarsWithRovers_EmptyMap_AssertsNoRoversNeeded() {
        Mars mars = new Mars();
        Map<String, String> noRovers = new HashMap<>();

        marsService.populateMarsWithRovers(mars, noRovers);

        final int expectedSize = 0;
        int actual = mars.getRovers().size();

        assertEquals(expectedSize, actual);
    }

    @Test
    void populateMarsWithRovers_AddRover_AssertsNotEmpty() {
        Mars mars = new Mars();
        mars.setPlateau(new Plateau(0, 0));
        Map<String, String> rovers = new HashMap<>();

        rovers.put("rover[0][posX]", "0");
        rovers.put("rover[0][posY]", "0");
        rovers.put("rover[0][direction]", "N");

        marsService.populateMarsWithRovers(mars, rovers);

        final int expectedSize = 1;
        int actual = mars.getRovers().size();

        assertEquals(expectedSize, actual);
    }

    @Test
    void populateMarsWithRovers_AddedAllRovers_AssertEmptyMap() {
        Mars mars = new Mars();
        mars.setPlateau(new Plateau(0, 0));
        Map<String, String> rovers = new HashMap<>();

        rovers.put("rover[0][posX]", "0");
        rovers.put("rover[0][posY]", "0");
        rovers.put("rover[0][direction]", "N");

        marsService.populateMarsWithRovers(mars, rovers);

        final int expectedSize = 0;
        int actual = rovers.size();

        assertEquals(expectedSize, actual);
    }

    @Test
    void populateMarsWithRovers_NegativeX_AssertNotNegativeX() {
        Mars mars = new Mars();
        mars.setPlateau(new Plateau(0, 0));
        Map<String, String> rovers = new HashMap<>();

        rovers.put("rover[0][posX]", "-5");
        rovers.put("rover[0][posY]", "-5");
        rovers.put("rover[0][direction]", "N");

        marsService.populateMarsWithRovers(mars, rovers);
        final int expected = 0;
        int actual = mars.getRovers().get(0).getPosX();

        assertTrue(actual >= 0);
    }

    @Test
    void populateMarsWithRovers_NegativeY_AssertNotNegativeY() {
        Mars mars = new Mars();
        mars.setPlateau(new Plateau(0, 0));
        Map<String, String> rovers = new HashMap<>();
        final int expected = 0;

        rovers.put("rover[0][posX]", "-5");
        rovers.put("rover[0][posY]", "-5");
        rovers.put("rover[0][direction]", "N");

        marsService.populateMarsWithRovers(mars, rovers);
        int actual = mars.getRovers().get(0).getPosY();

        assertTrue(actual >= 0);
    }

    @Test
    void populateMarsWithRovers_XMoreThanPlateau_AssertXCannotBeMoreThanPlateau() {
        Mars mars = new Mars();
        Map<String, String> rovers = new HashMap<>();

        mars.setPlateau(new Plateau(5, 5));
        rovers.put("rover[0][posX]", "9");
        rovers.put("rover[0][posY]", "9");
        rovers.put("rover[0][direction]", "N");

        marsService.populateMarsWithRovers(mars, rovers);
        final int expected = 5;
        int actual = mars.getRovers().get(0).getPosX();

        assertTrue(actual <= expected);
    }

    @Test
    void populateMarsWithRovers_YMoreThanPlateau_AssertYCannotBeMoreThanPlateau() {
        Mars mars = new Mars();
        Map<String, String> rovers = new HashMap<>();

        mars.setPlateau(new Plateau(5, 5));
        rovers.put("rover[0][posX]", "9");
        rovers.put("rover[0][posY]", "9");
        rovers.put("rover[0][direction]", "N");

        marsService.populateMarsWithRovers(mars, rovers);

        final int expected = 5;
        int actual = mars.getRovers().get(0).getPosY();

        assertTrue(actual <= expected);
    }

    @Test
    void populateMarsWithRovers_AssertThatRoversCanSetDirection() {
        Mars mars = new Mars();
        Map<String, String> rovers = new HashMap<>();

        mars.setPlateau(new Plateau(10, 10));
        rovers.put("rover[0][posX]", "9");
        rovers.put("rover[0][posY]", "9");
        rovers.put("rover[0][direction]", "S");

        marsService.populateMarsWithRovers(mars, rovers);

        final char expected = 'S';
        char actual = mars.getRovers().get(0).getDirection();

        assertEquals(expected, actual);
    }


    @Test
    void populateMarsWithRovers_AssertThatRoversCanHaveNoInstructions() {
        Mars mars = new Mars();
        Map<String, String> rovers = new HashMap<>();

        mars.setPlateau(new Plateau(10, 10));
        rovers.put("rover[0][posX]", "9");
        rovers.put("rover[0][posY]", "9");
        rovers.put("rover[0][direction]", "N");

        marsService.populateMarsWithRovers(mars, rovers);

        final String expected = "";
        String actual = mars.getRovers().get(0).getInstructions();

        assertEquals(expected, actual);
    }


}
