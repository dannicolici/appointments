package ro.bitgloss;

import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.domain.Appointment;
import io.IO;
import io.TypedIO;
import ro.bitgloss.view.View;

import java.util.function.BiFunction;

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

    public static BiFunction<AppointmentDAO, IO, IO> display(View view) {
        return (dao, io) -> {
            switch (dao.appointmentsCount()) {
                case 0  -> io.printLine("No appointments found");
                default -> io.print(view.display(dao));
            }
            return io;
        };
    }
}
