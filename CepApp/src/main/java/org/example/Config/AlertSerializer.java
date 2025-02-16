package org.example.Config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.kafka.common.serialization.Serializer;
import org.example.Dto.Alert;

public class AlertSerializer implements Serializer<Alert> {
    private final ObjectMapper mapper = new ObjectMapper();

    public AlertSerializer() {
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public byte[] serialize(String topic, Alert alert) {
        try {
            return mapper.writeValueAsBytes(alert);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing alert", e);
        }
    }
}
