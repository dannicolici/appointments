package ro.bitgloss.dao;

import io.TypedIO;
import ro.bitgloss.domain.Appointment;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class AppointmentDAO {

  private static List<Appointment> DB = Collections.synchronizedList(new ArrayList<>());
  public static final Collection<String> HEADERS = Arrays.asList("TIME", "DOCTOR", "PATIENT", "COMMENTS");

  public static int appointmentsCount() {
    return DB.size();
  }

  public static Appointment findByIndex(int index) {
    return DB.get(index);
  }

  public static Consumer<Appointment> save = appointment -> DB.add(appointment);

  public static void deleteAllAppointments() {
    DB.clear();
  }

  public static Supplier<Stream<Collection<String>>> content = () ->
     DB.stream()
            .map(a ->
                    Arrays.asList(
                            a.isExpired() ? "EXPIRED" : a.date().format(TypedIO.DF),
                            a.doctor(),
                            a.patient(),
                            a.comments()));
}
