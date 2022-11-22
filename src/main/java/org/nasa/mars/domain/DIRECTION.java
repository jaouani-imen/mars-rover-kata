package org.nasa.mars.domain;

import java.util.Optional;
import java.util.stream.Stream;

public enum DIRECTION {
    NORTH('N'),
    EAST('E'),
    WEST('W'),
    SOUTH('S');

    private char direction;

    DIRECTION(char direction) {
        this.direction = direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public char getDirection() {
        return direction;
    }

    public static Optional<DIRECTION> getDirectionByCode(char direction) {
        return Stream.of(values()).filter(d -> d.direction == direction).findFirst();
    }

}
