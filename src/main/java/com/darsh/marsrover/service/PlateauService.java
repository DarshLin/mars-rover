package com.darsh.marsrover.service;

import com.darsh.marsrover.model.Mars;
import com.darsh.marsrover.model.Plateau;
import org.springframework.stereotype.Service;

@Service
public class PlateauService {

    public Plateau createPlateauOnMars(Mars mars, Integer x, Integer y) {
        Plateau plateau = new Plateau(x,y);
        mars.setPlateau(plateau);

        return mars.getPlateau();
    }
}
