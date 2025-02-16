package org.example.Repository;

import org.example.Dto.Alert;

import java.util.ArrayList;

public class InMemoryRepository {

    private final ArrayList<Alert> alerts;

    public InMemoryRepository() {
        this.alerts = new ArrayList<>();
    }

    public Alert save(Alert alert) {
        this.alerts.add(alert);

        return alert;
    }
}
