package org.example.Dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;


public class AlertDeserializer implements Deserializer<Alert> {

    private final ObjectMapper mapper = new ObjectMapper();

    public AlertDeserializer() {}

    @Override
    public Alert deserialize(String s, byte[] bytes) {
        try {
            return mapper.readValue(bytes, Alert.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize alert", e);
        }
    }
}
