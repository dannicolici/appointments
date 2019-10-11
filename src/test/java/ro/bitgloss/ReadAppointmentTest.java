package ro.bitgloss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.domain.Appointment;
import ro.bitgloss.io.IO;
import ro.bitgloss.io.TypedIO;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadAppointmentTest {

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
  public void testInputNewAppointment() {
    io.readBuffer.offer("20/10/2018");
    io.readBuffer.offer("doctor");
    io.readBuffer.offer("patient");

    Application.createNewAppointment(dao, io);

    assertEquals(1, dao.appointmentsCount());
    Appointment appointment = dao.findByIndex(0);
    assertEquals(new Date(2018 - 1900, 9, 20), appointment.getDate());
    assertEquals("doctor", appointment.getDoctor());
    assertEquals("patient", appointment.getPatient());
  }

  @Test
  public void testInputInvalidDateOnNewAppointment() {
    io.readBuffer.offer("xyz");
    io.readBuffer.offer("20/10/2018");
    io.readBuffer.offer("doctor");
    io.readBuffer.offer("patient");

    Application.createNewAppointment(dao, io);

    assertEquals(1, dao.appointmentsCount());
    Appointment appointment = dao.findByIndex(0);
    assertEquals(new Date(2018 - 1900, 9, 20), appointment.getDate());
    assertEquals("doctor", appointment.getDoctor());
    assertEquals("patient", appointment.getPatient());
  }

}