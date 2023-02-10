package com.fmi.collections.models;


import com.fmi.collections.models.enums.DeviceType;
import com.fmi.collections.services.DeviceIdGenerator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class BaseSmartDevice implements SmartDevice {
	private final String id;
	private final String name;
	private final double powerConsumption;
	private final LocalDateTime installationDateTime;
	private final DeviceType type;
	
	public BaseSmartDevice(String name, double powerConsumption, LocalDateTime installationDateTime, DeviceType deviceType) {
		this.id = DeviceIdGenerator.generate(deviceType, name);
		this.name = name;
		this.powerConsumption = powerConsumption;
		this.installationDateTime = installationDateTime;
		this.type = deviceType;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getPowerConsumption() {
		return powerConsumption;
	}

	@Override
	public LocalDateTime getInstallationDateTime() {
		return installationDateTime;
	}

	@Override
	public DeviceType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		BaseSmartDevice other = (BaseSmartDevice) obj;
		return id.equals(other.getId());
	}
	
	public double totalPowerConsumption() {
		Duration powerOnDuration = Duration.between(installationDateTime, LocalDateTime.now());
		long powerOnHours = powerOnDuration.toHours();
		double consumedEnergy = powerOnHours * powerConsumption;
		
		return consumedEnergy;
	}

}
