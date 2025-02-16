package org.example;

import org.example.Config.DroolsConfig;
import org.example.Config.KafkaConfig;
import org.example.Service.DataProcessorService;
import org.example.Service.DroolsService;
import org.example.Service.KafkaProducer;
import org.kie.api.runtime.KieSession;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        KafkaConfig kafkaConfig = new KafkaConfig();
        KafkaProducer producer = new KafkaProducer(kafkaConfig.getProducerConfig());

        DroolsConfig config = new DroolsConfig(producer);

        DroolsService droolsService = new DroolsService(config.getKieSession());

        DataProcessorService dataProcessorService = new DataProcessorService(droolsService);

        TCPServer server = new TCPServer(5555, dataProcessorService);
        server.start();
    }


}