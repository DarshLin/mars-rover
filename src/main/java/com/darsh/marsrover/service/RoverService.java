package com.darsh.marsrover.service;

import com.darsh.marsrover.model.Plateau;
import org.springframework.stereotype.Service;

@Service
public class RoverService {

    public void moveRover(Plateau plateau) {

//        coordinates.setPlateauSize("Plateau Size: " + coordinates.getPlateauSize());
//        coordinates.setStartPosition("Start: " + coordinates.getStartPosition());
//        coordinates.setDirections("Directions: " + coordinates.getDirections());
        plateau.setSizeX(plateau.getSizeX() + 1);
        plateau.setSizeY(plateau.getSizeY() + 1);
    }
}
