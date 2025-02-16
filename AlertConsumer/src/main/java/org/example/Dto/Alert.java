package org.example.Dto;

public class Alert {

    private Integer patientId;
    private String message;
    private String date;

    public Alert() {}

    public Alert(Integer patientId, String message, String date) {
        this.patientId = patientId;
        this.message = message;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "patientId=" + patientId +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
