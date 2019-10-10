package ro.bitgloss;

import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.io.Console;
import ro.bitgloss.managers.AppointmentManager;
import ro.bitgloss.view.ListView;
import ro.bitgloss.view.TabularView;
import ro.bitgloss.view.View;

public class Main {
  private static final String MENU = "Menu\n" +
          "l - list view\n" +
          "t - tabular view\n" +
          "a - add new appointment\n" +
          "x - exit";

  private static ListView listView = new ListView();
  private static TabularView tabularView = new TabularView();
  private static final AppointmentDAO dao = new AppointmentDAO();
  private static final AppointmentManager appointmentManager = new AppointmentManager(dao);

  public static void main(String[] args) {
    for (char choice = Console.choice(MENU); choice != 'x'; choice = Console.choice(MENU)) {
      switch (choice) {
        case 'l':
          display(listView);
          break;
        case 't':
          display(tabularView);
          break;
        case 'a':
          appointmentManager.inputNewAppointment();
          break;
        default: Console.printLine("Invalid choice");
      }
    }
  }

  private static void display(View view) {
    if (dao.appointmentsCount() > 0)
      Console.print(view.display(dao));
    else
      Console.printLine("No appointments found");
  }

}
