package simulator.CSV;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import simulator.Body;
import simulator.Engine;

/**
 * The class {@code CSVFileReader} handles reading and parsing CSV files to obtain lists of
 * {@link Engine} and {@link Body} objects.
 * <p>
 * This class uses the OpenCSV library to convert CSV data into Java objects.
 * </p>
 */
public class CSVFileReader {

    /**
     * Reads and returns a list of {@link Engine} objects from the CSV file.
     * 
     * @return a list of {@link Engine} objects
     */
    public List<Engine> getEngines() {
        // Reads the CSV file for engines and returns the list
        List<Engine> engines = readCSVFile("engines", Engine.class);
        return engines;
    }

    /**
     * Reads and returns a list of {@link Body} objects from the CSV file.
     * 
     * @return a list of {@link Body} objects
     */
    public List<Body> getBodies() {
        // Reads the CSV file for bodies and returns the list
        List<Body> bodies = readCSVFile("bodies", Body.class);
        return bodies;
    }

    /**
     * Reads a CSV file and converts its contents to a list of objects of type {@code T}.
     * <p>
     * The file is specified by the {@code fileName} parameter and is expected to be in the
     * "resources" directory with a .csv extension. The {@code clazz} parameter specifies the type
     * of objects to parse from the CSV file.
     * </p>
     * 
     * @param <T> the type of objects to read from the CSV file
     * @param fileName the name of the CSV file (without extension)
     * @param clazz the class type of objects to create
     * @return a list of objects of type {@code T}
     */
    private <T> List<T> readCSVFile(String fileName, Class<T> clazz) {
        List<T> result = null;
        try (FileReader fr = new FileReader("resources\\" + fileName + ".csv")) {
            // Create a CSVReader to read the file
            CSVReader csvReader = new CSVReaderBuilder(fr).build();

            // Create a CsvToBean instance for parsing the CSV
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(csvReader).withType(clazz).build();

            // Parse the CSV file and populate the list
            result = csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace(); // Handle file reading exceptions
        }
        return result;
    }
}
