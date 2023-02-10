package com.fmi.collections;

import com.fmi.collections.exceptions.DeviceAlreadyRegisteredException;
import com.fmi.collections.models.SmartLamp;
import com.fmi.collections.models.SmartTrafficLight;
import com.fmi.collections.repositories.DeviceRepository;
import com.fmi.collections.services.SmartCityHub;

import java.time.LocalDateTime;

public class Main {
	public static void main(String[] args) throws DeviceAlreadyRegisteredException {
		SmartLamp sl = new SmartLamp("lamp10x1", 1, LocalDateTime.now().minusHours(10));
		SmartLamp sl2 = new SmartLamp("lamp", 1, LocalDateTime.now());
		SmartTrafficLight stl = new SmartTrafficLight("trafic light", 10, LocalDateTime.now().minusHours(2));
		
		SmartLamp sl3 = new SmartLamp("lamp", 1, LocalDateTime.now());
		
		
		DeviceRepository dr = new DeviceRepository();
		SmartCityHub smh = new SmartCityHub(dr);
		
		smh.register(sl);
		smh.register(sl2);
		smh.register(stl);
		smh.register(sl3);
		
		smh.getTopNDevicesByPowerConsumption(1)
		.forEach(e -> System.out.println(e));;
	}
}
