package org.nasa.mars.utils;

import org.nasa.mars.domain.*;
import org.nasa.mars.exceptions.RoverInputDataValidationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RovertInputFileManager {

    public static final String PLATEAU_MAX_POSITION_ERROR_MESSAGE = "Plateau Input Data is not valid !";
    public static final String ROVER_PARAMS_NOT_VALID_ERROR_MESSAGE = "Rover Input Data is not valid !";
    public static final String ROVER_PARAMS_OUT_OF_PLATEAU_ERROR_MESSAGE = "Rover coordinate is out of Plateau !";

    public static final String INSTRUCTIONS_ERROR_MESSAGE = "Instructions Input Data is not valid !";
    public static final String INPUT_DATA_LINE_SEPARATOR = " ";

    public static List<String> parseInputFromFile(String fileName) throws IOException {
        List<String> result;
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            result = lines.collect(Collectors.toList());
        }
        return result;
    }

    public static Plateau parsePlateauInput(String plateauInput) throws RoverInputDataValidationException {
        if (!RovertInputFileValidator.plateauInputIsValidated(plateauInput))
            throw new RoverInputDataValidationException(PLATEAU_MAX_POSITION_ERROR_MESSAGE);
        String[] inputArray = plateauInput.split(INPUT_DATA_LINE_SEPARATOR);
        int maximalCoordinateX = Integer.parseInt(inputArray[0]);
        int maximalCoordinateY = Integer.parseInt(inputArray[1]);
        return new Plateau(maximalCoordinateX, maximalCoordinateY);
    }

    public static Rover parseRoverPositionInput(String positionInput, Plateau plateau) throws RoverInputDataValidationException {
        if (!RovertInputFileValidator.roverInputIsValidated(positionInput))
            throw new RoverInputDataValidationException(ROVER_PARAMS_NOT_VALID_ERROR_MESSAGE);
        String[] inputArray = positionInput.split(INPUT_DATA_LINE_SEPARATOR);
        int roverCoordinateX = Integer.parseInt(inputArray[0]);
        int roverCoordinateY = Integer.parseInt(inputArray[1]);
        Coordinate roverCoordinate = new Coordinate(roverCoordinateX, roverCoordinateY);
        if (plateau.outOfPlateau(roverCoordinate))
            throw new RoverInputDataValidationException(ROVER_PARAMS_OUT_OF_PLATEAU_ERROR_MESSAGE);
        DIRECTION direction = DIRECTION.getDirectionByCode(inputArray[2].toCharArray()[0]).get();
        return new Rover(plateau, roverCoordinate, direction);
    }

    public static List<INSTRUCTION> parseInstructionsInput(String instructionsInput) throws RoverInputDataValidationException {
        if (!RovertInputFileValidator.instructionsInputIsValidated(instructionsInput))
            throw new RoverInputDataValidationException(INSTRUCTIONS_ERROR_MESSAGE);
        char[] inputArray = instructionsInput.toCharArray();
        ArrayList<INSTRUCTION> instructionsList = new ArrayList<>();
        for (char character : inputArray) {
            INSTRUCTION instruction = INSTRUCTION.getInstructionByCode(character).get();
            instructionsList.add(instruction);
        }

        return instructionsList;
    }
}
