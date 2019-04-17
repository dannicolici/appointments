package ro.bitgloss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.managers.AppointmentManager;
import ro.bitgloss.view.ListView;
import ro.bitgloss.view.TabularView;

public class Main {
  private static ListView listView = new ListView();
  private static TabularView tabularView = new TabularView();

  public static void main(String[] args) throws IOException {
    AppointmentManager appointmentManager = new AppointmentManager(new AppointmentDAO());
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      System.out.println("Menu\nl - list view\nt - tabular view\na - add new appointment\nx - exit");
      String choice = br.readLine();
      if (choice.equals("x")) break;
      switch (choice) {
        case "l":
          appointmentManager.printAppointments(listView); break;
        case "t":
          appointmentManager.printAppointments(tabularView); break;
        case "a": appointmentManager.inputNewAppointment(); break;
        default: System.out.println("Invalid choice");
      }
    }
  }

}
