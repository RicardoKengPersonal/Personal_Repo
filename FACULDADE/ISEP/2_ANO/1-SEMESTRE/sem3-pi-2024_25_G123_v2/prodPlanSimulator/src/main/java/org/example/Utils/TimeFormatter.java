package org.example.Utils;

/**
 * A utility class for formatting time values.
 */
public class TimeFormatter {

    /**
     * Formats a given time in seconds into a more readable format.
     *
     * If the time is less than 3600 seconds (1 hour), the format will be mm:ss.
     * If the time is 3600 seconds or more, the format will be hh:mm:ss.
     *
     * @param secondsSinceStart the time in seconds to format
     * @return a formatted time string in either mm:ss or hh:mm:ss format
     */
    public static String formatTime(int secondsSinceStart) {
        if (secondsSinceStart >= 3600) {
            int hours = secondsSinceStart / 3600;
            int minutes = (secondsSinceStart % 3600) / 60;
            int seconds = secondsSinceStart % 60;

            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }

        int minutes = secondsSinceStart / 60;
        int seconds = secondsSinceStart % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }
}
