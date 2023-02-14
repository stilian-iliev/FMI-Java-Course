package com.fmi.io_strems;


import com.fmi.io_strems.repositories.CityRepository;
import com.fmi.io_strems.services.Tagger;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class TaggerTest {
	
	@Mock
	private CityRepository cityRepository;
	
	@Mock
	private Tagger tagger;

	private CityCountryPair firstTestEntry;
	private CityCountryPair secondTestEntry;
	
	@BeforeEach
	public void setUp() throws IOException {
		cityRepository = mock(CityRepository.class);
		tagger = new Tagger(cityRepository);
		firstTestEntry = new CityCountryPair("Sofia", "Bulgaria");
		secondTestEntry = new CityCountryPair("Paris", "France");
	}

	@Test
	void testTagsCitySuccessfully() throws IOException {
		when(cityRepository.containsCity(firstTestEntry.getCity())).thenReturn(true);
		when(cityRepository.containsCity(secondTestEntry.getCity())).thenReturn(true);
		when(cityRepository.tagCity(eq(firstTestEntry.getCity()), anyInt())).thenReturn(firstTestEntry.toString());
		when(cityRepository.tagCity(eq(secondTestEntry.getCity()), anyInt())).thenReturn(secondTestEntry.toString());
		
		String input = String.format("Testing %s and %s.", firstTestEntry.getCity(), secondTestEntry.getCity());
		String expectedOutput = String.format("Testing %s and %s.", firstTestEntry.toString(), secondTestEntry.toString());
		StringWriter output = new StringWriter();
		
		tagger.tagCities(new StringReader(input), output);
		
		assertEquals(expectedOutput, output.toString().trim());
	}
	
	@Test
	void testGetNMostTaggedCitiesReturnsRightCollection() throws IOException {
		String [] expectedValues = {firstTestEntry.getCity(), secondTestEntry.getCity()};
		List<String> expected = Arrays.asList(expectedValues);
		
		when(cityRepository.getNMostTaggedCities(anyInt())).thenReturn(expected);
		
		List<String> actual = (List<String>) tagger.getNMostTaggedCities(2);
		
		assertEquals(2, actual.size());
		assertEquals(expected.get(0), actual.get(0));
		assertEquals(expected.get(1), actual.get(1));
		
	}
	
	@Test
	void testGetNMostTaggedCitiesThrowsIfNIsNegative() throws IOException {
		assertThrows(IllegalArgumentException.class, () -> {
			tagger.getNMostTaggedCities(-1);
		});
	}

	
	@Test
	void testGetAllTaggedCitiesReturnsRightCollection() throws IOException {
		String [] expectedValues = {firstTestEntry.getCity(), secondTestEntry.getCity()};
		Set<String> expected = new HashSet<>(Arrays.asList(expectedValues));
		
		when(cityRepository.getCities()).thenReturn(expected);
		
		Collection<String> allTaggedCities = tagger.getAllTaggedCities();
		
		assertTrue(allTaggedCities.contains(expectedValues[0]));
		assertTrue(allTaggedCities.contains(expectedValues[1]));
	}
	
	@Test
	void testGetAllTaggsCountReturnsRightValue() throws IOException {
		Map<String, Integer> expectedValues = new HashMap<>();
		expectedValues.put(firstTestEntry.getCity(), 1);
		expectedValues.put(secondTestEntry.getCity(), 2);
		
		long expected = 3;
		
		when(cityRepository.getTaggedCities()).thenReturn(expectedValues);
		
		long actual = tagger.getAllTagsCount();
		
		assertEquals(expected, actual);
	}
}
