package com.fmi.io_strems;

public class CityCountryPair {
	
	private String city;
	private String country;

	public CityCountryPair(String city, String country) {
		this.city = city;
		this.country = country;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getCountry() {
		return country;
	}
	//instead of csv maker make method to return entry in csv format
	
	//rename to get tagged
	@Override
	public String toString() {
		return String.format("<city country=\"%s\">%s</city>", country, city);
	}

}
