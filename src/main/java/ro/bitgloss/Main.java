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

public class Main {
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
            put('l', Application.displayAppointments(LIST_VIEW));
            put('t', Application.displayAppointments(TABULAR_VIEW));
            put('a', Application.createNewAppointment());
            put('x', (__, ___) -> { System.exit(0); return null; });
        }
    };

    private static final BiFunction<AppointmentDAO, ? super Console, IO> INVALID_CHOICE = (__, io) -> {
        io.printLine("Invalid choice"); return io;
    };

    public static void main(String[] args) {
        while (true) {
            Optional.ofNullable(FUNCTION_TABLE.get(CONSOLE.choice(MENU)))
                    .ifPresentOrElse(
                            f -> f.apply(DAO, CONSOLE),
                            () -> INVALID_CHOICE.apply(DAO, CONSOLE));
        }
    }
}
