package com.fmi.collections.repositories;

import com.fmi.collections.models.SmartDevice;
import com.fmi.collections.models.enums.DeviceType;

import java.util.*;
import java.util.stream.Collectors;

public class DeviceRepository {
	private final Map<String, SmartDevice> devices;
	
	public DeviceRepository() {
		devices = new LinkedHashMap<>();
	}
	
	public void save(SmartDevice device) {		
		devices.put(device.getId(), device);
	}
	
	public SmartDevice delete(SmartDevice device) {	
		return devices.remove(device.getId());
	}
	
	public boolean existsById(String id) {
		return devices.containsKey(id);
	}
	
	public Optional<SmartDevice> findById(String id) {
		return Optional.ofNullable(devices.get(id));
	}
	
	public long count() {
		return devices.size();
	}

	public long countByType(DeviceType type) {
		return devices.values().stream()
				.filter(device -> device.getType() == type)
				.count();
	}

	public Collection<String> findFirstNOrderByPowerConsumptionDesc(int n) {
		//stream values
		return devices.values()
			.stream()
			.sorted(Comparator.comparing(SmartDevice::totalPowerConsumption).reversed())
			.limit(n)
			.map(e -> e.getId())
			.collect(Collectors.toSet());
	}

	public Collection<SmartDevice> findFirstNDevices(int n) {
		return devices.values()
				.stream()
				.limit(n)
				.collect(Collectors.toSet());
	}
}
