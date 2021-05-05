package mappe.del2.patient;

import mappe.del2.patient.model.Patient;
import mappe.del2.patient.model.PatientRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type File handler test.
 */
public class FileHandlerTest {
    private FileHandler fileHandler;
    private PatientRegister patientRegister;
    private final File file = new File("PatientTestResource.csv");
    private final String path = file.getAbsolutePath();

    /**
     * Init.
     */
    @BeforeEach
    public void init() {
        fileHandler = new FileHandler();
        patientRegister = new PatientRegister();
        System.out.println(path);
    }

    /**
     * Write to file does not throw.
     */
    @Test
    @DisplayName("Writing to file does not throw exception")
    public void writeToFileDoesNotThrow() {
        assertDoesNotThrow(() -> fileHandler.writeCSV(patientRegister.getPatients(),path));
    }

    /**
     * Read from file does not throw.
     */
    @Test
    @DisplayName("Reading from file does not throw exception")
    public void readFromFileDoesNotThrow() {
        assertDoesNotThrow(() -> fileHandler.readCSV(file));
    }

    /**
     * Reading empty file.
     *
     * @throws IOException the io exception
     */
    @Test
    @DisplayName("Reading empty file returns empty list")
    public void readingEmptyFile() throws IOException {
        ArrayList<Patient> list = fileHandler.readCSV(file);
        assertTrue(list.isEmpty());
    }

    /**
     * Writing then reading.
     *
     * @throws IOException the io exception
     */
    @Test
    @DisplayName("The read list is the same as the written list")
    public void writingThenReading() throws IOException {
        fileHandler.writeCSV(patientRegister.getPatients(),path);
        ArrayList<Patient> fromFile = fileHandler.readCSV(file);
        assertEquals(patientRegister.getPatients(),fromFile);
    }
}
