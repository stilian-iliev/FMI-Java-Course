package bg.sofia.uni.fmi.mjt.race.track.pit;

import bg.sofia.uni.fmi.mjt.race.track.Car;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Pit {
    private List<PitTeam> teams;
    private final AtomicInteger stoppedCount;
    private final Queue<Car> pitQueue;
    public Pit(int nPitTeams) {
        this.teams = new ArrayList<>();
        this.stoppedCount = new AtomicInteger();
        this.pitQueue = new ArrayDeque<>();

        for (int i = 0; i < nPitTeams; i++) {
            PitTeam team = new PitTeam(i, this);
            teams.add(team);
            team.start();
        }
    }

    public void submitCar(Car car) {
        stoppedCount.incrementAndGet();

    }

    public Car getCar() {
        return pitQueue.poll();
    }

    public int getPitStopsCount() {
        return stoppedCount.get();
    }

    public List<PitTeam> getPitTeams() {
        return Collections.unmodifiableList(teams);
    }

    public void finishRace() {
        throw new UnsupportedOperationException("Method not yet implemented");
    }

}
