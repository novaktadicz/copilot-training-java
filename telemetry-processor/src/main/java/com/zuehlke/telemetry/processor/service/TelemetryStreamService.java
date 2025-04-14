package com.zuehlke.telemetry.processor.service;

import com.zuehlke.telemetry.processor.model.TelemetryData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TelemetryStreamService {

    private final TelemetryTransformer transformer;
    private final KafkaTemplate<String, TelemetryData> kafkaTemplate;

    @Value("${kafka.topic.out}")
    private String outputTopic;

    public TelemetryStreamService(TelemetryTransformer transformer,
                                  KafkaTemplate<String, TelemetryData> kafkaTemplate) {
        this.transformer = transformer;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "${kafka.topic.in}", groupId = "telemetry-processor-group")
    public void process(TelemetryData data) {
        TelemetryData transformed = transformer.transform(data);
        kafkaTemplate.send(outputTopic, data.getDeviceId(), transformed);
    }
}
