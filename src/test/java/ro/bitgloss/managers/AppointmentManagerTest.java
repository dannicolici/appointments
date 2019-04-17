package ro.bitgloss.managers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.domain.Appointment;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AppointmentManagerTest {

  private AppointmentDAO dao;
  private AppointmentManager appointmentManager;
  private PrintStream out;

  @BeforeEach
  public void setUp() {
    dao = new AppointmentDAO();
    dao.deleteAllAppointments();
    appointmentManager = new AppointmentManager(dao);
    out = mock(PrintStream.class);
    System.setOut(out);
  }

  @Test
  public void testPrintAppointments() {
    Appointment appointment = new Appointment();
    appointment.setDate(new Date(2019 - 1900, 11, 2));
    appointment.setDoctor("doctor");
    appointment.setPatient("patient");
    dao.saveAppointment(appointment);
    appointmentManager.printAppointments();
    ArgumentCaptor<Object> argumentCaptor = ArgumentCaptor.forClass(Object.class);
    verify(out, times(14)).print(argumentCaptor.capture());
    List<String> printout = Arrays.asList(
          "---------------------------------------------------------------------------------\n"
        , "|          TIME          |          DOCTOR          |          PATIENT          |\n"
        , "---------------------------------------------------------------------------------\n"
        , "          ","02/12/2019","          ","          ","doctor","          ","          ","patient","          ","\n"
        , "---------------------------------------------------------------------------------\n");
    assertEquals(printout, argumentCaptor.getAllValues());
  }
  
  @Test
  public void testInputNewAppointment() {
    String data = "20/10/2018\ndoctor\npatient\n";
    ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
    System.setIn(in);
    appointmentManager.inputNewAppointment();
    assertEquals(1, dao.getAllAppointments().size());
    Appointment appointment = dao.getAllAppointments().get(0);
    assertEquals(new Date(2018 - 1900, 9, 20), appointment.getDate());
    assertEquals("doctor", appointment.getDoctor());
    assertEquals("patient", appointment.getPatient());
  }

}