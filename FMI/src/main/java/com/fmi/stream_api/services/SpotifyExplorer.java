package com.fmi.stream_api.services;

import com.fmi.stream_api.models.SpotifyTrack;
import com.fmi.stream_api.repositories.SpotifyTrackRepository;

import java.util.*;
import java.util.stream.Collectors;

public class SpotifyExplorer {

	private SpotifyTrackRepository trackRepository;

	/**
	 * Loads the dataset from the given {@code dataInput} stream.
	 *
	 * @param trackRepository java.io.Reader input stream from which the dataset can be
	 *                  read
	 */
	public SpotifyExplorer(SpotifyTrackRepository trackRepository) {
		this.trackRepository = trackRepository;
	}

	/**
	 * @return all spotify tracks from the dataset as unmodifiable collection If the
	 *         dataset is empty, return an empty collection
	 */
	public Collection<SpotifyTrack> getAllSpotifyTracks() {
		return Collections.unmodifiableCollection(trackRepository.getAllTracks());
	}

	/**
	 * @return all tracks from the spotify dataset classified as explicit as
	 *         unmodifiable collection If the dataset is empty or contains no tracks
	 *         classified as explicit, return an empty collection
	 */
	public Collection<SpotifyTrack> getExplicitSpotifyTracks() {
		Collection<SpotifyTrack> explicitTracks = trackRepository.getAllTracks().stream()
				.filter(SpotifyTrack::isExplicit)
				.collect(Collectors.toList());

		return explicitTracks;
	}

	/**
	 * Returns all tracks in the dataset, grouped by release year. If no tracks were
	 * released in a given year it should not appear as key in the map.
	 *
	 * @return map with year as a key and the set of spotify tracks released this
	 *         year as value. If the dataset is empty, return an empty collection
	 */
	public Map<Integer, Set<SpotifyTrack>> groupSpotifyTracksByYear() {
		Map<Integer, Set<SpotifyTrack>> tracksGroupedByYear = trackRepository.getAllTracks().stream()
				.collect(Collectors.groupingBy(SpotifyTrack::getYear, Collectors.toSet()));

		return tracksGroupedByYear;
	}

	/**
	 * Returns the number of years between the oldest and the newest released tracks
	 * of an artist. For example, if the oldest and newest tracks are released in
	 * 1996 and 1998 respectively, return 3, if the oldest and newest release match,
	 * e.g. 2002-2002, return 1. Note that tracks with multiple authors including
	 * the given artist should also be considered in the result.
	 *
	 * @param artist artist name
	 * @return number of active years If the dataset is empty or there are no tracks
	 *         by the given artist in the dataset, return 0.
	 */
	public int getArtistActiveYears(String artist) {
		List<SpotifyTrack> tracksByArtist = trackRepository.getTracksByArtist(artist);
		
		if (tracksByArtist.size() == 0) {
			return 0;
		}

		IntSummaryStatistics summaryStatistics = tracksByArtist.stream()
				.mapToInt(SpotifyTrack::getYear)
				.summaryStatistics();

		int activeYears = (summaryStatistics.getMax() - summaryStatistics.getMin()) + 1;
		
		return activeYears;
	}

	/**
	 * Returns the @n tracks with highest valence from the 80s. Note that the 80s
	 * started in 1980 and lasted until 1989, inclusive. Valence describes the
	 * musical positiveness conveyed by a track. Tracks with high valence sound more
	 * positive (happy, cheerful, euphoric), while tracks with low valence sound
	 * more negative (sad, depressed, angry).
	 *
	 * @param n number of tracks to return If @n exceeds the total number of tracks
	 *          from the 80s, return all tracks available from this period.
	 * @return unmodifiable list of tracks sorted by valence in descending order
	 * @throws IllegalArgumentException in case @n is a negative number.
	 */
	public List<SpotifyTrack> getTopNHighestValenceTracksFromThe80s(int n) {
		ensurePositive(n);
		
		final int startYearOf80s = 1980;
		final int endYearOf80s = 1990;
		
		List<SpotifyTrack> eightiesTracks = trackRepository.getAllBetweenYears(startYearOf80s, endYearOf80s);
		
		List<SpotifyTrack> topNByValence = eightiesTracks.stream()
			.sorted(Comparator.comparing(SpotifyTrack::getValence).reversed())
			.limit(n)
			.collect(Collectors.toList());
		
		return topNByValence;
	}

	private void ensurePositive(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Number cannot be negative. The number you entered: " + n);
		}
	}

	/**
	 * Returns the most popular track from the 90s. Note that the 90s started in
	 * 1990 and lasted until 1999, inclusive. The value is between 0 and 100, with
	 * 100 being the most popular.
	 *
	 * @return the most popular track of the 90s. If there more than one tracks with
	 *         equal highest popularity, return any of them
	 * @throws NoSuchElementException if there are no tracks from the 90s in the
	 *                                dataset
	 */
	public SpotifyTrack getMostPopularTrackFromThe90s() {
		final int startYearOf90s = 1990;
		final int endYearOf90s = 2000;
		
		List<SpotifyTrack> nintiesTracks = trackRepository.getAllBetweenYears(startYearOf90s, endYearOf90s);
		
		Optional<SpotifyTrack> mostPopularTrackOptional = nintiesTracks.stream()
				.max(Comparator.comparing(SpotifyTrack::getPopularity));
		
		if (!mostPopularTrackOptional.isPresent()) {
			throw new NoSuchElementException("There are no tracks from the 90s in the dataset.");
		}
		
		return mostPopularTrackOptional.get();
	}

	/**
	 * Returns the number of tracks longer than @minutes released before @year.
	 *
	 * @param minutes
	 * @param year
	 * @return the number of tracks longer than @minutes released before @year
	 * @throws IllegalArgumentException in case @minutes or @year is a negative
	 *                                  number
	 */
	public long getNumberOfLongerTracksBeforeYear(int minutes, int year) {
		ensurePositive(minutes);
		ensurePositive(year);
		
		List<SpotifyTrack> tracksBeforeYear = trackRepository.getAllTracksBefore(year);
		
		long numberOfTracks = tracksBeforeYear.stream()
			.mapToDouble(t -> milisecondsToMinutes(t.getDuration()))
			.filter(duration -> (duration > minutes))
			.count();
		
		return numberOfTracks;
	}

	private double milisecondsToMinutes(long durationInMs) {
		return durationInMs / 60000;
	}

	/**
	 * Returns the loudest track released in a given year
	 *
	 * @param year
	 * @return the loudest track released in a given year
	 * @throws IllegalArgumentException in case @year is a negative number
	 */
	public Optional<SpotifyTrack> getTheLoudestTrackInYear(int year) {
		ensurePositive(year);
		
		List<SpotifyTrack> tracksByYear = trackRepository.getAllByYear(year);
		
		Optional<SpotifyTrack> loudestTrackOpt = tracksByYear.stream()
			.sorted(Comparator.comparing(SpotifyTrack::getLoudness).reversed())
			.findFirst();
		
		return loudestTrackOpt;
	}

}
