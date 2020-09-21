package ro.bitgloss;

import io.IO;
import io.TypedIO;
import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.data.DataSource;
import ro.bitgloss.domain.Appointment;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class Appointments {

    public static BiConsumer<AppointmentDAO, TypedIO> addNew() {
        return (dao, io) -> {
            var appointment = new Appointment();
            io.readDate("Enter time: ", "invalid date", appointment::setDate);
            io.readString("Enter doctor: ", "", appointment::setDoctor);
            io.readString("Enter patient: ", "", appointment::setPatient);
            io.readString("Enter comments (if any): ", "", appointment::setComments);

            dao.saveAppointment(appointment);
        };
    }

    public static BiConsumer<AppointmentDAO, IO> display(Function<DataSource, String> view) {
        return (dao, io) ->
                io.print(dao.appointmentsCount() == 0 ? "No appointments found\n" : view.apply(dao));
    }
}
