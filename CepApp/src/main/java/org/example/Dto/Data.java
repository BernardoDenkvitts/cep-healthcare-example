package org.example.Dto;

import java.util.Date;

public class Data {

    private Integer sensorId;
    private Integer patientId;
    private Type type;
    private Double valueOne;
    private Double valueTwo;
    private Date timestamp;

    public Data() {}

    public Data(Integer sensorId, Integer patientId, Type type, Double valueOne, Double valueTwo, Date timestamp) {
        this.sensorId = sensorId;
        this.patientId = patientId;
        this.type = type;
        this.valueOne = valueOne;
        this.valueTwo = valueTwo;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Data{" +
                "sensorId=" + sensorId +
                ", patientId=" + patientId +
                ", type=" + type +
                ", valueOne=" + valueOne +
                ", valueTwo=" + valueTwo +
                ", timestamp=" + timestamp +
                '}';
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public Type getType() {
        return type;
    }

    public Double getValueOne() {
        return valueOne;
    }

    public Double getValueTwo() {
        return valueTwo;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
