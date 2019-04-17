package ro.bitgloss.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ro.bitgloss.domain.Appointment;

public class AppointmentDAO {
  
  private static List<Appointment> DB = new ArrayList<>();

  public List<Appointment> getAllAppointments() {
    return Collections.unmodifiableList(DB);
  }

  public void saveAppointment(Appointment appointment) {
    DB.add(appointment);
  }
  
  public void deleteAllAppointments() {
    DB.clear();
  }
}
