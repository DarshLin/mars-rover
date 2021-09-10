package com.darsh.marsrover.model;

import lombok.Data;

@Data
public class Rover {

    private Integer posX;

    private Integer posY;

    private Character direction;

    private String instructions;
}
