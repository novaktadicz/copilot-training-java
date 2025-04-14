package com.zuehlke.telemetry.producer.service;

import com.zuehlke.telemetry.producer.model.TelemetryData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TelemetryProducer {
    private final KafkaTemplate<String, TelemetryData> kafkaTemplate;
    private final String topic;

    public TelemetryProducer(KafkaTemplate<String, TelemetryData> kafkaTemplate,
                             @Value("${producer.topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void send(TelemetryData data) {
        kafkaTemplate.send(topic, data.getDeviceId(), data);
    }
}
