package ro.bitgloss.managers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.data.DataSource;
import ro.bitgloss.domain.Appointment;
import ro.bitgloss.view.TabularView;
import ro.bitgloss.view.View;

public class AppointmentManager {

  private static DateFormat DF = new SimpleDateFormat("dd/MM/yyyy");
  private AppointmentDAO dao;

  public AppointmentManager(AppointmentDAO dao) {
    this.dao = dao;
  }

  public void printAppointments() {
    List<Appointment> appointments = dao.getAllAppointments();
    if (appointments != null) {
      View view = new TabularView();      
      display(view.display(createDataSource(appointments)));
    } else {
      displayLine("No appointments found");
    }
  }

  private DataSource createDataSource(List<Appointment> appointments) {
    return new DataSource() {
      @Override
      public List<String> entryDetails() {
        return Arrays.asList("TIME", "DOCTOR", "PATIENT");
      }

      @Override
      public Stream<List<String>> stream() {
        return appointments.stream()
            .map(a -> 
              Arrays.asList(
                  isExpired(a.getDate()) ? "EXPIRED" : DF.format(a.getDate()),
                  a.getDoctor(),
                  a.getPatient()));
      }
    };
  }

  private boolean isExpired(Date date) {
    long time = date != null ? date.getTime() : new Date(1970, 1, 1).getTime();
    return (System.currentTimeMillis() - time) / 1000 > 3600 * 24 * 30 * 6;
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
