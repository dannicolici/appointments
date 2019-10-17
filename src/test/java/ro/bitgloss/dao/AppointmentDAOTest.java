package ro.bitgloss.dao;

import org.junit.jupiter.api.Test;
import ro.bitgloss.domain.Appointment;

import java.time.LocalDate;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppointmentsDataSourceTest {

    private final AppointmentDAO dao = new AppointmentDAO();

    @Test
    public void comment_headers_are_displayed() {
        assertEquals(asList("TIME", "DOCTOR", "PATIENT", "COMMENTS"), dao.entryDetails());
    }

    @Test
    public void comments_are_shown_in_details() {
        dao.saveAppointment(new Appointment()
                .withDate(LocalDate.now())
                .withDoctor("D")
                .withPatient("P")
                .withComments("comments"));

        assertTrue(dao.stream().anyMatch(l -> l.contains("comments")));
    }

}