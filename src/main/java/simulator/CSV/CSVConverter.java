package simulator.CSV;

import com.opencsv.bean.AbstractBeanField;
import simulator.Delay;
import simulator.Engine;
import simulator.Impulse;
import simulator.Thrust;

/**
 * The class {@code CSVConverter} contains custom converters for CSV data fields, i.e.
 * {@code delay}, {@code impulse} or {@code thrust} in the {@link Engine} class.
 * <p>
 * These converters extend {@link AbstractBeanField} and provide the logic to convert String values
 * into specific custom types.
 * </p>
 */
public class CSVConverter {

    /**
     * The class {@code DelayConverter} converts String values from CSV into {@link Delay} objects.
     * If the input string is not a valid number, it defaults to a Delay with a value of zero.
     */
    public static class DelayConverter extends AbstractBeanField<Double, Delay> {

        /**
         * Converts String value to a {@link Delay} object.
         * 
         * @param value the string value to convert
         * @return the converted {@link Delay} object
         */
        @Override
        protected Delay convert(String value) {
            try {
                double delayValue = Double.parseDouble(value.trim());
                return new Delay(delayValue);
            } catch (NumberFormatException e) {
                return new Delay(0);
            }
        }
    }

    /**
     * The class {@code ImpulseConverter} converts String values from CSV into {@link Impulse}
     * objects. If the input string is not a valid number, it defaults to an Impulse with a value of
     * zero.
     */
    public static class ImpulseConverter extends AbstractBeanField<Double, Impulse> {

        /**
         * Converts String value to a {@link Impulse} object.
         * 
         * @param value the string value to convert
         * @return the converted {@link Impulse} object
         */
        @Override
        protected Impulse convert(String value) {
            try {
                double impulseValue = Double.parseDouble(value.trim());
                return new Impulse(impulseValue);
            } catch (NumberFormatException e) {
                return new Impulse(0);
            }
        }
    }

    /**
     * The class {@code ThrustConverter} converts String values from CSV into {@link Thrust}
     * objects. If the input string is not a valid number, it defaults to a Thrust with a value of
     * zero.
     */
    public static class ThrustConverter extends AbstractBeanField<Double, Thrust> {

        /**
         * Converts String value to a {@link Thrust} object.
         * 
         * @param value the string value to convert
         * @return the converted {@link Thrust} object
         */
        @Override
        protected Thrust convert(String value) {
            try {
                double thrustValue = Double.parseDouble(value.trim());
                return new Thrust(thrustValue);
            } catch (NumberFormatException e) {
                return new Thrust(0);
            }
        }
    }
}
