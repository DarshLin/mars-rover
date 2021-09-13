package com.darsh.marsrover.controller;

import com.darsh.marsrover.model.Mars;
import com.darsh.marsrover.service.MarsService;
import com.darsh.marsrover.service.PlateauService;
import com.darsh.marsrover.service.RoverService;
import com.darsh.marsrover.model.Plateau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class MarsRoverController {

    @Autowired
    MarsService marsService;

    @Autowired
    PlateauService plateauService;

    @Autowired
    RoverService roverService;

    public static Mars mars = new Mars();

    /**
     * Creates the first page of the app
     * @return the first page
     */
    @GetMapping(path = "/index")
    public String getCoordinates() {
        return "/index";
    }

    /**
     * sends plateau information for rover page
     * @param plateau constructed plateau from plateau service
     * @param model sends model attributes of plateau to be usable in rover page
     * @return rover page
     */
    @PostMapping(path = "/rover")
    public String submitCoordinates(@ModelAttribute Plateau plateau, Model model) {
        plateauService.createPlateauOnMars(mars, plateau.getSizeX(), plateau.getSizeY());
        model.addAttribute("plateau", plateau);

        return "rover";
    }

    /**
     * Moves the rovers and sends results to result page
     * @param blueprints payload from html from post coming in from rover page
     * @param model sends final positions of rovers to result page
     * @return result page
     */
    @PostMapping(path = "/result")
    public String processRoverInstructions(@RequestParam MultiValueMap<String, String> blueprints, Model model) {

        Map<String,String> rovers = new HashMap<>();

        Iterator<String> it = blueprints.keySet().iterator();

        while(it.hasNext()) {
            String key = it.next();
            rovers.put(key, blueprints.getFirst(key));
        }

        marsService.populateMarsWithRovers(mars, rovers);
        roverService.moveRover(mars);
        model.addAttribute("rovers", mars.getRovers());

        return "result";
    }
}
