package Eureka;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeConverter {

    public static LocalDateTime dateTimeConverter(String timeToBeConverted) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            return LocalDateTime.parse(timeToBeConverted.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Invalid date format. Please use yyyy-MM-dd HH:mm, e.g. 2025-09-01 18:00"
            );
        }
    }

}
