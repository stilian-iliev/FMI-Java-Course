package com.fmi.oop_ii.services;

import com.fmi.oop_ii.exceptions.ContentNotFoundException;
import com.fmi.oop_ii.exceptions.ContentUnavailableException;
import com.fmi.oop_ii.exceptions.UserNotFoundException;
import com.fmi.oop_ii.models.content.Streamable;

public interface StreamingService {

    /**
    * Simulates watching activity for the given user.
    * @param username the user that will watch the video. The user must be registered in the platform in order to access its contents.
    * @param videoContentName the exact name of the video content: movie or series
    *                         If the content is of type Series, we assume that the user will watch all episodes in it.
    * @throws ContentUnavailableException if the content is age restricted and the user is not yet permitted to access it.
    * @throws UserNotFoundException if the user is not registered in the platform.
    * @throws ContentNotFoundException if the content is not present in the platform.
    */
    void watch(String username, String videoContentName) throws ContentUnavailableException;

   /**
    * @param videoContentName the exact name of the video content: movie or series
    * @return the Streamable resource with name that matches the provided name or null if no such content exists in the platform.
    */
    Streamable findByName(String videoContentName);

    /**
    * @return the most watched Streamable resource available in the platform or null if no streams were done yet.
    */
    Streamable mostViewed();

    /**
    * @return the minutes spent by all users registered in the platform while watching streamable content.
    */
    int totalWatchedTimeByUsers();

}
