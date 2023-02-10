package com.fmi.collections.models;

import com.fmi.collections.models.enums.DeviceType;

import java.time.LocalDateTime;

public class SmartTrafficLight extends BaseSmartDevice {
	
	public SmartTrafficLight(String name, double powerConsumption, LocalDateTime installationDateTime) {
		super(name, powerConsumption, installationDateTime, DeviceType.TRAFFIC_LIGHT);
	}

}
