package com.zuehlke.telemetry.processor.service;

import com.zuehlke.telemetry.processor.mapper.TelemetryMapper;
import com.zuehlke.telemetry.processor.model.RawTelemetryDTO;
import com.zuehlke.telemetry.processor.model.TelemetryData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TelemetryStreamService {

    private final KafkaTemplate<String, TelemetryData> kafkaTemplate;
    private final TelemetryMapper mapper;
    private final TelemetryEnricher enricher;

    @Value("${kafka.topic.out}")
    private String outputTopic;

    public TelemetryStreamService(KafkaTemplate<String, TelemetryData> kafkaTemplate,
                                  TelemetryMapper mapper,
                                  TelemetryEnricher enricher) {
        this.kafkaTemplate = kafkaTemplate;
        this.mapper = mapper;
        this.enricher = enricher;
    }

    @KafkaListener(topics = "${kafka.topic.in}", groupId = "telemetry-processor-group")
    public void process(RawTelemetryDTO raw) {
        TelemetryData data = mapper.map(raw);
        data = enricher.enrich(data);

        kafkaTemplate.send(outputTopic, data.getDeviceId(), data);
    }
}
