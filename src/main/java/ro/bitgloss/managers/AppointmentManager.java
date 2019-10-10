package ro.bitgloss.managers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.domain.Appointment;
import ro.bitgloss.io.Console;

public class AppointmentManager {

  private AppointmentDAO dao;

  public AppointmentManager(AppointmentDAO dao) {
    this.dao = dao;
  }

  public void inputNewAppointment() {
    Appointment appointment = new Appointment();
    appointment.setId(System.currentTimeMillis());

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try {
      Console.print("Enter time: ");
      String date = br.readLine();
      try {
        appointment.setDate(Appointment.DF.parse(date));
      } catch (ParseException e) {
        Console.printLine("invalid date");
      }

      Console.print("Enter doctor: ");
      String doctor = br.readLine();
      appointment.setDoctor(doctor);

      Console.print("Enter patient: ");
      String patient = br.readLine();
      appointment.setPatient(patient);
    } catch (IOException e) {
      // do nothing
    }

    dao.saveAppointment(appointment);
  }
}
