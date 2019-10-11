package ro.bitgloss.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Appointment {

  public static DateFormat DF = new SimpleDateFormat("dd/MM/yyyy");

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

  public String getFormattedDateString() {
    return DF.format(date);
  }

  public void setDate(String dateString) {
    try {
      date = DF.parse(dateString);
    } catch (ParseException e) {
      // don't set invalid date
    }
  }
}
