package com.zuehlke.telemetry.store.repository;

import com.zuehlke.telemetry.store.model.TelemetryData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TelemetryRepository extends MongoRepository<TelemetryData, String> {
}
