package ro.bitgloss.io;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

public interface TypedIO extends IO {

    DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    default LocalDate readDate(String prompt, String errorMessage) {
        try {
            return LocalDate.parse(readString(prompt, errorMessage).get(), DF);
        } catch (Exception e) {
            printLine(errorMessage);
            return readDate(prompt, errorMessage);
        }
    }

    default LocalDate readDate(String prompt, String errorMessage, Consumer<LocalDate> f) {
        var date = readDate(prompt, errorMessage);
        f.accept(date);
        return date;
    }
}