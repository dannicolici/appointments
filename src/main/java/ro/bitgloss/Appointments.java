package ro.bitgloss;

import io.IO;
import io.TypedIO;
import ro.bitgloss.Types.ReadWriter;
import ro.bitgloss.Types.View;
import ro.bitgloss.Types.ViewWriter;
import ro.bitgloss.domain.Appointment;

import java.util.function.Function;
import java.util.stream.Stream;

public class Appointments {

    public static ReadWriter<Appointment, TypedIO> addNew =
            writer -> reader -> writer.accept(new Appointment(
                    reader.readDate("Enter date: ", "invalid date"),
                    reader.readString("Enter doctor: ", "").orElse(""),
                    reader.readString("Enter patient: ", "").orElse(""),
                    reader.readString("Enter comments (if any): ", "").orElse("")));

    public static Function<View, ViewWriter<IO>> display = view ->
            (headers, content) -> io ->
                    io.print(content.get().count() == 0 ?
                            "No appointments found\n" :
                            view.apply(headers, content));

}
