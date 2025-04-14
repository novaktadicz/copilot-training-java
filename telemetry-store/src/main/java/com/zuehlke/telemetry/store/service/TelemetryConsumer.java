package com.zuehlke.telemetry.store.service;

import com.zuehlke.telemetry.store.model.TelemetryData;
import com.zuehlke.telemetry.store.repository.TelemetryRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TelemetryConsumer {

    private final TelemetryRepository repository;

    public TelemetryConsumer(TelemetryRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "${kafka.topic.in}", groupId = "telemetry-store-group")
    public void handleMessage(TelemetryData data) {
        repository.save(data);
    }
}
