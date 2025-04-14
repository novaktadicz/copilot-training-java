package com.zuehlke.telemetry.processor.service;

import com.zuehlke.telemetry.processor.model.TelemetryData;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TelemetryTransformer {

    public TelemetryData transform(TelemetryData original) {
        original.setProcessedAt(Instant.now());
        // Add any other logic here (filtering, enrichment, etc.)
        return original;
    }
}
