package org.nasa.mars.utils;

import org.apache.commons.lang3.StringUtils;

public class RovertInputFileValidator {
    
    public static boolean plateauInputIsValidated(String plateauInput) {
        return StringUtils.isNotBlank(plateauInput) && plateauInput.matches("^[0-9] [0-9]$");
    }


    public static boolean roverInputIsValidated(String roverInput) {
        return StringUtils.isNotBlank(roverInput) && roverInput.matches("^[0-9] [0-9] (N|S|W|E)$");
    }
    
    public static boolean instructionsInputIsValidated(String instructionsInput) {
        return StringUtils.isNotBlank(instructionsInput)
                && StringUtils.containsOnly(instructionsInput,
                'L', 'R', 'M');
    }
}
