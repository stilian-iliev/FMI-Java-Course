package bg.sofia.uni.fmi.mjt.race.track.pit;

public class PitTeam extends Thread{
    private final int id;
    private final Pit pit;
    public PitTeam(int id, Pit pitStop) {
        this.id = id;
        this.pit = pitStop;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Method not yet implemented");
    }

    public int getPitStoppedCars() {
        return pit.getPitStopsCount();
    }
}
