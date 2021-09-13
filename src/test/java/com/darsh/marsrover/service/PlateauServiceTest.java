package com.darsh.marsrover.service;

import com.darsh.marsrover.model.Mars;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlateauServiceTest {

    @Autowired
    PlateauService plateauService;

    @Test
    void createPlateauOnMars_XEquals0_Assert0() {
        Mars mars = new Mars();
        plateauService.createPlateauOnMars(mars, 0, 0);

        final int expected = 0;
        int actual = mars.getPlateau().getSizeX();

        assertEquals(expected, actual);
    }

    @Test
    void createPlateauOnMars_YEquals0_Assert0() {
        Mars mars = new Mars();
        plateauService.createPlateauOnMars(mars, 0, 0);

        final int expected = 0;
        int actual = mars.getPlateau().getSizeY();

        assertEquals(expected, actual);
    }

    @Test
    void createPlateauOnMars_XNotNegative_Asserts0() {
        Mars mars = new Mars();

        plateauService.createPlateauOnMars(mars, -5, 1);

        int actual = mars.getPlateau().getSizeX();

        assertTrue(actual >= 0);
    }

    @Test
    void createPlateauOnMars_YNotNegative_Asserts0() {
        Mars mars = new Mars();
        plateauService.createPlateauOnMars(mars, 1, -5);

        int actual = mars.getPlateau().getSizeY();

        assertTrue(actual >= 0);
    }

    @Test
    void createPlateauOnMars_AssignX_Asserts3() {
        Mars mars = new Mars();
        plateauService.createPlateauOnMars(mars, 3, 0);

        final int expected = 3;
        int actual = mars.getPlateau().getSizeX();

        assertEquals(expected, actual);
    }

    @Test
    void createPlateauOnMars_AssignY_Asserts3() {
        Mars mars = new Mars();
        plateauService.createPlateauOnMars(mars, 0, 3);

        final int expected = 3;
        int actual = mars.getPlateau().getSizeY();

        assertEquals(expected, actual);
    }

    @Test
    void createPlateauOnMars_AssignXMaxInt_AssertsMaxInt() {
        Mars mars = new Mars();
        plateauService.createPlateauOnMars(mars, Integer.MAX_VALUE, 0);

        final int expected = Integer.MAX_VALUE;
        int actual = mars.getPlateau().getSizeX();

        assertEquals(expected, actual);
    }

    @Test
    void createPlateauOnMars_AssignYMaxInt_AssertsMaxInt() {
        Mars mars = new Mars();
        plateauService.createPlateauOnMars(mars, 0, Integer.MAX_VALUE);

        final int expected = Integer.MAX_VALUE;
        int actual = mars.getPlateau().getSizeY();

        assertEquals(expected, actual);
    }
}
