package ro.bitgloss;

import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.io.Console;
import ro.bitgloss.view.ListView;
import ro.bitgloss.view.TabularView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {
  private static final String MENU = "Menu\n" +
          "l - list view\n" +
          "t - tabular view\n" +
          "a - add new appointment\n" +
          "x - exit";

  private static Map<Character, Runnable>
          FUNCTION_TABLE = new HashMap<Character, Runnable>() {
    {
      put('l', () -> Application.displayAppointments(dao, listView, console));
      put('t', () -> Application.displayAppointments(dao, tabularView, console));
      put('a', () -> Application.createNewAppointment(dao, console));
    }
  };

  private static Console console = Console.getInstance();
  private static ListView listView = new ListView();
  private static TabularView tabularView = new TabularView();
  private static final AppointmentDAO dao = new AppointmentDAO();


  public static void main(String[] args) {
    for (char choice = console.choice(MENU); choice != 'x'; choice = console.choice(MENU)) {
      Optional.ofNullable(FUNCTION_TABLE.get(choice))
              .orElse(() -> console.printLine("Invalid choice"))
              .run();
    }
  }
}
