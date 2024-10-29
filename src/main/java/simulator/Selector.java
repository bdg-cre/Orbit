package simulator;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * The class {@code Selector} handles the selection of a {@link Body} and an {@link Engine} to
 * create a {@link Rocket}. It utilizes a {@link ReaderSelector} to fetch available data and
 * facilitates user interaction for component selection.
 */
public class Selector {

    private Engine selectedEngine;
    private Body selectedBody;
    private Rocket selectedRocket;
    private ReaderSelector readerSelector;

    /**
     * Default constructor initializing the {@code Selector} with a {@link ReaderSelector} from the
     * {@link ReaderFactory#createReaderSelector()} method.
     */
    public Selector() {
        this.readerSelector = ReaderFactory.createReaderSelector();
    }

    /**
     * Parameterized constructor for injecting a custom {@link ReaderSelector}.
     * 
     * @param readerSelector the {@link ReaderSelector} to use
     */
    public Selector(ReaderSelector readerSelector) {
        this.readerSelector = readerSelector;
    }

    /**
     * Asks the user to select a file type.
     * 
     * @param sc the {@link Scanner} instance for user input
     */
    public void inputFileType(Scanner sc) {
        readerSelector.selectFileType(sc);
    }

    /**
     * Asks the user to select a body model, and returns the selected {@link Body} object.
     * 
     * This method displays the available models, then asks the user to select one. If the selected
     * model is valid, the corresponding {@link Body} object is returned.
     * 
     * @param sc the {@link Scanner} instance for user input
     * @return the selected {@link Body} object
     */
    private Body selectBody(Scanner sc) {
        // List of all bodies read from the selector
        List<Body> bodiesList = readerSelector.selectReader(Body.class);
        // Set to store the models
        Set<String> uniqueModels = new TreeSet<>();

        // Fill the Set with models
        for (Body body : bodiesList) {
            uniqueModels.add(body.getModel());
        }
        // Print available models
        System.out.println("Available Models: ");
        for (String model : uniqueModels) {
            System.out.println(model);
        }

        while (true) {
            // Ask the user to select a model
            System.out.println("Select Model: ");
            if (!sc.hasNextLine()) {
                System.out.println("Please check your input and try again.");
                continue;
            }
            String selectedModel = sc.nextLine().trim();
            boolean modelFound = false;

            // Check if selected model exists in the bodies list
            for (Body body : bodiesList) {
                if (body.getModel().equalsIgnoreCase(selectedModel)) {
                    modelFound = true;
                    selectedBody = body;
                    return selectedBody;

                }
            }

            // Notify user if the model is not found
            if (!modelFound) {
                System.out.println("Model not found.");
            }
        }

    }

    /**
     * Asks the user to select an engine class and type, and returns the selected {@link Engine}
     * object.
     * 
     * This method first displays the available engine classes, then asks the user to select a
     * class. Once a valid class is selected, it display the types of engines within that
     * engine-class and asks the user to select an engine type. If the selected type is valid, the
     * corresponding {@link Engine} object is returned.
     * 
     * @param sc the {@link Scanner} instance for user input
     * @return the selected {@link Engine} object
     */
    private Engine selectEngine(Scanner sc) {
        // List of all engines read from the selector
        List<Engine> enginesList = readerSelector.selectReader(Engine.class);
        // Set to store unique engine classes
        Set<String> uniqueEngineClasses = new TreeSet<>();

        // Fill the Set with unique engine classes
        for (Engine engine : enginesList) {
            uniqueEngineClasses.add(engine.getEngineClass());
        }

        // Print available engine classes
        System.out.println("Available engine classes: ");
        for (String engineClass : uniqueEngineClasses) {
            System.out.println(engineClass);
        }


        while (true) {
            // Ask user to select an engine class
            System.out.println("Select Engine-class: ");
            if (!sc.hasNextLine()) {
                System.out.println("Please check your input and try again.");
                continue;
            }
            String selectedEngineClass = sc.nextLine().trim();
            boolean classFound = false;

            // Check if selected engine class exists in the list of all engines
            for (Engine engine : enginesList) {
                if (engine.getEngineClass().equalsIgnoreCase(selectedEngineClass)) {
                    classFound = true;

                    // Print available types for the selected engine class
                    System.out.println("Available types for class " + selectedEngineClass + ":");
                    for (Engine eng : enginesList) {
                        if (eng.getEngineClass().equalsIgnoreCase(selectedEngineClass)) {
                            System.out.println(eng.getDescription());
                        }
                    }

                    // Ask user to select an engine type
                    System.out.println("Select your engine type: ");
                    if (!sc.hasNextLine()) {
                        System.out.println("Please check your input and try again.");
                        continue;
                    }
                    String selectedEngineType = sc.nextLine().trim();

                    // Find and return the selected engine
                    for (Engine eng : enginesList) {
                        if (eng.getEngineClass().equalsIgnoreCase(selectedEngineClass)
                                && eng.getDescription().equalsIgnoreCase(selectedEngineType)) {
                            selectedEngine = eng;
                            return selectedEngine;

                        }
                    }
                    if (selectedEngine == null) {
                        System.out.println("Type not found.");
                    }

                }

            }
            if (!classFound) {
                System.out.println("Engine class not found.");
            }
        }


    }

    /**
     * Creates a {@link Rocket} by selecting a {@link Body} and an {@link Engine} from user input
     * via {@link #selectBody(Scanner)} and {@link #selectEngine(Scanner)}.
     * 
     * @param sc the {@link Scanner} instance for user input
     * @return the created {@link Rocket}
     * @throws IllegalStateException if no {@link Body} or {@link Engine} is selected
     */
    public Rocket createRocket(Scanner sc) {

        selectedRocket = new Rocket(selectBody(sc), selectEngine(sc));
        if (selectedBody == null || selectedEngine == null) {
            throw new IllegalStateException("No Body or Engine selected.");
        } else {
            return selectedRocket;
        }

    }

}
