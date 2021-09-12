package com.darsh.marsrover.controller;

import com.darsh.marsrover.model.Mars;
import com.darsh.marsrover.model.Rover;
import com.darsh.marsrover.model.Squad;
import com.darsh.marsrover.service.PlateauService;
import com.darsh.marsrover.service.RoverService;
import com.darsh.marsrover.model.Plateau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class MarsRoverController {

    @Autowired
    RoverService roverService;

    @Autowired
    PlateauService plateauService;

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
    public String processRoverInstructions(@RequestBody Squad squad) {

        return "result";
    }
}
