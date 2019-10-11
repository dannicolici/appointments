package ro.bitgloss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.bitgloss.Application;
import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.domain.Appointment;

import java.io.ByteArrayInputStream;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadAppointmentTest {

  private AppointmentDAO dao;

  @BeforeEach
  public void setUp() {
    dao = new AppointmentDAO();
    dao.deleteAllAppointments();
  }
  
  @Test
  public void testInputNewAppointment() {
    String data = "20/10/2018\ndoctor\npatient\n";
    ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
    System.setIn(in);
    Application.createNewAppointment(dao);
    assertEquals(1, dao.appointmentsCount());
    Appointment appointment = dao.findByIndex(0);
    assertEquals(new Date(2018 - 1900, 9, 20), appointment.getDate());
    assertEquals("doctor", appointment.getDoctor());
    assertEquals("patient", appointment.getPatient());
  }

}