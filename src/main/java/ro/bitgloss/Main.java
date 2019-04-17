package ro.bitgloss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.managers.AppointmentManager;

public class Main {

  public static void main(String[] args) throws IOException, ParseException {
    AppointmentManager appointmentManager = new AppointmentManager(new AppointmentDAO());
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      System.out.println("Existing appointments:");
      appointmentManager.printAppointments();

      System.out.println("Input new appointment? (y/n)");
      if (br.readLine().equals("n")) break;
      appointmentManager.inputNewAppointment();
    }
  }

}
