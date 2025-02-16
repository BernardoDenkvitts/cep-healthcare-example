package org.example.Service;

import org.kie.api.runtime.KieSession;

public class DroolsService {

    private final KieSession kieSession;

    public DroolsService(KieSession kieSession) {
        this.kieSession = kieSession;

        // Drools Active Mode
        new Thread(this.kieSession::fireUntilHalt).start();
    }

    public void insert(Object object) {
        kieSession.insert(object);
    }

}
