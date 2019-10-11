package ro.bitgloss;

import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.io.Console;
import ro.bitgloss.view.ListView;
import ro.bitgloss.view.TabularView;

public class Main {
  private static final String MENU = "Menu\n" +
          "l - list view\n" +
          "t - tabular view\n" +
          "a - add new appointment\n" +
          "x - exit";

  private static ListView listView = new ListView();
  private static TabularView tabularView = new TabularView();
  private static final AppointmentDAO dao = new AppointmentDAO();

  public static void main(String[] args) {
    for (char choice = Console.choice(MENU); choice != 'x'; choice = Console.choice(MENU)) {
      switch (choice) {
        case 'l':
          Application.displayAppointments(dao, listView);
          break;
        case 't':
          Application.displayAppointments(dao, tabularView);
          break;
        case 'a':
          Application.createNewAppointment(dao);
          break;
        default: Console.printLine("Invalid choice");
      }
    }
  }
}
