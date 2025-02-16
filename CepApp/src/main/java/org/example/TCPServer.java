package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Dto.Data;
import org.example.Service.DataProcessorService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class TCPServer {
    private final int port ;
    private final DataProcessorService service;
    private final ObjectMapper mapper = new ObjectMapper();

    public TCPServer(int port, DataProcessorService service) {
        this.port = port;
        this.service = service;
    }

    public void start() throws IOException {
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while (true) {
            try {
                Socket client = server.accept();
                System.out.println("Client connected");

                new Thread(() -> handleClient(client)).start();
            } catch (IOException e) {
                System.err.println("Connection error : " + e.getMessage());
            }
        }

    }

    private void handleClient(Socket client) {
        try {
            DataInputStream dataInput = new DataInputStream(client.getInputStream());
            while (true) {
                Data data = readMessage(dataInput);
                if (data != null) {
                    service.process(data);
                }
            }
        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
        } finally {
            try { client.close(); } catch (Exception ignored) { }
        }
    }

    private Data readMessage(DataInputStream dataInput) {
        try {
            if (dataInput.available() == 0) { return null; }

            int messageLength = dataInput.readInt();
            byte[] jsonBytes = new byte[messageLength];
            dataInput.readFully(jsonBytes);

            String jsonData = new String(jsonBytes, StandardCharsets.UTF_8);

            return mapper.readValue(jsonData, Data.class);
        } catch (Exception e) {
            System.err.println("Error reading message: " + e.getMessage());
            return null;
        }
    }
}
