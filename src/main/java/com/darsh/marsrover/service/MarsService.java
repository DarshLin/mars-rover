package com.darsh.marsrover.service;

import com.darsh.marsrover.model.Mars;
import com.darsh.marsrover.model.Rover;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MarsService {

    public void populateMarsWithRovers(Mars mars, Map<String, String> rovers) {
        List<Rover> roverSquad = new ArrayList<>();
        int i = 0;

        rovers.remove("submit");

        while (!rovers.isEmpty()) {
            //Get all attribute keys
            String posX = "rover[" + i + "][posX]";
            String posY = "rover[" + i + "][posY]";
            String direction = "rover[" + i + "][direction]";
            String instructions = "rover[" + i + "][instructions]";

            //create new rover to put into squad
            roverSquad.add(new Rover(
                    Integer.parseInt(rovers.get(posX)) < mars.getPlateau().getSizeX() ? Integer.parseInt(rovers.get(posX)) : mars.getPlateau().getSizeX(), //make sure positions are not away from the grid
                    Integer.parseInt(rovers.get(posY)) < mars.getPlateau().getSizeY() ? Integer.parseInt(rovers.get(posY)) : mars.getPlateau().getSizeY(),
                    rovers.get(direction).charAt(0),
                    rovers.get(instructions) == null ? "" : rovers.get(instructions)
            ));

            checkForNegativeCoordinates(roverSquad.get(roverSquad.size() - 1));

            //delete from map when done
            rovers.remove(posX);
            rovers.remove(posY);
            rovers.remove(direction);
            rovers.remove(instructions);
            i++;
        }
        mars.setRovers(roverSquad);
    }

    private void checkForNegativeCoordinates(Rover rover) {
        if (rover.getPosX() < 0) {
            rover.setPosX(0);
        }
        if (rover.getPosY() < 0) {
            rover.setPosY(0);
        }

    }
}
