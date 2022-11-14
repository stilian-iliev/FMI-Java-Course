package bg.sofia.uni.fmi.mjt.race.track;

import bg.sofia.uni.fmi.mjt.race.track.pit.Pit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RaceTrack implements Track {
    private final Pit pit;
    private final List<Integer> finishedCarsIds;

    public RaceTrack(int pitTeams) {
        this.pit = new Pit(pitTeams);
        this.finishedCarsIds = new ArrayList<>();
    }

    @Override
    public void enterPit(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Invalid car arg");
        }

        if (car.getNPitStops() == 0) {
            finishedCarsIds.add(car.getCarId());
            System.out.println("Car " + car.getCarId() + " finished.");

        } else {
            pit.submitCar(car);
        }

    }

    @Override
    public int getNumberOfFinishedCars() {
        return finishedCarsIds.size();
    }

    @Override
    public List<Integer> getFinishedCarsIds() {
        return Collections.unmodifiableList(finishedCarsIds);
    }

    @Override
    public Pit getPit() {
        return this.pit;
    }
}
