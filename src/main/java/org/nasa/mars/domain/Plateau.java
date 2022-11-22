package org.nasa.mars.domain;

import lombok.Data;

@Data
public class Plateau {
    private Coordinate initialCoordinate;
    private Coordinate maximalCoordinate;

    public Plateau(int maxCoordinateX, int maxCoordinateY) {
        this.initialCoordinate = new Coordinate(0, 0);
        this.maximalCoordinate = new Coordinate(maxCoordinateX, maxCoordinateY);
    }

    public boolean outOfPlateau(Coordinate coordinate) {
        return coordinate.getX() >= 0
                && coordinate.getY() >= 0
                && coordinate.getX() <= this.getMaximalCoordinate().getX()
                && coordinate.getY() <= this.getMaximalCoordinate().getY();
    }
}
