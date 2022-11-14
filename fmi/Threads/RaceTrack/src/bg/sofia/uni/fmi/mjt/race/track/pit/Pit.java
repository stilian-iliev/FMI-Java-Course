package bg.sofia.uni.fmi.mjt.race.track.pit;

import bg.sofia.uni.fmi.mjt.race.track.Car;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Pit {
    private List<PitTeam> teams;
    private final AtomicInteger stoppedCount;
    private final ConcurrentLinkedQueue<Car> pitQueue;
    private boolean finished;

    public Pit(int nPitTeams) {
        this.teams = new ArrayList<>();
        this.stoppedCount = new AtomicInteger();
        this.pitQueue = new ConcurrentLinkedQueue<>();
        finished = false;

        for (int i = 0; i < nPitTeams; i++) {
            PitTeam team = new PitTeam(i, this);
            teams.add(team);
            team.start();
        }
    }

    public void submitCar(Car car) {
        if (finished) return;
        stoppedCount.incrementAndGet();


        synchronized (this) {
            pitQueue.add(car);
            this.notifyAll();
        }
    }

    public synchronized Car getCar() {
        while (pitQueue.isEmpty() && !finished){
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return pitQueue.isEmpty() ? null : pitQueue.poll();
    }

    public int getPitStopsCount() {
        return stoppedCount.get();
    }

    public List<PitTeam> getPitTeams() {
        return Collections.unmodifiableList(teams);
    }

    public synchronized void finishRace() {
        this.finished = true;
        notifyAll();
    }

}
