package ro.bitgloss;

import io.IO;
import io.TypedIO;
import ro.bitgloss.domain.Appointment;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Appointments {

    public static Function<Consumer<Appointment>, Consumer<TypedIO>> addNew =
            dao ->
                    io -> {
                        var appointment = new Appointment();
                        io.readDate("Enter time: ", "invalid date", appointment::setDate);
                        io.readString("Enter doctor: ", "", appointment::setDoctor);
                        io.readString("Enter patient: ", "", appointment::setPatient);
                        io.readString("Enter comments (if any): ", "", appointment::setComments);

                        dao.accept(appointment);
                    };

    public static Function<
            BiFunction<List<String>, Supplier<Stream<List<String>>>, String>,
            BiFunction<List<String>, Supplier<Stream<List<String>>>, Consumer<IO>>> display =
            view ->
                    (headers, content) ->
                            io ->
                                    io.print(content.get().count() == 0 ?
                                            "No appointments found\n" :
                                            view.apply(headers, content));

}
