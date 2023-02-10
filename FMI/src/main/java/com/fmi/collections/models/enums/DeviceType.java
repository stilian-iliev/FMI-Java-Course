package com.fmi.collections.models.enums;

public enum DeviceType {
    TRAFFIC_LIGHT("TFL"),
    LAMP("LM"),
    CAMERA("CM");

	private final String shortName;
    private int count;

    private DeviceType(String shortName) {
        this.shortName = shortName;
        this.count = 0;
    }

    public String getShortName() {
        return shortName;
    }
    
    public int getAndIncrementCount() {
    	int tmp = count;
    	count++;
    	return tmp;
    }
}
