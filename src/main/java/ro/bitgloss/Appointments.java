package ro.bitgloss;

import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.data.DataSource;
import ro.bitgloss.domain.Appointment;
import io.IO;
import io.TypedIO;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Appointments {

    public static BiFunction<AppointmentDAO, TypedIO, IO> addNew() {
        return (dao, io) -> {
            var appointment = new Appointment();
            io.readDate("Enter time: ", "invalid date", appointment::setDate);
            io.readString("Enter doctor: ", "", appointment::setDoctor);
            io.readString("Enter patient: ", "", appointment::setPatient);
            io.readString("Enter comments (if any): ", "", appointment::setComments);

            dao.saveAppointment(appointment);

            return io;
        };
    }

    public static BiFunction<AppointmentDAO, IO, IO> display(Function<DataSource, String> view) {
        return (dao, io) -> {
            io.print(dao.appointmentsCount() == 0 ?
                    "No appointments found\n" :
                    view.apply(dao));
            return io;
        };
    }
}
