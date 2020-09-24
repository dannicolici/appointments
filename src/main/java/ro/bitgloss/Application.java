package ro.bitgloss;

import io.Console;
import ro.bitgloss.dao.AppointmentDAO;
import ro.bitgloss.view.ListView;
import ro.bitgloss.view.TabularView;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import static java.util.Optional.ofNullable;
import static ro.bitgloss.Appointments.*;

public class Application {
    private static final String MENU = """
            Menu
            l - list view
            t - tabular view
            a - add new appointment
            x - exit
            """;

    private static final AppointmentDAO DAO = new AppointmentDAO();

    private static final Map<Character, BiConsumer<AppointmentDAO, ? super Console>>
            FUNCTION_TABLE = new HashMap<>() {
        {
            put('l', display.apply(ListView.format));
            put('t', display.apply(TabularView.format));
            put('a', addNew);
            put('x', (_1, _2) -> System.exit(0));
        }
    };

    public static void main(String[] args) {
        Console.getInstance().choice(MENU).flatMap(
                c -> ofNullable(FUNCTION_TABLE.get(c)))
                .ifPresent(f -> f.accept(DAO, Console.getInstance()));
        main(args);
    }
}
