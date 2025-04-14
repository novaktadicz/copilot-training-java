package com.zuehlke.telemetry.api.controller;

import com.zuehlke.telemetry.api.model.TelemetryData;
import com.zuehlke.telemetry.api.repository.TelemetryRepository;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/telemetry")
public class TelemetryController {

    private final TelemetryRepository repository;

    public TelemetryController(TelemetryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/latest/{deviceId}")
    public TelemetryData getLatest(@PathVariable String deviceId) {
        return repository.findTopByDeviceIdOrderByTimestampDesc(deviceId)
                .orElse(null);
    }

    @GetMapping("/range")
    public List<TelemetryData> getInRange(@RequestParam Instant start,
                                          @RequestParam Instant end) {
        return repository.findByTimestampBetween(start, end);
    }

    @GetMapping("/low-battery")
    public List<TelemetryData> getLowBatteryDevices(@RequestParam(defaultValue = "20") double threshold) {
        return repository.findByMetrics_BatteryLessThan(threshold);
    }
}
