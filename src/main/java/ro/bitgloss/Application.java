package ro.bitgloss;

import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.io.Console;
import ro.bitgloss.io.IO;
import ro.bitgloss.view.ListView;
import ro.bitgloss.view.TabularView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

public class Application {
    private static final String MENU = "Menu\n" +
    "l - list view\n" +
    "t - tabular view\n" +
    "a - add new appointment\n" +
    "x - exit";

    private static final Console CONSOLE = Console.getInstance();
    private static final ListView LIST_VIEW = new ListView();
    private static final TabularView TABULAR_VIEW = new TabularView();
    private static final AppointmentDAO DAO = new AppointmentDAO();

    private static final Map<Character, BiFunction<AppointmentDAO, ? super Console, IO>>
    FUNCTION_TABLE = new HashMap<>() {
        {
            put('l', Appointments.display(LIST_VIEW));
            put('t', Appointments.display(TABULAR_VIEW));
            put('a', Appointments.addNew());
            put('x', (__, ___) -> { System.exit(0); return null; });
        }
    };

    public static void main(String[] args) {
        Optional.ofNullable(FUNCTION_TABLE.get(CONSOLE.choice(MENU)))
                .ifPresentOrElse(
                        f -> f.apply(DAO, CONSOLE),
                        () -> CONSOLE.printLine("Invalid choice"));
        main(args);
    }
}
