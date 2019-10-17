package ro.bitgloss.domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Appointment {

    private long id;
    private LocalDate date;
    private String doctor;
    private String patient;
    private List<String> comments;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isExpired() {
        return date.isBefore(LocalDate.now()) && Period.between(date, LocalDate.now()).getMonths() > 6;
    }
}