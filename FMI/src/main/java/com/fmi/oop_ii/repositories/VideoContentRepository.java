package com.fmi.oop_ii.repositories;

import com.fmi.oop_ii.exceptions.ContentNotFoundException;
import com.fmi.oop_ii.models.content.Streamable;

import java.util.Arrays;

public class VideoContentRepository {
	private Streamable[] contents;
	
	public VideoContentRepository(Streamable[] contents) {		
		this.contents = contents;
	}
	
	public Streamable findByTitle(String title) {
		return Arrays.stream(contents)
				.filter(c -> c.getTitle().equals(title))
				.findFirst()
				.orElseThrow(() -> new ContentNotFoundException(String.format("Content %s not found.", title)));
	}
}
