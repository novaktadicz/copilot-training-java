package com.zuehlke.telemetry.api.repository;

import com.zuehlke.telemetry.api.model.TelemetryData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface TelemetryRepository extends MongoRepository<TelemetryData, String> {
    Optional<TelemetryData> findTopByDeviceIdOrderByTimestampDesc(String deviceId);

    List<TelemetryData> findByTimestampBetween(Instant start, Instant end);

    List<TelemetryData> findByMetrics_BatteryLessThan(double batteryThreshold);
}
