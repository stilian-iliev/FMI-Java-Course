package bg.sofia.uni.fmi.mjt.race.track;

import bg.sofia.uni.fmi.mjt.race.track.pit.Pit;

import java.util.List;

public class RaceTrack implements Track{
    private final Pit pit;

    public RaceTrack(int pitTeams) {
        this.pit = new Pit(pitTeams);
    }

    @Override
    public void enterPit(Car car) {
        pit.submitCar(car);
    }

    @Override
    public int getNumberOfFinishedCars() {
        return 0;
    }

    @Override
    public List<Integer> getFinishedCarsIds() {
        return null;
    }

    @Override
    public Pit getPit() {
        return this.pit;
    }
}
