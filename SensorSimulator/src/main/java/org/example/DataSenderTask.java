package org.example;

import java.net.Socket;

public class DataSenderTask implements Runnable {
    private final int patientId;

    public DataSenderTask(int patientId) {
        this.patientId = patientId;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket("127.0.0.1", 5555)) {
            TcpSender tcpSender = new TcpSender(socket);
            DataGenerator generator = new DataGenerator();
            int amountOfDataPerSecond = 200;

            while (true) {
                for (int i = 0; i < amountOfDataPerSecond; i++) {
                    Data random = generator.generate(patientId);
                    tcpSender.send(random);
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.err.println("Failed to connect");
        }
    }
}
