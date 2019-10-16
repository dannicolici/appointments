package ro.bitgloss.io;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;

public interface TypedIO extends IO {

    DateFormat DF = new SimpleDateFormat("dd/MM/yyyy");

    default Date readDate(String prompt, String errorMessage) {
        try {
            return DF.parse(readString(prompt, errorMessage).get());
        } catch (ParseException e) {
            printLine(errorMessage);
            return readDate(prompt, errorMessage);
        }
    }

    default Date readDate(String prompt, String errorMessage, Consumer<Date> f) {
        var date = readDate(prompt, errorMessage);
        f.accept(date);
        return date;
    }
}