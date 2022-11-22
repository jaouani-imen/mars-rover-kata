package org.nasa.mars;

import org.nasa.mars.domain.INSTRUCTION;
import org.nasa.mars.domain.Plateau;
import org.nasa.mars.domain.Rover;
import org.nasa.mars.exceptions.RoverInputDataValidationException;
import org.nasa.mars.utils.RovertInputFileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static List<String> roverInstructionsResults ;
    public static void main(String... args) throws IOException, RoverInputDataValidationException {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("Filename is missing");
        }
        File inputFile = new File(args[0]);
        if (!inputFile.isFile()) {
            throw new FileNotFoundException("File not exist");
        } else {
            roverInstructionsResults = new ArrayList<>();
            List<String> roverInputFileData = RovertInputFileManager.parseInputFromFile(args[0]);
            Plateau plateau = RovertInputFileManager.parsePlateauInput(roverInputFileData.get(0));

            for (int i = 1; i < roverInputFileData.size(); i += 2) {
                Rover rover = RovertInputFileManager.parseRoverPositionInput(roverInputFileData.get(i), plateau);
                List<INSTRUCTION> instructions = RovertInputFileManager.parseInstructionsInput(roverInputFileData.get(i + 1));
                rover.execute(instructions);
                roverInstructionsResults.add(rover.printPosition());
            }

            roverInstructionsResults.forEach(result -> System.out.println(result));
        }
    }
}