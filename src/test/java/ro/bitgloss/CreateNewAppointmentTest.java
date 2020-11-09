package ro.bitgloss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.bitgloss.dao.AppointmentDAO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateNewAppointmentTest {

  private TestIO io;

  @BeforeEach
  public void setUp() {
    AppointmentDAO.deleteAllAppointments();
    io = new TestIO();
  }
  
  @Test
  public void input_new_valid_appointment() {
    io.readBuffer.offer("20/10/2018");
    io.readBuffer.offer("doctor");
    io.readBuffer.offer("patient");
    io.readBuffer.offer("comments");

    Appointments.addNew.apply(AppointmentDAO.save).accept(io);

    assertEquals(1, AppointmentDAO.appointmentsCount());
    var appointment = AppointmentDAO.findByIndex(0);
    assertEquals(LocalDate.of(2018, 10, 20), appointment.date());
    assertEquals("doctor", appointment.doctor());
    assertEquals("patient", appointment.patient());
    assertEquals("comments", appointment.comments());
  }

  @Test
  public void input_invalid_date_on_new_appointment() {
    io.readBuffer.offer("xyz");
    io.readBuffer.offer("20/10/2018");
    io.readBuffer.offer("doctor");
    io.readBuffer.offer("patient");
    io.readBuffer.offer("comments");

    Appointments.addNew.apply(AppointmentDAO.save).accept(io);

    assertEquals(1, AppointmentDAO.appointmentsCount());
    var appointment = AppointmentDAO.findByIndex(0);
    assertEquals(LocalDate.of(2018, 10, 20), appointment.date());
    assertEquals("doctor", appointment.doctor());
    assertEquals("patient", appointment.patient());
    assertEquals("comments", appointment.comments());
  }

}