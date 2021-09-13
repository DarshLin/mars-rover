package com.darsh.marsrover.service;

import com.darsh.marsrover.model.Mars;
import com.darsh.marsrover.model.Plateau;
import org.springframework.stereotype.Service;

@Service
public class PlateauService {

    /**
     * Creates and sets the plateau on mars
     * @param mars the thing plateau is made on
     * @param x horizontal axis
     * @param y vertical axis
     */
    public void createPlateauOnMars(Mars mars, Integer x, Integer y) {
        //Check that x and y aren't negative
        x = x < 0 ? 0 : x;
        y = y < 0 ? 0 : y;

        Plateau plateau = new Plateau(x,y);
        mars.setPlateau(plateau);
    }
}
