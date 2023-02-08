package com.fmi.oop_ii.models.content;

import com.fmi.oop_ii.models.content.enumerations.PgRating;

public interface Streamable {
    /**
    * @return the title of the streamable content.
    */
    String getTitle();

    /**
    * @return the content duration in minutes.
    */
    int getDuration();

    /**
    * @return the PG rating of the streamable content.
    */
    PgRating getRating();
    
    void watch();
}