package ro.bitgloss.domain;

import ro.bitgloss.io.TypedIO;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Appointment {

  private long id;
  private Date date;
  private String doctor;
  private String patient;
  private List<String> comments;

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
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
    long time = date != null ? date.getTime() : new Date(1970, 1, 1).getTime();
    return (System.currentTimeMillis() - time) / 1000 > 3600 * 24 * 30 * 6;
  }
}
