package com.darsh.marsrover.service;

import com.darsh.marsrover.model.Mars;
import com.darsh.marsrover.model.Plateau;
import org.springframework.stereotype.Service;

@Service
public class PlateauService {

    public void createPlateauOnMars(Mars mars, Integer x, Integer y) {
        //Check that x and y aren't negative
        x = x < 0 ? 0 : x;
        y = y < 0 ? 0 : y;

        Plateau plateau = new Plateau(x,y);
        mars.setPlateau(plateau);
    }
}
