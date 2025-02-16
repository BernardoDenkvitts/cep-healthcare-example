package org.example;

import java.time.Instant;
import java.util.Date;
import java.util.Random;

public class DataGenerator {

    private final Random random;

    public DataGenerator() {
        this.random = new Random();
    }

    public Data generate(int patientId) {
        Type[] types = Type.values();
        Type randomType = types[this.random.nextInt(types.length)];

        double valueOne;
        double valueTwo;

        Data data = new Data();
        data.setType(randomType);
        data.setPatientId(patientId);
        data.setTimestamp(Date.from(Instant.now()));

        switch (randomType) {
            case TEMPERATURE:
                valueOne = 30.0 + (random.nextDouble() * 10.0);
                data.setValueOne(valueOne);
                return data;
            case SPO2:
                valueOne = 0.0 + (random.nextDouble() * 100.0);
                valueTwo = 0.0 + (random.nextDouble() * 200.0);
                data.setValueOne(valueOne);
                data.setValueTwo(valueTwo);
                return data;
            case BLOOD_PRESSURE:
                valueOne = 0.0 + (random.nextDouble() * 300.0);
                valueTwo = 0.0 + (random.nextDouble() * 300.0);
                data.setValueOne(valueOne);
                data.setValueTwo(valueTwo);
                return data;
        }

        return null;
    }

}
