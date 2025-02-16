package org.example;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[] patientIds = new int[]{1, 2, 3};

        for (int id: patientIds) {
            new Thread(new DataSenderTask(id)).start();
        }
    }

}