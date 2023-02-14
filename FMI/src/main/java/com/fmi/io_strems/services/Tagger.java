package com.fmi.io_strems.services;

import com.fmi.io_strems.repositories.CityRepository;

import java.io.*;
import java.util.*;

public class Tagger {
	private static final String WORD_SPLITTER = "[^\\w]+";
	
	private final CityRepository cityRepository;

	/**
	 * Creates a new instance of Tagger for a given list of city/country pairs
	 * 
	 * Reader is not closed
	 *
	 * @param citiesReader a java.io.Reader input stream containing list of cities
	 *                     and countries in the specified CSV format
	 * @throws IOException
	 */
	public Tagger(CityRepository cityRepository) throws IOException {
		this.cityRepository = cityRepository;
	}

	/**
	 * Processes an input stream of a text file, tags any cities and outputs result
	 * to a text output stream.
	 * 
	 * Reader and Writer closing is not handled
	 *
	 * @param text   a java.io.Reader input stream containing text to be processed
	 * @param output a java.io.Writer output stream containing the result of tagging
	 * @throws IOException
	 */
	public void tagCities(Reader text, Writer output) throws IOException {
		BufferedReader textReader = new BufferedReader(text);
		BufferedWriter textWriter = new BufferedWriter(output);

		cityRepository.resetTagCount();
		
		String currentLine = textReader.readLine();
		
		//TODO: make more readable
		while (currentLine != null) {
			String taggedLine = tagLine(currentLine);
			
			textWriter.write(taggedLine);
			textWriter.newLine();

			currentLine = textReader.readLine();
		}

		textWriter.flush();
	}

	private String tagLine(String currentLine) {
		String taggedLine = currentLine;
		
		String[] words = currentLine.split(WORD_SPLITTER);
		Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));

		for (String word : uniqueWords) {
			if (cityRepository.containsCity(word)) {
				int occurancesOfCityInLine = (currentLine.split(word, -1).length) - 1;
				String taggedCityString = cityRepository.tagCity(word, occurancesOfCityInLine);
				taggedLine = taggedLine.replaceAll(word, taggedCityString);
			}
		}
		
		return taggedLine;
	}

	/**
	 * Returns a collection the top @n most tagged cities' unique names from the
	 * last tagCities() invocation. Note that if a particular city has been tagged
	 * more than once in the text, just one occurrence of its name should appear in
	 * the result. If @n exceeds the total number of cities tagged, return as many
	 * as available If tagCities() has not been invoked at all, return an empty
	 * collection.
	 *
	 * @param n the maximum number of top tagged cities to return
	 * @return a collection the top @n most tagged cities' unique names from the
	 *         last tagCities() invocation.
	 */
	public Collection<String> getNMostTaggedCities(int n) {
		ensureNumberPositive(n);
		
		List<String> nMostTaggedCities = cityRepository.getNMostTaggedCities(n);
		
		return nMostTaggedCities;
	}

	private void ensureNumberPositive(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Only positive numbers are allowed. The number you entered was:" + n);
		}
	}

	/**
	 * Returns a collection of all tagged cities' unique names from the last
	 * tagCities() invocation. Note that if a particular city has been tagged more
	 * than once in the text, just one occurrence of its name should appear in the
	 * result. If tagCities() has not been invoked at all, return an empty
	 * collection.
	 *
	 * @return a collection of all tagged cities' unique names from the last
	 *         tagCities() invocation.
	 */
	public Collection<String> getAllTaggedCities() {
		Set<String> copyOfLastTaggedCities = new HashSet<>(cityRepository.getCities());
		
		return copyOfLastTaggedCities;
	}

	/**
	 * Returns the total number of tagged cities in the input text from the last
	 * tagCities() invocation In case a particular city has been taged in several
	 * occurences, all must be counted. If tagCities() has not been invoked at all,
	 * return 0.
	 *
	 * @return the total number of tagged cities in the input text
	 */
	public long getAllTagsCount() {
		int totalTags = cityRepository.getTaggedCities()
				.values()
				.stream()
				.mapToInt(i -> i)
				.sum();
		
		return totalTags;
	}

}