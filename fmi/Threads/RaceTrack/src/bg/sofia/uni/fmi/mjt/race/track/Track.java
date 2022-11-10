package bg.sofia.uni.fmi.mjt.race.track;

import bg.sofia.uni.fmi.mjt.race.track.pit.Pit;

import java.util.List;

public interface Track {
    /**
     * A car enters the bg.sofia.uni.fmi.mjt.race.track.pit when it needs maintenance or when it finishes the race.
     * A car can finish the race, if it has no more pitStops to be done.
     *
     * @param car - the car which enters the bg.sofia.uni.fmi.mjt.race.track.pit
     */
    void enterPit(Car car);

    /**
     * Returns the number of cars which already finished the race.
     *
     * @return the number of cars which already finished the race
     */
    int getNumberOfFinishedCars();

    /**
     * Returns the ids of the cars which already finished the race.
     *
     * @return the ids of the cars which already finished the race
     */
    List<Integer> getFinishedCarsIds();

    /**
     * Returns the bg.sofia.uni.fmi.mjt.race.track.pit.
     *
     * @return the bg.sofia.uni.fmi.mjt.race.track.pit
     */
    Pit getPit();
}
