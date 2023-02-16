package com.fmi.stream_api.repositories;

import com.fmi.stream_api.models.SpotifyTrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

public class SpotifyTrackRepository {
	private List<SpotifyTrack> tracks;
	
	public void loadFromCsv(Reader dataInput) {
		try (BufferedReader reader = new BufferedReader(dataInput)) {

            tracks = reader.lines()
                    .skip(1)
                    .map(SpotifyTrack::new)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new IllegalArgumentException("Could not load tracks", e);
        }
	}
	
	public List<SpotifyTrack> getAllTracks() {
		return tracks;
	}

	public List<SpotifyTrack> getTracksByArtist(String artist) {
		if (artist == null) {
			throw new IllegalArgumentException("Artist cannot be null.");
		}
		
		List<SpotifyTrack> tracksByArtist = tracks.stream()
				.filter(t -> t.getArtists().contains(artist))
				.collect(Collectors.toList());
		
		return tracksByArtist;
	}
	
	/**
	 * Returns all tracks between the mentioned years.
	 *
	 * @param startYear the minimal year of the track (INCLUSIVE)
	 * @param endYear the latest year of the track (EXCLUSIVE)
	 */
	public List<SpotifyTrack> getAllBetweenYears(int startYear, int endYear){
		List<SpotifyTrack> tracksBetweenYears = tracks.stream()
				.filter(t -> (t.getYear() >= startYear) && (t.getYear() < endYear))
				.collect(Collectors.toList());
		
		return tracksBetweenYears;
	}

	public List<SpotifyTrack> getAllTracksBefore(int year) {
		List<SpotifyTrack> tracksBeforeYear = tracks.stream()
				.filter(t -> t.getYear() < year)
				.collect(Collectors.toList());
		
		return tracksBeforeYear;
	}

	public List<SpotifyTrack> getAllByYear(int year) {
		List<SpotifyTrack> tracksInYear = tracks.stream()
				.filter(t -> t.getYear() == year)
				.collect(Collectors.toList());
		
		return tracksInYear;
		
	}

}
