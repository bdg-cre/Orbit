package simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import simulator.CSV.CSVFileReader;
import simulator.JSON.JSONReader;
import simulator.XML.XMLReader;

/**
 * The class {@code ReaderSelector} manages the selection of different file types (XML, JSON, CSV)
 * and delegates the task of reading data to the appropriate reader based on the selected file type.
 * <p>
 * This class allows the user to choose a file type and then fetch data from that file type using
 * specific readers for XML, JSON, and CSV formats.
 * </p>
 */
public class ReaderSelector {

    // Instances of readers for XML, JSON, and CSV file formats
    private XMLReader xmlReader;
    private JSONReader jsonReader;
    private CSVFileReader csvFileReader;
    private FileType fileType; // The currently selected file type

    /**
     * Default constructor initializes the XML, JSON, and CSV readers.
     */
    public ReaderSelector() {
        this.xmlReader = new XMLReader();
        this.jsonReader = new JSONReader();
        this.csvFileReader = new CSVFileReader();
    }

    /**
     * Constructor allowing injection of custom readers.
     * 
     * @param xmlReader the {@link XMLReader} instance to be used
     * @param jsonReader the {@link JSONReader} instance to be used
     * @param csvFileReader the {@link CSVFileReader} instance to be used
     */
    public ReaderSelector(XMLReader xmlReader, JSONReader jsonReader, CSVFileReader csvFileReader) {
        this.xmlReader = xmlReader;
        this.jsonReader = jsonReader;
        this.csvFileReader = csvFileReader;
    }

    /**
     * Asks the user to select a file type (XML, JSON, CSV) and sets the {@code fileType} field.
     * <p>
     * This method continues to ask the user until a valid file type is entered.
     * </p>
     * 
     * @param sc the {@link Scanner} instance for user input
     */
    public void selectFileType(Scanner sc) {
        while (true) {
            System.out.println("Please select preferred file-type (XML/JSON/CSV): ");
            String input = sc.nextLine().trim().toUpperCase();
            try {
                // Set the fileType based on user input
                this.fileType = FileType.valueOf(input);
                break; // Exit loop on succesful selection
            } catch (IllegalArgumentException e) {
                // Handle invalid file type input
                System.out.println("Invalid file type. Please try again.");
            }
        }
    }

    /**
     * Selects and returns data from the currently selected file type.
     * <p>
     * Based on the value of {@code fileType}, this method delegates to the appropriate method for
     * retrieving data in the format specified.
     * </p>
     * 
     * @param <T> the type of data to retrieve
     * @param clazz the {@link Class} object of the type to retrieve
     * @return a {@link List} of data objects of Type {@code T}
     * @throws IllegalStateException if no file type has been selected
     * @throws IllegalArgumentException if the class type is unsupported for the selected file type
     */
    public <T> List<T> selectReader(Class<T> clazz) {
        if (fileType == null) {
            throw new IllegalStateException("No file-type selected.");
        }

        switch (fileType) {
            case XML:
                return getXMLData(clazz);
            case JSON:
                return getJSONData(clazz);
            case CSV:
                return getCSVData(clazz);
            default:
                throw new IllegalArgumentException("Unsupported file-type.");
        }
    }

    /**
     * Casts a list of unknown type to a list of the specified type, performing a runtime check to
     * ensure that all elements are of the desired type.
     * <p>
     * This method iterates through the provided list, checks each element to ensure it is an
     * instance of the specified class, and then returns a new list containing the casted elements.
     * In case of an undesired type, throws an exception.
     * </p>
     * 
     * @param <T> the type of elements in the returned list
     * @param clazz the {@link Class} object representing the desired type of elements
     * @param rawList the list of elements to be cast
     * @return a {@link List} of elements of type {@code T}
     * @throws ClassCastException if any element in the list is not of the specified type
     */
    private <T> List<T> castList(Class<T> clazz, List<?> rawList) {
        List<T> result = new ArrayList<>();
        for (Object obj : rawList) {
            if (clazz.isInstance(obj)) {
                result.add(clazz.cast(obj));
            } else {
                throw new ClassCastException(
                        "List contains elements that are not of type " + clazz.getName());
            }
        }
        return result;

    }

    /**
     * Retrieves data from an XML file based on the specified class type.
     * <p>
     * Returns a list of data objects of the specified type if supported, otherwise throws an
     * exception.
     * </p>
     * 
     * @param <T> the type of data to retrieve
     * @param clazz the {@link Class} object of the type to retrieve
     * @return a {@link List} of data objects of type {@code T}
     * @throws IllegalArgumentException if the class type is unsupported for XML
     */
    private <T> List<T> getXMLData(Class<T> clazz) {
        if (clazz.equals(Body.class)) {
            return castList(clazz, xmlReader.getBodies());
        } else if (clazz.equals(Engine.class)) {
            return castList(clazz, xmlReader.getEngines());
        } else {
            throw new IllegalArgumentException("Unsupported class type for XML.");
        }
    }

    /**
     * Retrieves data from a JSON file based on the specified class type.
     * <p>
     * Returns a list of data objects of the specified type if supported, otherwise throws an
     * exception.
     * </p>
     * 
     * @param <T> the type of data to retrieve
     * @param clazz the {@link Class} object of the type to retrieve
     * @return a {@link List} of data objects of type {@code T}
     * @throws IllegalArgumentException if the class type is unsupported for JSON
     */
    private <T> List<T> getJSONData(Class<T> clazz) {
        if (clazz.equals(Body.class)) {
            return castList(clazz, jsonReader.getBodies());
        } else if (clazz.equals(Engine.class)) {
            return castList(clazz, jsonReader.getEngines());
        } else {
            throw new IllegalArgumentException("Unsupported class type for JSON.");
        }
    }

    /**
     * Retrieves data from a CSV file based on the specified class type.
     * <p>
     * Returns a list of data objects of the specified type if supported, otherwise throws an
     * exception.
     * </p>
     * 
     * @param <T> the type of data to retrieve
     * @param clazz the {@link Class} object of the type to retrieve
     * @return a {@link List} of data objects of type {@code T}
     * @throws IllegalArgumentException if the class type is unsupported for CSV
     */
    private <T> List<T> getCSVData(Class<T> clazz) {
        if (clazz.equals(Body.class)) {
            return castList(clazz, csvFileReader.getBodies());
        } else if (clazz.equals(Engine.class)) {
            return castList(clazz, csvFileReader.getEngines());
        } else {
            throw new IllegalArgumentException("Unsupported class type for CSV.");
        }
    }

    /**
     * Enum representing the supported file types for reading.
     */
    private enum FileType {
        XML, JSON, CSV
    }


}
