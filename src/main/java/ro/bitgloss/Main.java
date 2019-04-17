package ro.bitgloss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.managers.AppointmentManager;

public class Main {

  public static void main(String[] args) throws IOException {
    AppointmentManager appointmentManager = new AppointmentManager(new AppointmentDAO());
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      System.out.println("Existing appointments:");
      appointmentManager.printAppointments();

      System.out.println("Menu\nl - list view\nt - tabular view\na - add new appointment\nx - exit");
      String choice = br.readLine();
      if (choice.equals("x")) break;
      switch (choice) {
        case "l": appointmentManager.printAppointments(); break;
        case "t": appointmentManager.printAppointments(); break;
        case "a": appointmentManager.inputNewAppointment(); break;
        default: System.out.println("Invalid choice");
      }
    }
  }

}
