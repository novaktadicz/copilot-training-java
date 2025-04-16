package com.zuehlke.telemetry.store.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


@Document(collection = "telemetry_data")
public class TelemetryData {

    @Id
    private String id;
    private String deviceId;
    private Instant timestamp;
    private double latitude;
    private double longitude;
    private Metrics metrics;
    private Instant processedAt;

    private String deviceName;
    private String firmwareVersion;
    private String owner;
    private Double maxTemperature;
    private Double maxBattery;

    private Double estimatedBatteryHealth;
    private Double acceleration;
    private Double batteryPerKm;
    private Double distanceFromLast;

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Instant getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(Instant processedAt) {
        this.processedAt = processedAt;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Double getMaxBattery() {
        return maxBattery;
    }

    public void setMaxBattery(Double maxBattery) {
        this.maxBattery = maxBattery;
    }

    public Double getEstimatedBatteryHealth() {
        return estimatedBatteryHealth;
    }

    public void setEstimatedBatteryHealth(Double estimatedBatteryHealth) {
        this.estimatedBatteryHealth = estimatedBatteryHealth;
    }

    public Double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Double acceleration) {
        this.acceleration = acceleration;
    }

    public Double getBatteryPerKm() {
        return batteryPerKm;
    }

    public void setBatteryPerKm(Double batteryPerKm) {
        this.batteryPerKm = batteryPerKm;
    }

    public Double getDistanceFromLast() {
        return distanceFromLast;
    }

    public void setDistanceFromLast(Double distanceFromLast) {
        this.distanceFromLast = distanceFromLast;
    }

    public static class Metrics {
        private double battery;
        private double temperature;
        private double speed;

        // Getters and setters
        public double getBattery() {
            return battery;
        }

        public void setBattery(double battery) {
            this.battery = battery;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }
    }
}
