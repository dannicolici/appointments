package ro.bitgloss.managers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.domain.Appointment;

public class AppointmentManager {

  private static DateFormat DF = new SimpleDateFormat("dd/MM/yyyy");
  private AppointmentDAO dao;

  public AppointmentManager(AppointmentDAO dao) {
    this.dao = dao;
  }

  public void printAppointments() {
    List<Appointment> appointments = dao.getAllAppointments();
    if (appointments != null) {
      displayLine("---------------------------------------------------------------------------------");
      displayLine("|          TIME          |          DOCTOR          |          PATIENT          |");
      displayLine("---------------------------------------------------------------------------------");
      for (Appointment a : appointments) {
        Date date = a.getDate();
        long time = date != null ? date.getTime() : new Date(1970, 1, 1).getTime();
        // appointments older than 6 months are marked as EXPIRED    
        if ((System.currentTimeMillis() - time) / 1000 > 3600 * 24 * 30 * 6) {
          display("          ");
          display("EXPIRED   ");
          display("          ");
        } else {
          display("          ");
          display(DF.format(a.getDate()));
          display("          ");
        }
        display("          ");
        display(a.getDoctor());
        display("          ");
        display("          ");
        display(a.getPatient());
        display("          ");
        displayLine("");
      }
      displayLine("---------------------------------------------------------------------------------");
    } else {
      displayLine("No appointments found");
    }
  }

  private void display(Object o) {
    System.out.print(o);
  }

  private void displayLine(Object o) {
    display(o + "\n");
  }

  public void inputNewAppointment() {
    Appointment appointment = new Appointment();
    appointment.setId(System.currentTimeMillis());

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try {
      display("Enter time: ");
      String date = br.readLine();
      try {
        appointment.setDate(DF.parse(date));
      } catch (ParseException e) {
        displayLine("invalid date");
      }

      display("Enter doctor: ");
      String doctor = br.readLine();
      appointment.setDoctor(doctor);

      display("Enter patient: ");
      String patient = br.readLine();
      appointment.setPatient(patient);
    } catch (IOException e) {
      // do nothing
    }

    dao.saveAppointment(appointment);
  }
}
