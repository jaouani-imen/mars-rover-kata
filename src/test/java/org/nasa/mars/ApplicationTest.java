package org.nasa.mars;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.nasa.mars.exceptions.RoverInputDataValidationException;
import org.nasa.mars.utils.RovertInputFileManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class ApplicationTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    final String PATH = "./src/test/resources/";

    @Test(expected = IllegalArgumentException.class)
    public void rover_file_name_is_missing() throws RoverInputDataValidationException, IOException {
        Application.main(null);
    }

    @Test(expected = FileNotFoundException.class)
    public void rover_file_test_not_Found() throws RoverInputDataValidationException, IOException {
        Application.main("not_existing_file");
    }

    @Test
    public void first_line_error_test() throws RoverInputDataValidationException, IOException {
        expectedEx.expect(RoverInputDataValidationException.class);
        expectedEx.expectMessage(RovertInputFileManager.PLATEAU_MAX_POSITION_ERROR_MESSAGE);
        Application.main(PATH + "first_line_error_file.txt");
    }

    @Test
    public void rover_line_error_test() throws RoverInputDataValidationException, IOException {
        expectedEx.expect(RoverInputDataValidationException.class);
        expectedEx.expectMessage(RovertInputFileManager.ROVER_PARAMS_ERROR_MESSAGE);
        Application.main(PATH + "rover_line_error_file.txt");
    }

    @Test
    public void instructions_line_error_test() throws RoverInputDataValidationException, IOException {
        expectedEx.expect(RoverInputDataValidationException.class);
        expectedEx.expectMessage(RovertInputFileManager.INSTRUCTIONS_ERROR_MESSAGE);
        Application.main(PATH + "instructions_line_error_file.txt");
    }

    @Test
    public void rover_execution_success() throws RoverInputDataValidationException, IOException {
        Application.main(PATH + "success_rover_input_file.txt");
        assertNotNull(Application.roverInstructionsResults);
        assertEquals(Application.roverInstructionsResults.size(), 2);
        assertEquals("1 3 N", Application.roverInstructionsResults.get(0));
        assertEquals("5 1 E", Application.roverInstructionsResults.get(1));
    }
}
