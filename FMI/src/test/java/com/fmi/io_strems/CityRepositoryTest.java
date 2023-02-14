package com.fmi.io_strems;

import com.fmi.io_strems.exceptions.CityNotFoundException;
import com.fmi.io_strems.repositories.CityRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CityRepositoryTest {
	private CityRepository cityRepository;
	private CSVCityToCountryMaker cityToCountryMaker;
	
	private CityCountryPair firstTestEntry; 
	private CityCountryPair secondTestEntry; 

	@BeforeEach
	public void setUp() throws IOException {
		firstTestEntry = new CityCountryPair("Sofia", "Bulgaria");
		secondTestEntry = new CityCountryPair("Paris", "France");
		
		cityToCountryMaker = new CSVCityToCountryMaker();
		String sofiaCsvString = cityToCountryMaker.getInCSV(firstTestEntry);
		String parisCsvString = cityToCountryMaker.getInCSV(secondTestEntry);
		String citiesInCsv = String.join("\n", sofiaCsvString, parisCsvString);
		
		cityRepository = new CityRepository(new StringReader(citiesInCsv));
	}
	
	@Test
	public void testConstructorLoadsCities() {
		assertTrue(cityRepository.containsCity(firstTestEntry.getCity()));
		assertTrue(cityRepository.containsCity(secondTestEntry.getCity()));
		assertFalse(cityRepository.containsCity("notAddedCity"));
	}
	
	@Test
	public void testLoadCitiesThrowsIfInvalidCSV() {
		assertThrows(IllegalStateException.class, () -> {
			cityRepository.loadCitiesFromCsv(new StringReader("invlaidCSV"));
		});
	}
	
	@Test
	public void testTagCityReturnsCityInTaggedForm() {
		String expectedOutput = firstTestEntry.toString();
		String output = cityRepository.tagCity(firstTestEntry.getCity(), 1);
		
		assertEquals(expectedOutput, output);
	}
	
	@Test
	public void testTagCityNotLoadedCityShouldThrow() {
		assertThrows(CityNotFoundException.class, () -> {
			cityRepository.tagCity("notAddedCity", 1);
	    });
	}
	
	@Test
	public void testGetNMostTaggedCitiesReturnsInRightOrder() {
		cityRepository.tagCity(firstTestEntry.getCity(), 1);
		cityRepository.tagCity(secondTestEntry.getCity(), 2);
		
		List<String> nMostTaggedCities = cityRepository.getNMostTaggedCities(2);
		
		assertEquals(2, nMostTaggedCities.size());
		
		assertEquals(secondTestEntry.getCity(), nMostTaggedCities.get(0));
		assertEquals(firstTestEntry.getCity(), nMostTaggedCities.get(1));
	}
	
	@Test
	public void testResetTagCountClearsTheTaggedCities() {
		cityRepository.tagCity(firstTestEntry.getCity(), 1);
		cityRepository.tagCity(secondTestEntry.getCity(), 2);
		
		cityRepository.resetTagCount();
		
		List<String> nMostTaggedCities = cityRepository.getNMostTaggedCities(2);
		
		assertEquals(0, nMostTaggedCities.size());
	}
	
	@Test
	public void testGetCitiesReturnUnmodifiableSetOfTaggedCities() {
		cityRepository.tagCity(firstTestEntry.getCity(), 1);
		cityRepository.tagCity(firstTestEntry.getCity(), 1);
		cityRepository.tagCity(secondTestEntry.getCity(), 2);
		
		Set<String> cities = cityRepository.getCities();
		
		assertEquals(2, cities.size());
		
		assertTrue(cities.contains(firstTestEntry.getCity()));
		assertTrue(cities.contains(secondTestEntry.getCity()));
	}
	
}
