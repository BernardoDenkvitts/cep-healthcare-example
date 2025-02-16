package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TcpSender {
    private final DataOutputStream out;
    private final ObjectMapper mapper;

    public TcpSender(Socket socket) throws IOException {
        this.out = new DataOutputStream(socket.getOutputStream());
        this.mapper = new ObjectMapper();
    }

    public void send(Data data) throws IOException {
        System.out.println(data.toString());
        byte[] byteData = mapper.writeValueAsString(data).getBytes(StandardCharsets.UTF_8);

        this.out.writeInt(byteData.length);
        this.out.write(byteData);
        this.out.flush();
    }
}
