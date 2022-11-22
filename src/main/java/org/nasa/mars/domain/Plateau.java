package org.nasa.mars.domain;

import lombok.Data;

@Data
public class Plateau {

    private final int lowerLeftCoordinateX = 0;
    private final int lowerLeftCoordinateY = 0;
    private Coordinate lowerLeftCoordinate;
    private Coordinate upperRightCoordinate;

    public Plateau(int maxCoordinateX, int maxCoordinateY) {
        this.lowerLeftCoordinate = new Coordinate(lowerLeftCoordinateX, lowerLeftCoordinateY);
        this.upperRightCoordinate = new Coordinate(maxCoordinateX, maxCoordinateY);
    }

    public boolean outOfPlateau(Coordinate coordinate) {
        return coordinate.getX() < lowerLeftCoordinateX
                || coordinate.getY() < lowerLeftCoordinateY
                || coordinate.getX() > this.getUpperRightCoordinate().getX()
                || coordinate.getY() > this.getUpperRightCoordinate().getY();
    }
}
