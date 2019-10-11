package ro.bitgloss.dao;

import ro.bitgloss.data.DataSource;
import ro.bitgloss.domain.Appointment;
import ro.bitgloss.io.TypedIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class AppointmentDAO implements DataSource {

  private static List<Appointment> DB = new ArrayList<>();

  public int appointmentsCount() {
    return DB.size();
  }

  public Appointment findByIndex(int index) {
    return DB.get(index);
  }

  public void saveAppointment(Appointment appointment) {
    DB.add(appointment);
  }

  public void deleteAllAppointments() {
    DB.clear();
  }

  @Override
  public List<String> entryDetails() {
    return Arrays.asList("TIME", "DOCTOR", "PATIENT");
  }

  @Override
  public Stream<List<String>> stream() {
    return DB.stream()
            .map(a ->
                    Arrays.asList(
                            a.isExpired() ? "EXPIRED" : TypedIO.DF.format(a.getDate()),
                            a.getDoctor(),
                            a.getPatient()));
  }
}
