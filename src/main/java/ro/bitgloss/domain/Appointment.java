package ro.bitgloss.domain;

import java.time.LocalDate;
import java.time.Period;

public record Appointment(LocalDate date, String doctor, String patient, String comments) {
    public boolean isExpired() {
        return date.isBefore(LocalDate.now()) && Period.between(date, LocalDate.now()).getMonths() > 6;
    }
}