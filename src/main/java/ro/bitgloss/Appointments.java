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
                    content.get().findAny().ifPresentOrElse(
                            data -> io.print(view.apply(headers, () -> Stream.of(data))),
                            () -> io.print("No appointments found\n"));

}
