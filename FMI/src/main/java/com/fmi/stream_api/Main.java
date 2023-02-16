package com.fmi.stream_api;

import com.fmi.stream_api.repositories.SpotifyTrackRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		SpotifyTrackRepository str = new SpotifyTrackRepository();
		str.loadFromCsv(new FileReader(new File("C:\\perforce\\1666\\ims.bg\\trainings\\2022-2023\\stilian\\java\\com\\sap\\asj\\training\\lecture8\\spotify-data.csv")));
	}
}
