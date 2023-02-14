package com.fmi.io_strems;

public class CSVCityToCountryMaker {

	public String getInCSV(CityCountryPair entry) {
		return entry.getCity() + ',' + entry.getCountry();
	}

}
