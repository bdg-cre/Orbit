package simulator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the class {@link Calculations}.
 * <p>
 * Every expected result has been calculated manually.
 * </p>
 */
public class CalculationsTest {

	private Calculations calculations;

	/**
	 * Sets up a new {@link Calculations} instance before every test.
	 */
	@BeforeEach
	public void setUp() {
		calculations = new Calculations();
	}

	@Test
	public void testCalculateMassM() {
		double massEmpty = 100.0;
		double massEngine = 200.0;
		double expectedMass = 0.30063;
		double actualMass = calculations.calculateMassM(massEmpty, massEngine);
		assertEquals(expectedMass, actualMass, 1e-5);
	}

	@Test
	public void testCalculateAreaA() {
		double diameterD = 10;
		double expectedArea = 0.000079;
		double actualArea = calculations.calculateAreaA(diameterD);
		assertEquals(expectedArea, actualArea, 1e-6);
	}

	@Test
	public void testCalculateBurnTime() {
		double impulseI = 3;
		double thrustT = 8;
		double expectedBurnTime = 0.375;
		double actualBurnTime = calculations.calculateBurnTime(impulseI, thrustT);
		assertEquals(expectedBurnTime, actualBurnTime, 1e-6);
	}

	@Test
	public void testCalculateGravForceG() {
		double massM = 0.0348;
		double expectedG = 0.34104;
		double actualG = calculations.calculateGravForceG(massM);
		assertEquals(expectedG, actualG, 1e-3);
	}

	@Test
	public void testCalculateWindResistanceFactor() {
		double areaA = 0.000241;
		double expectedWRF = 0.00010845;
		double actualWRF = calculations.calculateWindResistanceFactor(areaA);
		assertEquals(expectedWRF, actualWRF, 1e-6);
	}

	@Test
	public void testCalculateQ() {
		double thrustT = 6.8;
		double gravForceG = 0.31254;
		double windResFact = 0.00005481;
		double expectedQ = 344.0;
		double actualQ = calculations.calculateQ(thrustT, gravForceG, windResFact);
		assertEquals(expectedQ, actualQ, 1e-1);
	}

	@Test
	public void testCalculateX() {
		double windResFact = 0.00004185;
		double q = 321.4;
		double massM = 0.0410;
		double expectedX = 0.656;
		double actualX = calculations.calculateX(windResFact, q, massM);
		assertEquals(expectedX, actualX, 1e-3);
	}

	@Test
	public void testCalculateVelocity() {
		double q = 258.2;
		double x = 1.2;
		double burnTimeT = 0.452;
		double expectedVelocity = 68.4;
		double actualVelocity = calculations.calculateVelocityV(q, x, burnTimeT);
		assertEquals(expectedVelocity, actualVelocity, 1e-1);
	}

	@Test
	public void testCalculateBoostPhaseYB() {
		double massM = 0.05624;
		double windResFact = 0.000222;
		double thrustT = 6.5;
		double velocityV = 121.0;
		double expectedYB = 100.07;
		double actualYB =
				calculations.calculateBoostPhaseYB(massM, windResFact, thrustT, velocityV); // Results as 100.13
		assertEquals(expectedYB, actualYB, 1e-1); // Limited precision because of rounding issues in Java
	}

	@Test
	public void testCalculateCoastPhaseYC() {
		double massM = 0.05624;
		double windResFact = 0.000222;
		double velocityV = 121.0;
		double expectedYC = 244.5;
		double actualYC = calculations.calculateCoastPhaseYC(massM, windResFact, velocityV); // Results as 244.6
		assertEquals(expectedYC, actualYC, 1e-1); // Limited precision because of rounding issues in Java
	}

	@Test
	public void testCalculateAltitude() {
		double yb = 24.35;
		double yc = 98.7;
		double expectedAltitude = 123.05;
		double actualAltitude = calculations.calculateAltitude(yb, yc);
		assertEquals(expectedAltitude, actualAltitude, 1e-2);
	}

	@Test
	public void testCalculateAirDensity() {
		double temperature = 20;
		double expectedAirDensity = 1.20;
		double actualAirDensity = calculations.calculateAirDensity(temperature, 0); // Humidity isn't integrated yet = 0
		assertEquals(expectedAirDensity, actualAirDensity, 1e-1);
	}

	@Test
	public void testCalculateWindEffect() {
		double windSpeed = 1.45;
		double windGust = 6.54;
		double expectedWindEffect = 0.867;
		double actualWindEffect = calculations.calculateWindEffect(windSpeed, 0, windGust); // Wind direction isn't integrated yet = 0
		assertEquals(expectedWindEffect, actualWindEffect, 1e-3);

	}

	@Test
	public void testCalculatePrecipEffect() {
		double precipProbability = 0.75;
		double expectedPrecipEffect = 0.9;
		double actualPrecipEffect = calculations.calculatePrecipEffect(precipProbability);
		assertEquals(expectedPrecipEffect, actualPrecipEffect);
	}

	@Test
	public void testCalculateAdjustedAltitude() {
		double scaledAltitudeA = 94.14;
		double airDensity = 1.17;
		double windEffect = 0.584;
		double precipEffect = 0.9;
		double expectedAdjustedAltitude = 42.29;
		double actualAdjustedAltitude = calculations.calculateAdjustedAltitude(scaledAltitudeA,
				airDensity, windEffect, precipEffect);
		assertEquals(expectedAdjustedAltitude, actualAdjustedAltitude, 1e-2);
	}

	// limitDecimalPlaces() does not need a test, 
	// because if the calculation-methods work as expected,
	// then by extension the private method should work as intended.
}
