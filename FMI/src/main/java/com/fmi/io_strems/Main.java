package com.fmi.io_strems;

import com.fmi.io_strems.repositories.CityRepository;
import com.fmi.io_strems.services.Tagger;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		Writer consoleWriter = new PrintWriter(System.out);
		
		File csvFile = new File("C:\\perforce\\1666\\ims.bg\\trainings\\2022-2023\\stilian\\java\\com\\sap\\asj\\training\\lecture7\\resources\\world-cities.csv");
		FileReader csvFileReader = new FileReader(csvFile);
		
		CityRepository cityRepository = new CityRepository(csvFileReader);
		
		Tagger tagger = new Tagger(cityRepository);
		
//		tagger.tagCities(new StringReader("Plovdiv's old town is a major tourist attraction. It is the second largest city\r\n"
//				+ "in Bulgaria, after the capital ,Sofia."), consoleWriter);
		tagger.tagCities(new StringReader("Plovdiv's old town is a major tourist attraction. It is the second largest city\r\n"
				+ "in Bulgaria, after the capital Sofia."), consoleWriter);
		
		
		System.out.println(tagger.getNMostTaggedCities(3));
		
		System.out.println(tagger.getAllTagsCount());
		
		
	}
}
