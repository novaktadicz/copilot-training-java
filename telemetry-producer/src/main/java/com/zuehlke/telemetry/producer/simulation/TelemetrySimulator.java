package com.zuehlke.telemetry.producer.simulation;

import com.zuehlke.telemetry.producer.model.TelemetryData;
import com.zuehlke.telemetry.producer.service.TelemetryProducer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;

@Component
public class TelemetrySimulator {

    private final TelemetryProducer producer;
    private final Random random = new Random();

    public TelemetrySimulator(TelemetryProducer producer) {
        this.producer = producer;
    }

    @Scheduled(fixedRate = 3000)
    public void generateTelemetry() {
        TelemetryData data = new TelemetryData();
        data.setDeviceId("drone-" + (random.nextInt(10) + 1));
        data.setTimestamp(Instant.now());
        data.setLatitude(37.0 + random.nextDouble());
        data.setLongitude(-122.0 + random.nextDouble());

        TelemetryData.Metrics metrics = new TelemetryData.Metrics();
        metrics.setBattery(30 + random.nextDouble() * 70);
        metrics.setTemperature(20 + random.nextDouble() * 30);
        metrics.setSpeed(random.nextDouble() * 100);

        data.setMetrics(metrics);

        producer.send(data);
    }
}
