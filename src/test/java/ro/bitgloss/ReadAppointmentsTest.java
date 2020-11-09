package ro.bitgloss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.bitgloss.view.ListView;

import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReadAppointmentsTest {

    private TestIO io;

    @BeforeEach
    public void setUp() {
        io = new TestIO();
    }

    @Test
    public void no_appointments() {
        var writer = Appointments.display
                .apply(ListView.listFormat)
                .apply(singletonList("header"), Stream::empty);

        writer.accept(io);

        assertTrue(io.printBuffer.contains("No appointments found\n"));
    }

}