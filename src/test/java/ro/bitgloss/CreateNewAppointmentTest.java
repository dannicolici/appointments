package ro.bitgloss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.bitgloss.dao.AppointmentDAO;
import io.TypedIO;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateNewAppointmentTest {

  private AppointmentDAO dao;
  private TestIO io;

  class TestIO implements TypedIO {

    List<String> printBuffer = new ArrayList<>();
    Queue<String> readBuffer = new LinkedList<>();

    @Override
    public void print(Object o) {
      printBuffer.add(o.toString());
    }

    @Override
    public void printLine(Object o) {
      printBuffer.add(o + "\n");
    }

    @Override
    public Optional<String> readString(String prompt, String errorMessage) {
      return Optional.of(readBuffer.remove());
    }
  }

  @BeforeEach
  public void setUp() {
    dao = new AppointmentDAO();
    dao.deleteAllAppointments();
    io = new TestIO();
  }
  
  @Test
  public void input_new_valid_appointment() {
    io.readBuffer.offer("20/10/2018");
    io.readBuffer.offer("doctor");
    io.readBuffer.offer("patient");
    io.readBuffer.offer("comments");

    Appointments.addNew.accept(dao, io);

    assertEquals(1, dao.appointmentsCount());
    var appointment = dao.findByIndex(0);
    assertEquals(LocalDate.of(2018, 10, 20), appointment.getDate());
    assertEquals("doctor", appointment.getDoctor());
    assertEquals("patient", appointment.getPatient());
    assertEquals("comments", appointment.getComments());
  }

  @Test
  public void input_invalid_date_on_new_appointment() {
    io.readBuffer.offer("xyz");
    io.readBuffer.offer("20/10/2018");
    io.readBuffer.offer("doctor");
    io.readBuffer.offer("patient");
    io.readBuffer.offer("comments");

    Appointments.addNew.accept(dao, io);

    assertEquals(1, dao.appointmentsCount());
    var appointment = dao.findByIndex(0);
    assertEquals(LocalDate.of(2018, 10, 20), appointment.getDate());
    assertEquals("doctor", appointment.getDoctor());
    assertEquals("patient", appointment.getPatient());
    assertEquals("comments", appointment.getComments());
  }

}