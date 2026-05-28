package spiffysprite.utils;

public abstract class NumberUtils {
    public static boolean inRange(int value, int lowerLimit) {
        return inRange(value, lowerLimit, Integer.MAX_VALUE);
    }

    public static boolean inRange(int value, int lowerLimit, int upperLimit) {
        return value >= lowerLimit && value < upperLimit;
    }
}
