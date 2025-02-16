package org.example;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

public class Data implements Serializable {
    Integer sensorId;
    Integer patientId;
    Type type;
    Double valueOne;
    Double valueTwo;
    Date timestamp;

    public Data() {
        this.sensorId = (int) ((Math.random() * (100 - 1 + 1)) + 1);
    }

    public Data(Integer patientId, Type type, Double valueOne, Double valueTwo, Date timestamp) {
        this.sensorId = (int) ((Math.random() * (100 - 1 + 1)) + 1);
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

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setValueOne(Double valueOne) {
        this.valueOne = valueOne;
    }

    public void setValueTwo(Double valueTwo) {
        this.valueTwo = valueTwo;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
