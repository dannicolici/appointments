package ro.bitgloss;

import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.domain.Appointment;
import ro.bitgloss.io.Console;
import ro.bitgloss.view.View;

public class Application {

    public static void createNewAppointment(AppointmentDAO dao) {
      Appointment appointment = new Appointment();
      Console.readString("Enter time: ","invalid date")
             .ifPresent(appointment::setDate);
      Console.readString("Enter doctor: ", "")
              .ifPresent(appointment::setDoctor);
      Console.readString("Enter patient: ", "")
              .ifPresent(appointment::setPatient);
      dao.saveAppointment(appointment);
    }

    public static void displayAppointments(AppointmentDAO dao, View view) {
      if (dao.appointmentsCount() > 0)
        Console.print(view.display(dao));
      else
        Console.printLine("No appointments found");
    }
}
