package org.nasa.mars.domain;

import java.util.Optional;
import java.util.stream.Stream;

public enum INSTRUCTION {
    LEFT('L'),
    RIGHT('R'),
    MOVE('M');

    private char instruction;

    INSTRUCTION(char instruction) {
        this.instruction = instruction;
    }

    public static Optional<INSTRUCTION> getInstructionByCode(char instruction) {
        return Stream.of(values()).filter(i -> i.instruction == instruction).findFirst();
    }
}
