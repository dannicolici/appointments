package ro.bitgloss;

import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.domain.Appointment;
import ro.bitgloss.io.IO;
import ro.bitgloss.io.TypedIO;
import ro.bitgloss.view.View;

import java.util.function.BiFunction;

public class Appointments {

    public static BiFunction<AppointmentDAO, TypedIO, IO> createNew() {
        return (dao, io) -> {
            var appointment = new Appointment();
            io.readDate("Enter time: ", "invalid date", appointment::setDate);
            io.readString("Enter doctor: ", "", appointment::setDoctor);
            io.readString("Enter patient: ", "", appointment::setPatient);

            dao.saveAppointment(appointment);

            return io;
        };
    }

    public static BiFunction<AppointmentDAO, IO, IO> display(View view) {
        return (dao, io) -> {
            if (dao.appointmentsCount() > 0)
                io.print(view.display(dao));
            else
                io.printLine("No appointments found");
            return io;
        };
    }
}