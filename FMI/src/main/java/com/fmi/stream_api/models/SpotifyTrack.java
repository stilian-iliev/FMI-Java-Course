package com.fmi.stream_api.models;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SpotifyTrack {
	private static final int ID_INDEX = 0;
	private static final int ARTISTS_INDEX = 1;
	private static final int NAME_INDEX = 2;
	private static final int YEAR_INDEX = 3;
	private static final int POPULARITY_INDEX = 4;
	private static final int DURATION_MS_INDEX = 5;
	private static final int TEMPO_INDEX = 6;
	private static final int LOUDNESS_INDEX = 7;
	private static final int VALENCE_INDEX = 8;
	private static final int ACOUSTICNESS_INDEX = 9;
	private static final int DANCEABILITY_INDEX = 10;
	private static final int ENERGY_INDEX = 11;
	private static final int LIVENESS_INDEX = 12;
	private static final int SPEECHINESS_INDEX = 13;
	private static final int EXPLICIT_INDEX = 14;
	private static final String IS_EXPLICIT = "1";
	private String id;
	private String name;
	private int year;
	private int popularity;
	private long duration;
	private double tempo;
	private double loudness;
	private double valence;
	private double acousticness;
	private double danceability;
	private double energy;
	private double liveness;
	private double speechiness;
	private boolean explicit;
	private Set<String> artists;

	/**
	 * Returns a SpotifyTrack instance, based on the given @{line} from the dataset.
	 *
	 * @param line line from the dataset text file
	 * @return SpotifyTrack instance
	 **/
	public SpotifyTrack (String line) {
		String[] tokens = line.split(",");

		this.id = tokens[ID_INDEX];
		this.artists = Arrays.stream((tokens[ARTISTS_INDEX].split(";")))
				.map(s -> s.trim().replace("[", "").replace("]", "").replaceAll("'", "")).collect(Collectors.toSet());

		this.name = tokens[NAME_INDEX];
		this.year = Integer.parseInt(tokens[YEAR_INDEX]);
		this.popularity = Integer.parseInt(tokens[POPULARITY_INDEX]);
		this.duration = Long.parseLong(tokens[DURATION_MS_INDEX]);
		this.tempo = Double.parseDouble(tokens[TEMPO_INDEX]);
		this.loudness = Double.parseDouble(tokens[LOUDNESS_INDEX]);
		this.valence = Double.parseDouble(tokens[VALENCE_INDEX]);
		this.acousticness = Double.parseDouble(tokens[ACOUSTICNESS_INDEX]);
		this.danceability = Double.parseDouble(tokens[DANCEABILITY_INDEX]);
		this.energy = Double.parseDouble(tokens[ENERGY_INDEX]);
		this.liveness = Double.parseDouble(tokens[LIVENESS_INDEX]);
		this.speechiness = Double.parseDouble(tokens[SPEECHINESS_INDEX]);
		this.explicit = tokens[EXPLICIT_INDEX].equals(IS_EXPLICIT);
	}

	public String getId() {
		return id;
	}
	
	public Set<String> getArtists() {
		return artists;
	}

	public String getName() {
		return name;
	}

	public int getYear() {
		return year;
	}

	public int getPopularity() {
		return popularity;
	}

	public long getDuration() {
		return duration;
	}

	public double getTempo() {
		return tempo;
	}

	public double getLoudness() {
		return loudness;
	}

	public double getValence() {
		return valence;
	}

	public double getAcousticness() {
		return acousticness;
	}

	public double getDanceability() {
		return danceability;
	}

	public double getEnergy() {
		return energy;
	}

	public double getLiveness() {
		return liveness;
	}

	public double getSpeechiness() {
		return speechiness;
	}

	public boolean isExplicit() {
		return explicit;
	}
	
	

}
