package ro.bitgloss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.data.DataSource;
import ro.bitgloss.io.Console;
import ro.bitgloss.managers.AppointmentManager;
import ro.bitgloss.view.ListView;
import ro.bitgloss.view.TabularView;
import ro.bitgloss.view.View;

public class Main {
  private static ListView listView = new ListView();
  private static TabularView tabularView = new TabularView();
  private static final AppointmentDAO dao = new AppointmentDAO();
  private static final AppointmentManager appointmentManager = new AppointmentManager(dao);

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      Console.printLine("Menu\nl - list view\nt - tabular view\na - add new appointment\nx - exit");
      String choice = br.readLine();
      if (choice.equals("x"))
        break;

      switch (choice) {
        case "l":
          display(dao, listView);
          break;
        case "t":
          display(dao, tabularView);
          break;
        case "a":
          appointmentManager.inputNewAppointment();
          break;
        default: System.out.println("Invalid choice");
      }
    }
  }

  private static void display(AppointmentDAO dao, View view) {
    if (dao.appointmentsCount() > 0)
      Console.print(view.display(dao));
    else
      Console.printLine("No appointments found");
  }

}
