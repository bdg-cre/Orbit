package simulator.XML;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import simulator.Body;
import simulator.ConfigManager;
import simulator.Delay;
import simulator.Engine;
import simulator.Impulse;
import simulator.Thrust;



/**
 * The class {@code XMLReader} parses the XML files to extract {@link Engine} and {@link Body} data.
 * <p>
 * This class handles reading from XML files named "engines.xml" and "bodies.xml". It provides
 * methods to parse these files and return lists of {@link Engine} and {@link Body} objects.
 * </p>
 */
public class XMLReader {

    static ConfigManager configManager = new ConfigManager();

    /**
     * Parses the specified XML-file and returns a {@link Document} object.
     * <p>
     * This method is used internally to read XML data from the file passed from
     * {@link ConfigManager}.
     * </p>
     * 
     * @return the {@link Document} object representing the XML data
     */
    private Document getXMLBodies() {

        Document xml = null;
        try {
            File xmlBodies = configManager.returnXMLBodies();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            xml = db.parse(xmlBodies);
            xml.getDocumentElement().normalize(); // .xml gets normalized


        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace(); // Handle XML parsing exceptions
        }
        return xml;
    }

    public Document getXMLEngines() {
        Document xml = null;
        try {
            File xmlEngines = configManager.returnXMLEngines();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            xml = db.parse(xmlEngines);
            xml.getDocumentElement().normalize();
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
        return xml;
    }



    /**
     * Returns a {@link NodeList} containing all "engine" elements from the XML file.
     * 
     * @return a {@link NodeList} of "engine" elements
     */
    private NodeList returnEngineList() {
        Document xml = getXMLEngines();
        NodeList engineList = xml.getElementsByTagName("engine");
        return engineList;
    }



    /**
     * Returns a {@link NodeList} containing all "body" elements from the XML file.
     * 
     * @return a {@link NodeList} of "body" elements
     */
    private NodeList returnBodyList() {
        Document xml = getXMLBodies();
        NodeList bodyList = xml.getElementsByTagName("body");
        return bodyList;
    }

    /**
     * Parses the "bodies.xml" file and returns a list of {@link Body} objects.
     * <p>
     * This method converts each "body" element in the XML to a {@link Body} object and adds it to a
     * list.
     * </p>
     * 
     * @return a list of {@link Body} objects
     */
    public List<Body> getBodies() {

        NodeList bodyList = returnBodyList();
        List<Body> bodies = new ArrayList<>();

        for (int i = 0; i < bodyList.getLength(); i++) {
            Element bodyElement = (Element) bodyList.item(i);
            String bodyModel = bodyElement.getAttribute("model").trim();

            Element emptyMassElement =
                    (Element) bodyElement.getElementsByTagName("emptymass").item(0);
            double emptyMass = Double.parseDouble(emptyMassElement.getTextContent().trim());

            Element lengthElement = (Element) bodyElement.getElementsByTagName("length").item(0);
            double length = Double.parseDouble(lengthElement.getTextContent().trim());

            Element diameterElement =
                    (Element) bodyElement.getElementsByTagName("diameter").item(0);
            double diameter = Double.parseDouble(diameterElement.getTextContent().trim());

            Body body = new Body(bodyModel, emptyMass, length, diameter);

            bodies.add(body);
        }
        return bodies;

    }

    /**
     * Parses the "engines.xml" file and returns a list of {@link Engine} objects.
     * <p>
     * This method converts each "engine" element in the XML to an {@link Engine} object with nested
     * attributes and adds it to a list.
     * </p>
     * 
     * @return a list of {@link Engine} objects
     */
    public List<Engine> getEngines() {

        NodeList engineList = returnEngineList();
        List<Engine> engines = new ArrayList<>();


        for (int i = 0; i < engineList.getLength(); i++) {
            Element engineElement = (Element) engineList.item(i);
            String engineClass = engineElement.getAttribute("class").trim();
            NodeList typeList = engineElement.getElementsByTagName("type");

            for (int j = 0; j < typeList.getLength(); j++) {
                Element typeElement = (Element) typeList.item(j);
                String description = typeElement.getAttribute("description").trim();

                Element modelElement = (Element) typeElement.getElementsByTagName("model").item(0);
                String model = modelElement.getTextContent().trim();

                Element delayElement = (Element) typeElement.getElementsByTagName("delay").item(0);
                double delay = Double.parseDouble(delayElement.getTextContent().trim());

                Element totalImpulseElement =
                        (Element) typeElement.getElementsByTagName("totalimpulse").item(0);
                double totalImpulse =
                        Double.parseDouble(totalImpulseElement.getTextContent().trim());

                Element peakThrustElement =
                        (Element) typeElement.getElementsByTagName("peakthrust").item(0);
                double peakThrust = Double.parseDouble(peakThrustElement.getTextContent().trim());

                Element propellantMassElement =
                        (Element) typeElement.getElementsByTagName("propellantmass").item(0);
                double propellantMass =
                        Double.parseDouble(propellantMassElement.getTextContent().trim());

                Element totalMassElement =
                        (Element) typeElement.getElementsByTagName("totalmass").item(0);
                double totalEngineMass =
                        Double.parseDouble(totalMassElement.getTextContent().trim());

                Delay dly = new Delay(delay);
                Impulse impulse = new Impulse(totalImpulse);
                Thrust thrust = new Thrust(peakThrust);
                Engine engine = new Engine(engineClass, description, model, dly, impulse, thrust,
                        propellantMass, totalEngineMass);
                engines.add(engine);
            }
        }
        return engines;
    }
}
