package ro.bitgloss;

import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.domain.Appointment;
import ro.bitgloss.io.IO;
import ro.bitgloss.io.TypedIO;
import ro.bitgloss.view.View;

public class Application {

    public static void createNewAppointment(AppointmentDAO dao, TypedIO io) {
      Appointment appointment = new Appointment();
      appointment.setDate(
              io.readDate("Enter time: ","invalid date"));
      io.readString("Enter doctor: ", "")
              .ifPresent(appointment::setDoctor);
      io.readString("Enter patient: ", "")
              .ifPresent(appointment::setPatient);
      dao.saveAppointment(appointment);
    }

    public static void displayAppointments(AppointmentDAO dao, View view, IO io) {
      if (dao.appointmentsCount() > 0)
        io.print(view.display(dao));
      else
        io.printLine("No appointments found");
    }
}
