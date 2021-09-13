package com.darsh.marsrover.service;

import com.darsh.marsrover.model.Mars;
import com.darsh.marsrover.model.Plateau;
import org.springframework.stereotype.Service;

@Service
public class PlateauService {

    final Integer MAX = Integer.MAX_VALUE;

    /**
     * Creates and sets the plateau on mars
     * @param mars the thing plateau is made on
     * @param x horizontal axis
     * @param y vertical axis
     */
    public void createPlateauOnMars(Mars mars, Integer x, Integer y) {
        x = checkNotNegative(x) ? x : 0;
        y = checkNotNegative(y) ? y : 0;

        x = checkNotLimit(x) ? x : MAX;
        y = checkNotLimit(y) ? y : MAX;

        Plateau plateau = new Plateau(x,y);
        mars.setPlateau(plateau);
    }

    /**
     * Checks to see if coordinate is under 0
     * @param coordinate x or y
     * @return true or false
     */
    private boolean checkNotNegative(int coordinate) {
        return coordinate >= 0;
    }

    /**
     * Makes sure coordinate doesn't go over the integer limit
     * @param coordinate x or y
     * @return true or false
     */
    private boolean checkNotLimit(int coordinate) {
        return coordinate <= MAX;
    }
}
