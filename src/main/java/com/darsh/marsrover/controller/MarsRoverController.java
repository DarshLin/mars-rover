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

    @GetMapping(path = "/index")
    public String getCoordinates() {
        return "/index";
    }

    @PostMapping(path = "/rover")
    public String submitCoordinates(@ModelAttribute Plateau plateau, Model model) {
        plateauService.createPlateauOnMars(mars, plateau.getSizeX(), plateau.getSizeY());
        model.addAttribute("plateau", plateau);

        return "rover";
    }


    @PostMapping(path = "/result")
    public String processRoverInstructions(@RequestParam MultiValueMap<String, String> squad) {

        Map<String,String> rovers = new HashMap<>();

        Iterator<String> it = squad.keySet().iterator();

        while(it.hasNext()) {
            String key = it.next();
            rovers.put(key, squad.getFirst(key));
        }

        marsService.populateMarsWithRovers(mars, rovers);


        roverService.moveRover(mars);

        return "result";
    }
}
