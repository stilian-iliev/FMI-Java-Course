package com.fmi.collections.services;

import com.fmi.collections.exceptions.DeviceAlreadyRegisteredException;
import com.fmi.collections.exceptions.DeviceNotFoundException;
import com.fmi.collections.models.SmartDevice;
import com.fmi.collections.models.enums.DeviceType;
import com.fmi.collections.repositories.DeviceRepository;

import java.util.Collection;
import java.util.Optional;

public class SmartCityHub {
	private final DeviceRepository deviceRepository;

    public SmartCityHub(DeviceRepository deviceRepository) {
    	this.deviceRepository = deviceRepository;
    }

    /**
     * Adds a @device to the SmartCityHub.
     *
     * @throws IllegalArgumentException in case @device is null.
     * @throws DeviceAlreadyRegisteredException in case the @device is already registered.
     */
    public void register(SmartDevice device) throws DeviceAlreadyRegisteredException {
    	ensureNotNull(device, "Device cannot be null.");
    	
		if (deviceRepository.existsById(device.getId())) {
			throw new DeviceAlreadyRegisteredException(String.format("Device with id %s is already registered.", device.getId()));
		}
		
        deviceRepository.save(device);
    }

    /**
     * Removes the @device from the SmartCityHub.
     *
     * @throws IllegalArgumentException in case null is passed.
     * @throws DeviceNotFoundException in case the @device is not found.
     */
    public void unregister(SmartDevice device) throws DeviceNotFoundException {
    	ensureNotNull(device, "Device cannot be null.");
    	
    	if (!deviceRepository.existsById(device.getId())) {
    		throw new DeviceNotFoundException(String.format("Device with id %s is not registered in the hub.", device.getId()));
		}
    	
        deviceRepository.delete(device);
    }

    /**
     * Returns a SmartDevice with an ID @id.
     *
     * @throws IllegalArgumentException in case @id is null.
     * @throws DeviceNotFoundException in case device with ID @id is not found.
     */
    public SmartDevice getDeviceById(String id) throws DeviceNotFoundException {
    	ensureNotNull(id, "Device id cannot be null.");
    	
    	Optional<SmartDevice> smartDevice = deviceRepository.findById(id);
    	
    	if (!smartDevice.isPresent()) {
			throw new DeviceNotFoundException(String.format("Device with id %s is not registered in the hub.", id));
		}
    	
        return smartDevice.get();
    }

    /**
     * Returns the total number of devices with type @type registered in SmartCityHub.
     *
     * @throws IllegalArgumentException in case @type is null.
     */
    public long getDeviceQuantityPerType(DeviceType type) {
    	ensureNotNull(type, "Device type cannot be null.");
    	
    	long count = deviceRepository.countByType(type);
        return count;
    }

    /**
     * Returns a collection of IDs of the top @n devices which consumed
     * the most power from the time of their installation until now.
     * 
     * The total power consumption of a device is calculated by the hours elapsed
     * between the two LocalDateTime-s: the installation time and the current time (now)
     * multiplied by the stated nominal hourly power consumption of the device.
     *
     * If @n exceeds the total number of devices, return all devices available sorted by the given criterion.
     * @throws IllegalArgumentException in case @n is a negative number.
     */
    public Collection<String> getTopNDevicesByPowerConsumption(int n) {
    	ensureNumberIsPositive(n);
    	
    	Collection<String> deviceIds = deviceRepository.findFirstNOrderByPowerConsumptionDesc(n);
    	return deviceIds;
    }

    /**
     * Returns a collection of the first @n registered devices, i.e the first @n that were added
     * in the SmartCityHub (registration != installation).
     * 
     * If @n exceeds the total number of devices, return all devices available sorted by the given criterion.
     *
     * @throws IllegalArgumentException in case @n is a negative number.
     */
    public Collection<SmartDevice> getFirstNDevicesByRegistration(int n) {
    	ensureNumberIsPositive(n);
    	
    	Collection<SmartDevice> smartDevices = deviceRepository.findFirstNDevices(n);
        return smartDevices;
    }
    
    private void ensureNotNull(Object object, String errorMsg){
		if (object == null) {
			throw new IllegalArgumentException(errorMsg);
		}
	}
    
    private void ensureNumberIsPositive(int number) {
    	if (number < 0) {
			throw new IllegalArgumentException("Only positive numbers allowed! Number you entered " + number);
		}
    }
}