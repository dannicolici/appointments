package ro.bitgloss.domain;

import java.time.LocalDate;
import java.time.Period;

public class Appointment {

    private long id;
    private LocalDate date;
    private String doctor;
    private String patient;
    private String comments;

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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
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

    public Appointment withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Appointment withDoctor(String doctor) {
        this.doctor = doctor;
        return this;
    }

    public Appointment withPatient(String patient) {
        this.patient = patient;
        return this;
    }

    public Appointment withComments(String comments) {
        this.comments = comments;
        return this;
    }
}