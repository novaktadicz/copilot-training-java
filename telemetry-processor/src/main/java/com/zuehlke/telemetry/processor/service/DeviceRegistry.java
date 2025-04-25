package com.zuehlke.telemetry.processor.service;

import com.zuehlke.telemetry.processor.model.DeviceMetadata;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DeviceRegistry {

    private final Map<String, DeviceMetadata> metadataMap = new HashMap<>();

    @PostConstruct
    public void init() {
        metadataMap.put("drone-1", new DeviceMetadata("drone-1", "SkyRacer X1", "1.0.3", "Alice", 70, 100));
        metadataMap.put("drone-2", new DeviceMetadata("drone-2", "StormFury 7", "2.1.0", "Bob", 80, 100));
        metadataMap.put("drone-3", new DeviceMetadata("drone-3", "VoltWing V2", "1.8.5", "Charlie", 75, 100));
        metadataMap.put("drone-4", new DeviceMetadata("drone-4", "AeroGlide 3000", "3.0.1", "David", 85, 100));
        metadataMap.put("drone-5", new DeviceMetadata("drone-5", "SkyHawk 200", "2.5.0", "Eve", 90, 100));
        metadataMap.put("drone-6", new DeviceMetadata("drone-6", "SkyRacer X1", "1.0.3", "David", 70, 100));
        metadataMap.put("drone-7", new DeviceMetadata("drone-7", "StormFury 7", "2.1.0", "Alice", 80, 100));
        metadataMap.put("drone-8", new DeviceMetadata("drone-8", "VoltWing V2", "1.8.5", "Bob", 75, 100));
    }

    public DeviceMetadata get(String deviceId) {
        return metadataMap.get(deviceId);
    }
}