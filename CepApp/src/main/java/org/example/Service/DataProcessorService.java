package org.example.Service;

import org.example.Dto.Data;

public class DataProcessorService {
    DroolsService droolsService;

    public DataProcessorService(DroolsService droolsService) {
        this.droolsService = droolsService;
    }

    public void process(Data data) {
        droolsService.insert(data);
    }
}
