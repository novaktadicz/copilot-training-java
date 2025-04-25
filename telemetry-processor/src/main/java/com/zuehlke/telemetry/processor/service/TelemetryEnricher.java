package com.zuehlke.telemetry.processor.service;

import com.zuehlke.telemetry.processor.model.DeviceMetadata;
import com.zuehlke.telemetry.processor.model.TelemetryData;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TelemetryEnricher {

    private final Map<String, TelemetryData> deviceState = new ConcurrentHashMap<>();
    private final DeviceRegistry registry;

    public TelemetryEnricher(DeviceRegistry registry) {
        this.registry = registry;
    }

    public TelemetryData enrich(TelemetryData current) {
        TelemetryData last = deviceState.get(current.getDeviceId());

        DeviceMetadata meta = registry.get(current.getDeviceId());
        if (meta != null) {
            current.setDeviceName(meta.getDeviceName());
            current.setFirmwareVersion(meta.getFirmwareVersion());
            current.setOwner(meta.getOwner());
            current.setMaxTemperature(meta.getMaxTemperature());
            current.setMaxBattery(meta.getMaxBattery());
        }

        if (last != null) {
            double speedDelta = current.getMetrics().getSpeed() - last.getMetrics().getSpeed();
            current.setAcceleration(speedDelta);

            double initialBattery = deviceState.get(current.getDeviceId()).getMetrics().getBattery();
            double health = (current.getMetrics().getBattery() / initialBattery) * 100.0;
            current.setEstimatedBatteryHealth(health);
        } else {
            current.setEstimatedBatteryHealth(100.0);
        }

        deviceState.put(current.getDeviceId(), current);
        return current;
    }
}