package com.zuehlke.telemetry.processor.model;

public class DeviceMetadata {
    private String deviceId;
    private String deviceName;
    private String firmwareVersion;
    private String owner;
    private double maxTemperature;
    private double maxBattery;

    public DeviceMetadata(String deviceId, String deviceName, String firmwareVersion, String owner, double maxTemperature, double maxBattery) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.firmwareVersion = firmwareVersion;
        this.owner = owner;
        this.maxTemperature = maxTemperature;
        this.maxBattery = maxBattery;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public String getOwner() {
        return owner;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getMaxBattery() {
        return maxBattery;
    }
}
