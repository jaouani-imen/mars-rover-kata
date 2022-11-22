package org.nasa.mars.domain;

import lombok.Data;
import org.nasa.mars.exceptions.InvalidMoveException;

import java.util.List;

import static org.nasa.mars.domain.DIRECTION.*;

@Data
public class Rover {

    private Plateau plateau;
    private Coordinate coordinate;
    private DIRECTION direction;
    public static final String PREFIX_INVALID_MOVE_ERROR_MESSAGE = "The Rover cannot be out of bounds of the plateau: ";

    public Rover(Plateau plateau, Coordinate coordinate, DIRECTION direction) {
        this.plateau = plateau;
        this.coordinate = coordinate;
        this.direction = direction;
    }

    public String execute(List<INSTRUCTION> instructions) throws InvalidMoveException {
        for (INSTRUCTION instruction : instructions) {
            switch (instruction) {
                case LEFT:
                    spinToLeft();
                    break;
                case RIGHT:
                    spinToRight();
                    break;
                case MOVE:
                    moveForward();
                    break;
            }
        }
        return printPosition();
    }

    public void spinToRight() {
        switch (direction) {
            case NORTH:
                setDirection(EAST);
                break;
            case EAST:
                setDirection(SOUTH);
                break;
            case SOUTH:
                setDirection(WEST);
                break;
            case WEST:
                setDirection(NORTH);
                break;
        }
    }

    public void spinToLeft() {
        switch (direction) {
            case NORTH:
                setDirection(WEST);
                break;
            case EAST:
                setDirection(NORTH);
                break;
            case SOUTH:
                setDirection(EAST);
                break;
            case WEST:
                setDirection(SOUTH);
                break;
        }
    }

    private void moveForward() throws InvalidMoveException {
        switch (direction) {
            case NORTH: {
                if ((coordinate.getY() + 1) > plateau.getMaximalCoordinate().getY())
                    throw new InvalidMoveException( PREFIX_INVALID_MOVE_ERROR_MESSAGE + printPosition());
                coordinate.setY(coordinate.getY() + 1);
                break;
            }

            case EAST: {
                if ((coordinate.getX() + 1) > plateau.getMaximalCoordinate().getX())
                    throw new InvalidMoveException( PREFIX_INVALID_MOVE_ERROR_MESSAGE + printPosition());
                coordinate.setX(coordinate.getX() + 1);
                break;
            }

            case WEST: {
                if ((coordinate.getX() - 1) < plateau.getInitialCoordinate().getX())
                    throw new InvalidMoveException( PREFIX_INVALID_MOVE_ERROR_MESSAGE + printPosition());
                coordinate.setX(coordinate.getX() - 1);
                break;

            }

            case SOUTH: {
                if ((coordinate.getY() - 1) < plateau.getInitialCoordinate().getY())
                    throw new InvalidMoveException( PREFIX_INVALID_MOVE_ERROR_MESSAGE + printPosition());
                coordinate.setY(coordinate.getY() - 1);
                break;
            }

        }
    }

    public String printPosition() {
        StringBuilder positionBuilder = new StringBuilder();
        positionBuilder.append(coordinate.getX());
        positionBuilder.append(" ");
        positionBuilder.append(coordinate.getY());
        positionBuilder.append(" ");
        positionBuilder.append(direction.getDirection());
        return positionBuilder.toString();
    }


}
