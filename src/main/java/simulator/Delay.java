package simulator;

/**
 * The class {@code Delay} represents the delay the rocket's engine has.
 * <p>
 * The delay is a measure of the time delay before the rocket's engine ignites or performs a
 * specific action.
 * </p>
 */
public class Delay {

    /**
     * The delay time of the rocket's engine.
     */
    private double delay;

    /**
     * Default constructor initializing the {@code Delay} with a default value of zero.
     */
    public Delay() {
        this.delay = 0;
    };

    /**
     * Parameterized constructor to initialize the {@code Delay} using a specific delay value
     * 
     * @param dly the delay of the rocket's engine
     */
    public Delay(double dly) {
        this.delay = dly;
    }

    /**
     * Gets the delay of the rocket's engine.
     * 
     * @return the delay of the rocket's engine.
     */
    public double getDelay() {
        return delay;
    }
}
