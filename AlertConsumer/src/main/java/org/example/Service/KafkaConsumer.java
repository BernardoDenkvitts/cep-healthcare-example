package org.example.Service;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.example.Dto.Alert;
import org.example.Repository.InMemoryRepository;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class KafkaConsumer {
    private final Consumer<Integer, Alert> kafkaConsumer;
    private final String TOPIC = "alert-topic";
    private final InMemoryRepository repository;
    private final AtomicBoolean running = new AtomicBoolean(true);


    public KafkaConsumer(Consumer<Integer, Alert> kafkaConsumer, InMemoryRepository repository) {
        this.kafkaConsumer = kafkaConsumer;
        this.repository = repository;
        kafkaConsumer.subscribe(List.of(TOPIC));
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));

    }

    public void consume() {
        try {
            while (running.get()) {
                ConsumerRecords<Integer, Alert> records = kafkaConsumer.poll(Duration.of(1000, ChronoUnit.SECONDS));

                for (ConsumerRecord<Integer, Alert> record : records) {
                    repository.save(record.value());
                    System.out.println("NEW ALERT RECEIVED -> " + record.value().toString());
                }

                kafkaConsumer.commitAsync();
            }
        } catch (Exception e) {
            System.err.println("Error in consumer: " + e.getMessage());
        } finally {
            shutdown();
        }
    }

    private void shutdown() {
        running.set(false);
        try {
            kafkaConsumer.commitSync();
        } catch (Exception e) {
            System.out.println("Error during final commit" + e.getMessage());
        } finally {
            kafkaConsumer.close();
        }
    }
}
