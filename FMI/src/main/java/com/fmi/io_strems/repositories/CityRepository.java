package com.fmi.io_strems.repositories;

import com.fmi.io_strems.exceptions.CityNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

public class CityRepository {
	private static final String CSV_DELIMITER = ",";
	private static final int INDEX_OF_CITY_IN_CSV = 0;
	private static final int INDEX_OF_COUNTRY_IN_CSV = 1;

	private final Map<String, String> cityToCountryMap;
	private final Map<String, Integer> cityTagCount;
	
	public CityRepository(){
		cityToCountryMap = new HashMap<>();
		cityTagCount = new HashMap<>();
	}
	
	//implement autoclosable interface so you can use try(){} in main 
	public CityRepository(Reader cityReader) throws IOException {
		cityToCountryMap = new HashMap<>();
		cityTagCount = new HashMap<>();
		loadCitiesFromCsv(cityReader);
	}
	
	public void loadCitiesFromCsv(Reader citiesReader) throws IOException {
		BufferedReader reader = new BufferedReader(citiesReader);

		String currentLine = reader.readLine();

		while (currentLine != null) {
			String[] lineTokens = currentLine.split(CSV_DELIMITER);
			ensureValidTokens(lineTokens);
			String city = lineTokens[INDEX_OF_CITY_IN_CSV];
			String country = lineTokens[INDEX_OF_COUNTRY_IN_CSV];
			addCity(city, country);

			currentLine = reader.readLine();
		}
	}
	
	private void ensureValidTokens(String[] tokens) {
		if (tokens.length < 2) {
			throw new IllegalStateException("CSV entry :" + String.join(CSV_DELIMITER, tokens) + " doesn't have enough values to be valid entry. Each entry must have atleast a city name and country name.");
		}
	}

	private void addCity(String city, String country) {
		cityToCountryMap.put(city, country);
	}

	public boolean containsCity(String word) {
		return cityToCountryMap.containsKey(word);
	}
	
	//put methods about tagging in tagger
	public String tagCity(String word, int occurancesOfCityInLine) {
		if (!containsCity(word)) {
			throw new CityNotFoundException("The city \"" + word + "\" you are trying to tag is not loaded in the repository.");
		}
		
		increaseTagCount(word, occurancesOfCityInLine);
		return getTaggedCityString(word);
	}
	
	private void increaseTagCount(String city, int occurances) {
		cityTagCount.putIfAbsent(city, 0);
		int newTagCount = (cityTagCount.get(city) + occurances);
		cityTagCount.put(city, newTagCount);
	}
	
	private String getTaggedCityString(String city) {
		String country = cityToCountryMap.get(city);
		String taggedCity = String.format("<city country=\"%s\">%s</city>", country, city);
		return taggedCity;
	}

	public void resetTagCount() {
		cityTagCount.clear();
	}

	public List<String> getNMostTaggedCities(int n) {
		return cityTagCount.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.limit(n)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}

	public Set<String> getCities() {
		return Collections.unmodifiableSet(cityTagCount.keySet());
	}

	public Map<String, Integer> getTaggedCities() {
		return Collections.unmodifiableMap(cityTagCount);
	}

}
