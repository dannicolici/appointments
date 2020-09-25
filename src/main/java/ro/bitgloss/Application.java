package ro.bitgloss;

import io.Console;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static java.util.Optional.ofNullable;
import static ro.bitgloss.Appointments.addNew;
import static ro.bitgloss.Appointments.display;
import static ro.bitgloss.dao.AppointmentDAO.*;
import static ro.bitgloss.view.ListView.listFormat;
import static ro.bitgloss.view.TabularView.tabularFormat;

public class Application {

    private final static Console CONSOLE = Console.getInstance();

    private static final String MENU = """
            Menu
            l - list view
            t - tabular view
            a - add new appointment
            x - exit
            """;

    private static final Map<Character, Consumer<? super Console>>
            CHOICE_TO_FUNCTION = new HashMap<>() {
        {
            put('l', display.apply(listFormat).apply(HEADERS, content));
            put('t', display.apply(tabularFormat).apply(HEADERS, content));
            put('a', addNew.apply(save));
            put('x', (_ignore) -> System.exit(0));
        }
    };

    public static void main(String[] args) {
        CONSOLE.choice(MENU)
                .map(CHOICE_TO_FUNCTION::get)
                .ifPresent(f -> f.accept(CONSOLE));
        main(args);
    }
}
