package org.example.Service;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaProducer {

    private final Producer<Integer, Object> kafkaProducer;
    private final String TOPIC = "alert-topic";

    public KafkaProducer(Producer<Integer, Object> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void sendMessage(Integer key, Object value) {
        ProducerRecord<Integer, Object> record = new ProducerRecord<>(TOPIC, key, value);

        // Send asynchronously
        kafkaProducer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception != null) {
                    System.err.println("Error sending message: " + exception.getMessage());
                }
            }
        });
    }
}
