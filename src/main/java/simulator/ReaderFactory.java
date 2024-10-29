package simulator;

import simulator.CSV.CSVFileReader;
import simulator.JSON.JSONReader;
import simulator.XML.XMLReader;

/**
 * The class {@code ReaderFactory} is a factory for creating instances of various reader classes. It
 * provides static methods to create and return new instances of:
 * <ul>
 * <li>{@link XMLReader},</li>
 * <li>{@link JSONReader},</li>
 * <li>{@link CSVFileReader}</li>
 * <li>and {@link ReaderSelector}.</li>
 * </ul>
 * 
 * <p>
 * This class simplifies the creation process and centralizes the instantiation logic for these
 * reader objects.
 * </p>
 */
public class ReaderFactory {

    /**
     * Creates and returns a new instance of {@link XMLReader}.
     * 
     * @return a new instance of {@link XMLReader}
     */
    public static XMLReader createXMLReader() {
        return new XMLReader();
    }

    /**
     * Creates and returns a new instance of {@link JSONReader}.
     * 
     * @return a new instance of {@link JSONReader}
     */
    public static JSONReader createJSONReader() {
        return new JSONReader();
    }

    /**
     * Creates and returns a new instance of {@link CSVFileReader}.
     * 
     * @return a new instance of {@link CSVFileReader}
     */
    public static CSVFileReader createCSVFileReader() {
        return new CSVFileReader();
    }

    /**
     * Creates and returns a new instance of {@link ReaderSelector}, initialized with instances of
     * {@link XMLReader}, {@link JSONReader} and {@link CSVFileReader}.
     * 
     * @return a new instance of {@link ReaderSelector}
     */
    public static ReaderSelector createReaderSelector() {
        return new ReaderSelector(createXMLReader(), createJSONReader(), createCSVFileReader());
    }
}
