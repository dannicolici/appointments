package ro.bitgloss.io;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}