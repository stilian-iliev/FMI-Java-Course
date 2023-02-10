package com.fmi.collections.models;

import com.fmi.collections.models.enums.DeviceType;

import java.time.LocalDateTime;

public class SmartLamp extends BaseSmartDevice {
	
	public SmartLamp(String name, double powerConsumption, LocalDateTime installationDateTime) {
		super(name, powerConsumption, installationDateTime, DeviceType.LAMP);
	}
}
