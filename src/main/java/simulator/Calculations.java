package simulator;

import java.util.Map;
import java.util.Scanner;
import simulator.JSON.JSONWriter;

/**
 * The class {@code Calculations} provides methods for computing various parameters related to
 * rocket flight and altitude estimation. This includes calculations for mass, velocity, wind
 * resistance, and altitude adjustments considering environmental factors.
 * <p>
 * The methods should be called in order as they build upon each others results.
 * </p>
 * 
 */
public class Calculations {

	// Conversion factors
	private final double GRAMS_TO_OUNCES = 28.34952; // Conversion factor from grams to ounces
	private final double MM_TO_INCHES = 25.4; // Conversion factor from millimeters to inches

	// Constants for altitude calculations
	private final double PI = 3.14; // Value of π (pi), approximated to 2 decimal places
	private final double RHO = 1.2; // Density of air in kg/m^3
	private final double DRAGCD = 0.75; // Drag coefficient for a model rocket
	private final double G = 9.8; // Acceleration due to gravity in m/s²
	private final double P = 101325; // Standard atmospheric pressure at sea level in Pascals (Pa)
	private final double R = 287.05; // Specific gas constant for dry air in J/(kg * K)

	/**
	 * Calculates the total mass of the rocket in kilograms, rounded to five decimal places.
	 * <p>
	 * This is computed by converting the mass of the rocket body and engine from grams to ounces,
	 * summing these masses, and converting the total weight to kilograms.
	 * </p>
	 * 
	 * @param massEmpty the mass of the rocket body in grams.
	 * @param massEngine the mass of the engine in grams.
	 * @return the total mass of the rocket in kilograms, rounded to five decimal places.
	 */
	public double calculateMassM(double massEmpty, double massEngine) {
		double weightW = (massEmpty / GRAMS_TO_OUNCES) + (massEngine / GRAMS_TO_OUNCES);
		double massM = weightW / 16 / 2.2;
		double scaledMass = limitDecimalPlaces(massM, 5);
		return scaledMass;
	}

	/**
	 * Calculates the cross-sectional area of the rocket based on its diameter.
	 * <p>
	 * The diameter is converted from millimeters to inches, the radius is calculated in meters, and
	 * then the area is computed using the formula for the area of a circle.
	 * </p>
	 * 
	 * @param diameterD the diameter of the rocket in millimeters
	 * @return the cross-sectional area in square meters, rounded to six decimal places
	 */
	public double calculateAreaA(double diameterD) {
		double inch = diameterD / MM_TO_INCHES;
		double radiusR = 0.5 * inch / 12 * 0.3048;
		double areaA = PI * Math.pow(radiusR, 2);
		double scaledArea = limitDecimalPlaces(areaA, 6);
		return scaledArea;
	}

	/**
	 * Calculates the burn time of the rocket engine.
	 * <p>
	 * The burn time is derived by dividing the impulse by the thrust.
	 * </p>
	 * 
	 * @param impulseI the impulse of the rocket engine
	 * @param thrustT the thrust of the rocket engine
	 * @return the burn time in seconds, rounded to six decimal places
	 */
	public double calculateBurnTime(double impulseI, double thrustT) {
		double burnTimeT = impulseI / thrustT;
		double scaledBurnTime = limitDecimalPlaces(burnTimeT, 6);
		return scaledBurnTime;
	}

	/**
	 * Calculates the gravitational force on the rocket.
	 * <p>
	 * The gravitational force is computed by multiplying the rocket's mass by the acceleration due
	 * to gravity.
	 * </p>
	 * 
	 * @param massM the mass of the rocket in kilograms
	 * @return the gravitational force in Newtons, rounded to three decimal places
	 */
	public double calculateGravForceG(double massM) {
		double gravForceG = massM * G;
		double scaledG = limitDecimalPlaces(gravForceG, 3);
		return scaledG;

	}

	/**
	 * Computes the wind resistance factor for the rocket.
	 * <p>
	 * This is calculated using the air density, drag coefficient, and reference area.
	 * </p>
	 * 
	 * @param areaA the reference area in square meters
	 * @return the wind resistance factor, rounded to six decimal places
	 */
	public double calculateWindResistanceFactor(double areaA) {
		double windResFact = 0.5 * RHO * DRAGCD * areaA;
		double scaledWRF = limitDecimalPlaces(windResFact, 6);
		return scaledWRF;
	}

	/**
	 * Calculates the parameter {@code q} used in altitude and velocity calculations.
	 * <p>
	 * The parameter is derived from the thrust, gravity, and wind resistance factor.
	 * </p>
	 * 
	 * @param thrustT the thrust of the rocket engine
	 * @param gravForceG the gravitational force on the rocket
	 * @param windResFact the wind resistance factor
	 * @return the parameter {@code q}, rounded to one decimal place
	 */
	public double calculateQ(double thrustT, double gravForceG, double windResFact) {
		double q = Math.sqrt((thrustT - gravForceG) / windResFact);
		double scaledQ = limitDecimalPlaces(q, 1);
		return scaledQ;
	}

	/**
	 * Computes the parameter {@code x} used in the velocity calculation.
	 * <p>
	 * The parameter is calculated using the wind resistance factor, the value {@code q}, and the
	 * rocket's mass.
	 * </p>
	 * 
	 * @param windResFact the wind resistance factor
	 * @param q the parameter {@code q}
	 * @param massM the mass of the rocket in kilograms
	 * @return the parameter {@code x}, rounded to three decimal places
	 */
	public double calculateX(double windResFact, double q, double massM) {
		double x = 2 * windResFact * q / massM;
		double scaledX = limitDecimalPlaces(x, 3);
		return scaledX;
	}

	/**
	 * Calculates the rocket's velocity.
	 * <p>
	 * The velocity is computed based on the parameters {@code q}, {@code x}, and burn time.
	 * </p>
	 * 
	 * @param q the parameter {@code q}
	 * @param x the parameter {@code x}
	 * @param burnTimeT the burn time in seconds
	 * @return the rocket's velocity in meters per second, rounded to one decimal place
	 */
	public double calculateVelocityV(double q, double x, double burnTimeT) {
		double velocityV = q * ((1 - Math.exp(-x * burnTimeT)) / (1 + Math.exp(-x * burnTimeT)));
		double scaledV = limitDecimalPlaces(velocityV, 1);
		return scaledV;
	}

	/**
	 * Computes the boost phase distance of the rocket.
	 * <p>
	 * This is calculated using the rocket's mass, wind resistance, thrust, and velocity.
	 * </p>
	 * 
	 * @param massM the mass of the rocket in kilograms
	 * @param windResFact the wind resistance factor
	 * @param thrustT the thrust in Newtons
	 * @param velocityV the velocity of the rocket in meters per second
	 * @return the boost phase distance in meters, rounded to two decimal places
	 */
	public double calculateBoostPhaseYB(double massM, double windResFact, double thrustT,
			double velocityV) {
		double yb = (-massM / (2 * windResFact))
				* Math.log((thrustT - massM * G - windResFact * Math.pow(velocityV, 2))
						/ (thrustT - massM * G));
		double scaledYB = limitDecimalPlaces(yb, 2);
		return scaledYB;
	}

	/**
	 * Computes the coast phase distance of the rocket.
	 * <p>
	 * This is calculated using the rocket's mass, wind resistance, and velocity.
	 * </p>
	 * 
	 * @param massM the mass of the rocket in kilograms
	 * @param windResFact the wind resistance factor
	 * @param velocityV the velocity of the rocket in meters per second
	 * @return the coast phase distance in meters, rounded to one decimal place
	 */
	public double calculateCoastPhaseYC(double massM, double windResFact, double velocityV) {
		double yc = (massM / (2 * windResFact)
				* Math.log(((massM * G + windResFact * Math.pow(velocityV, 2)) / (massM * G))));
		double scaledYC = limitDecimalPlaces(yc, 1);
		return scaledYC;
	}


	/**
	 * Calculates the total theoretical altitude of the rocket.
	 * <p>
	 * The altitude is obtained by summing the boost phase and coast phase distances.
	 * </p>
	 * 
	 * @param yb the boost phase distance in meters
	 * @param yc the coast phase distance in meters
	 * @return the total altitude in meters, rounded to two decimal places
	 */
	public double calculateAltitude(double yb, double yc) {
		double altitudeA = yb + yc;
		double scaledAltitudeA = limitDecimalPlaces(altitudeA, 2);
		return scaledAltitudeA;
	}

	/**
	 * Calculates the air density based on temperature and humidity.
	 * <p>
	 * The air density is computed using the temperature in Kelvin and the standard atmospheric
	 * pressure.
	 * </p>
	 * 
	 * @param temperature the ambient temperature in degrees Celsius
	 * @param humidity the relative humidity as a percentage
	 * @return the air density in kg/m³, rounded to two decimal places
	 */
	public double calculateAirDensity(double temperature, double humidity) { // Humidity is included, but unused, in case of future precision upgrades
		double t = temperature + 273.15; // Temperature in Kelvin
		double airDensity = P / (R * t);
		double scaledAirDensity = limitDecimalPlaces(airDensity, 2);
		return scaledAirDensity;
	}

	/**
	 * Computes the effect of wind on altitude.
	 * <p>
	 * The wind effect is adjusted based on wind speed and gusts.
	 * </p>
	 * 
	 * @param windSpeed the wind speed in meters per second
	 * @param windDirection the wind direction in degrees (not used in calculation)
	 * @param windGust the maximum wind gust speed in meters per second
	 * @return the wind effect factor, rounded to three decimal places
	 */
	public double calculateWindEffect(double windSpeed, double windDirection, double windGust) { // Direction is included, but unused, in case of future precision upgrades
		double baseEffect = 1 - (windSpeed * 0.05);
		double gustEffect = 1 - (windGust * 0.01);
		double windEffect = baseEffect * gustEffect;
		double scaledWindEffect = limitDecimalPlaces(windEffect, 3);
		return scaledWindEffect;
	}

	/**
	 * Determines the effect of precipitation on altitude.
	 * <p>
	 * The effect is reduced if there is a chance of precipitation.
	 * </p>
	 * 
	 * @param precipProbability the probability of precipitation as a percentage
	 * @return the precipitation effect factor: 0.9 if precipitation is likely, otherwise 1.0
	 */
	public double calculatePrecipEffect(double precipProbability) {
		double precipEffect = precipProbability > 0 ? 0.9 : 1.0;
		return precipEffect;
	}

	/**
	 * Computes the adjusted altitude considering air density, wind effect, and precipitation.
	 * <p>
	 * The adjusted altitude is calculated by:
	 * <ul>
	 * <li>Dividing the theoretical altitude {@code scaledAltitudeA} by air density, to account for
	 * the effect of air density on the altitude.</li>
	 * <li>Multiplying the result by the {@code windEffect} and {@code precipEffect} factors to
	 * adjust for wind and precipitation effects.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param scaledAltitudeA the theoretical highest altitude in meters
	 * @param airDensity the air density in kg/m³
	 * @param windEffect the effect of wind on altitude
	 * @param precipEffect the effect of precipitation on altitude
	 * @return the adjusted altitude in meters, rounded to two decimal places
	 */
	public double calculateAdjustedAltitude(double scaledAltitudeA, double airDensity,
			double windEffect, double precipEffect) {
		double adjustedAltitude = scaledAltitudeA / airDensity; // Adjust for air density
		adjustedAltitude *= windEffect * precipEffect; // Apply wind and precipitation effects
		double scaledAdjustedAltitude = limitDecimalPlaces(adjustedAltitude, 2);
		return scaledAdjustedAltitude;
	}


	/**
	 * Rounds a value to a specified number of decimal places.
	 * 
	 * @param value the value to round
	 * @param decimalPlaces the number of decimal places to round to
	 * @return the rounded value
	 */
	private double limitDecimalPlaces(double value, int decimalPlaces) {
		return Math.round(value * Math.pow(10, decimalPlaces)) / Math.pow(10, decimalPlaces);
	}


	/**
	 * Passes calculation results to the {@link JSONWriter#saveResultsJSON(Map, Scanner)} method,
	 * for saving the results in JSON format.
	 * 
	 * @param calcResults a map of calculation results
	 * @param sc a {@link Scanner} instance for user input
	 */
	public void passToJSONWriter(Map<String, Object> calcResults, Scanner sc) {
		JSONWriter jw = new JSONWriter();
		jw.saveResultsJSON(calcResults, sc);
	}

}
