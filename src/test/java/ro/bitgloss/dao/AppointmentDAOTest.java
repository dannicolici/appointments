package ro.bitgloss.dao;

import org.junit.jupiter.api.Test;
import ro.bitgloss.domain.Appointment;

import java.time.LocalDate;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppointmentsDataSourceTest {

    @Test
    public void comment_headers_are_displayed() {
        assertEquals(asList("TIME", "DOCTOR", "PATIENT", "COMMENTS"), AppointmentDAO.HEADERS);
    }

    @Test
    public void comments_are_shown_in_details() {
        AppointmentDAO.save.accept(new Appointment(
                LocalDate.now(),"D", "P","comments"));

        assertTrue(AppointmentDAO.content.get().anyMatch(l -> l.contains("comments")));
    }

}