package com.fmi.collections.services;

import com.fmi.collections.models.enums.DeviceType;

public class DeviceIdGenerator {
	public static String generate(DeviceType deviceType, String name) {
		String id = String.format("%s-%s-%d", deviceType.getShortName(), name, deviceType.getAndIncrementCount());
		
		return id;
	}
}
