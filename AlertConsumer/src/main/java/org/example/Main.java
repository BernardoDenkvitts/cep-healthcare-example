package org.example;

import org.example.Config.KafkaConfig;
import org.example.Repository.InMemoryRepository;
import org.example.Service.KafkaConsumer;

public class Main {
    public static void main(String[] args) {

        KafkaConfig kafkaConfig = new KafkaConfig();
        InMemoryRepository repository = new InMemoryRepository();
        KafkaConsumer consumer = new KafkaConsumer(kafkaConfig.getConsumerConfig(), repository);

        System.out.println("Starting Consumer");
        consumer.consume();
    }
}