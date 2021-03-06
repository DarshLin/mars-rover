package com.darsh.marsrover.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rover {

    private Integer posX;

    private Integer posY;

    private Character direction;

    private String instructions;
}
