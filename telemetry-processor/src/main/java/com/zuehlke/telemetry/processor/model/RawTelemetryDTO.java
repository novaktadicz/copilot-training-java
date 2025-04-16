package com.zuehlke.telemetry.processor.model;

import java.time.Instant;

public class RawTelemetryDTO {
    private String deviceId;
    private Instant timestamp;
    private double latitude;
    private double longitude;
    private Metrics metrics;

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

    // Getters and setters
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
}
