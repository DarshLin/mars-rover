package com.darsh.marsrover.service;

import com.darsh.marsrover.model.Mars;
import com.darsh.marsrover.model.Rover;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MarsService {

    /**
     * Creates rovers that are then put into the mars static object
     * Initially takes away submit from the payload
     * Each iteration looks at a new set of blueprints for position x, y, direction, and instructions
     * Removes all corresponding blueprint until the blueprints map becomes empty meaning all rovers made
     *
     * @param mars the master object where everything is stored
     * @param blueprints a simple map with string representations of key and value from html post
     */
    public void populateMarsWithRovers(Mars mars, Map<String, String> blueprints) {
        List<Rover> roverSquad = new ArrayList<>();
        int i = 0;

        blueprints.remove("submit");
        while (!blueprints.isEmpty()) {
            String posX = "rover[" + i + "][posX]";
            String posY = "rover[" + i + "][posY]";
            String direction = "rover[" + i + "][direction]";
            String instructions = "rover[" + i + "][instructions]";

            //create new rover to put into squad
            roverSquad.add(new Rover(
                    Integer.parseInt(blueprints.get(posX)) < mars.getPlateau().getSizeX() ? Integer.parseInt(blueprints.get(posX)) : mars.getPlateau().getSizeX(), //make sure positions are not away from the grid
                    Integer.parseInt(blueprints.get(posY)) < mars.getPlateau().getSizeY() ? Integer.parseInt(blueprints.get(posY)) : mars.getPlateau().getSizeY(),
                    blueprints.get(direction).charAt(0),
                    blueprints.get(instructions) == null ? "" : blueprints.get(instructions)
            ));
            checkForNegativeCoordinates(roverSquad.get(roverSquad.size() - 1));

            removeRoverFromBlueprints(blueprints, posX, posY, direction, instructions);
            i++;
        }
        mars.setRovers(roverSquad);
    }

    /**
     * Checks that coordinates aren't negative.
     * Generally should not be the case as the frontend checks for it, but this is good redundancy.
     * @param rover the new rover created from loop
     */
    private void checkForNegativeCoordinates(Rover rover) {
        if (rover.getPosX() < 0) {
            rover.setPosX(0);
        }
        if (rover.getPosY() < 0) {
            rover.setPosY(0);
        }
    }

    /**
     * Deletes used blueprints from the blueprints map to reach an endpoint
     * @param blueprints map with all the needed things to make a rover
     * @param posX horizontal axis
     * @param posY vertical axis
     * @param direction cardinal direction
     * @param instructions instructions to execute
     */
    private void removeRoverFromBlueprints(Map<String, String> blueprints, String posX, String posY, String direction, String instructions) {
        blueprints.remove(posX);
        blueprints.remove(posY);
        blueprints.remove(direction);
        blueprints.remove(instructions);
    }
}
