package org.nasa.mars;

import org.junit.Before;
import org.junit.Test;
import org.nasa.mars.domain.*;
import org.nasa.mars.exceptions.InvalidMoveException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class RoverTest {

    private Plateau plateau = new Plateau(5, 5);
    private DIRECTION direction = DIRECTION.NORTH;
    private Rover rover;
    private int xCoordinate = 2;
    private int yCoordinate = 2;


    @Before
    public void setUp() {
        Coordinate roverCoordinate = new Coordinate(xCoordinate, yCoordinate);
        rover = new Rover(plateau, roverCoordinate, direction);
    }

    @Test
    public void test_move_LEFT_WHERE_DIRECTION_IS_NORTH() {
        List<INSTRUCTION> instructions = new ArrayList<>();
        instructions.add(INSTRUCTION.LEFT);
        String outputPosition = rover.execute(instructions);
        assertEquals("2 2 W", outputPosition);
    }

    @Test
    public void test_move_LEFT_WHERE_DIRECTION_IS_EAST() {
        rover.setDirection(DIRECTION.EAST);
        List<INSTRUCTION> instructions = new ArrayList<>();
        instructions.add(INSTRUCTION.LEFT);
        String outputPosition = rover.execute(instructions);
        assertEquals("2 2 N", outputPosition);
    }

    @Test
    public void test_move_LEFT_WHERE_DIRECTION_IS_WEST() {
        rover.setDirection(DIRECTION.WEST);
        List<INSTRUCTION> instructions = new ArrayList<>();
        instructions.add(INSTRUCTION.LEFT);
        String outputPosition = rover.execute(instructions);
        assertEquals("2 2 S", outputPosition);
    }

    @Test
    public void test_move_LEFT_WHERE_DIRECTION_IS_SOUTH() {
        rover.setDirection(DIRECTION.SOUTH);
        List<INSTRUCTION> instructions = new ArrayList<>();
        instructions.add(INSTRUCTION.LEFT);
        String outputPosition = rover.execute(instructions);
        assertEquals("2 2 E", outputPosition);
    }

    @Test
    public void test_move_RIGHT_WHERE_DIRECTION_IS_NORTH() {
        List<INSTRUCTION> instructions = new ArrayList<>();
        instructions.add(INSTRUCTION.RIGHT);
        String outputPosition = rover.execute(instructions);
        assertEquals("2 2 E", outputPosition);
    }

    @Test
    public void test_move_RIGHT_WHERE_DIRECTION_IS_EAST() {
        rover.setDirection(DIRECTION.EAST);
        List<INSTRUCTION> instructions = new ArrayList<>();
        instructions.add(INSTRUCTION.RIGHT);
        String outputPosition = rover.execute(instructions);
        assertEquals("2 2 S", outputPosition);
    }

    @Test
    public void test_move_RIGHT_WHERE_DIRECTION_IS_WEST() {
        rover.setDirection(DIRECTION.WEST);
        List<INSTRUCTION> instructions = new ArrayList<>();
        instructions.add(INSTRUCTION.RIGHT);
        String outputPosition = rover.execute(instructions);
        assertEquals("2 2 N", outputPosition);
    }

    @Test
    public void test_move_RIGHT_WHERE_DIRECTION_IS_SOUTH() {
        rover.setDirection(DIRECTION.SOUTH);
        List<INSTRUCTION> instructions = new ArrayList<>();
        instructions.add(INSTRUCTION.RIGHT);
        String outputPosition = rover.execute(instructions);
        assertEquals("2 2 W", outputPosition);
    }


    @Test
    public void test_move_FORWORD_WHERE_DIRECTION_IS_NORTH_INSIDE_PLATEAU() {
        List<INSTRUCTION> instructions = new ArrayList<>();
        instructions.add(INSTRUCTION.MOVE);
        String outputPosition = rover.execute(instructions);
        assertEquals("2 3 N", outputPosition);
    }

    @Test(expected = InvalidMoveException.class)
    public void test_move_FORWORD_WHERE_DIRECTION_IS_NORTH_AND_Y_COORDINATE_IS_MAX() {
        rover.setCoordinate(new Coordinate(2, 5));
        List<INSTRUCTION> instructions = new ArrayList<>();
        instructions.add(INSTRUCTION.MOVE);
        rover.execute(instructions);
    }


    @Test
    public void test_move_FORWORD_WHERE_DIRECTION_IS_EAST_INSIDE_PLATEAU() {
        rover.setDirection(DIRECTION.EAST);
        List<INSTRUCTION> instructions = new ArrayList<>();
        instructions.add(INSTRUCTION.MOVE);
        String outputPosition = rover.execute(instructions);
        assertEquals("3 2 E", outputPosition);
    }

    @Test(expected = InvalidMoveException.class)
    public void test_move_FORWORD_WHERE_DIRECTION_IS_EAST_AND_X_COORDINATE_IS_MAX() {
        rover.setCoordinate(new Coordinate(5, 2));
        rover.setDirection(DIRECTION.EAST);
        List<INSTRUCTION> instructions = new ArrayList<>();
        instructions.add(INSTRUCTION.MOVE);
        rover.execute(instructions);
    }

    @Test
    public void test_move_FORWORD_WHERE_DIRECTION_IS_WEST_INSIDE_PLATEAU() {
        rover.setDirection(DIRECTION.WEST);
        List<INSTRUCTION> instructions = new ArrayList<>();
        instructions.add(INSTRUCTION.MOVE);
        String outputPosition = rover.execute(instructions);
        assertEquals("1 2 W", outputPosition);
    }

    @Test(expected = InvalidMoveException.class)
    public void test_move_FORWORD_WHERE_DIRECTION_IS_WEST_AND_X_COORDINATE_IS_MIN() {
        rover.setCoordinate(new Coordinate(0, 2));
        rover.setDirection(DIRECTION.WEST);
        List<INSTRUCTION> instructions = new ArrayList<>();
        instructions.add(INSTRUCTION.MOVE);
        rover.execute(instructions);
    }

    @Test
    public void test_move_FORWORD_WHERE_DIRECTION_IS_SOUTH_INSIDE_PLATEAU() {
        rover.setDirection(DIRECTION.SOUTH);
        List<INSTRUCTION> instructions = new ArrayList<>();
        instructions.add(INSTRUCTION.MOVE);
        String outputPosition = rover.execute(instructions);
        assertEquals("2 1 S", outputPosition);
    }

    @Test(expected = InvalidMoveException.class)
    public void test_move_FORWORD_WHERE_DIRECTION_IS_SOUTH_AND_Y_COORDINATE_IS_MIN() {
        rover.setCoordinate(new Coordinate(2, 0));
        rover.setDirection(DIRECTION.SOUTH);
        List<INSTRUCTION> instructions = new ArrayList<>();
        instructions.add(INSTRUCTION.MOVE);
        rover.execute(instructions);
    }
}
