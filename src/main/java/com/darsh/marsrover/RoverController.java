package com.darsh.marsrover;

import com.darsh.marsrover.model.Coordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RoverController {

    @Autowired
    RoverService roverService;

    @GetMapping(path = "/rover")
    public String getCoordinates() {
        return "rover";
    }

    @PostMapping(path = "/move")
    public String submitCoordinates(@ModelAttribute Coordinates coordinates, Model model) {

        roverService.moveRover(coordinates);

        model.addAttribute("coordinates", coordinates);
        return "moved-rover";
    }
}
