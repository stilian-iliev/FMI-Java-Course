package bg.sofia.uni.fmi.mjt.race.track;

public class Car extends Thread{
    private final int id;
    private final int pitStops;
    private final Track track;

    public Car(int id, int nPitStops, Track track) {
        this.id = id;
        this.pitStops = nPitStops;
        this.track = track;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Method not yet implemented");
    }

    public int getCarId() {
        return this.id;
    }

    public int getNPitStops() {
        return this.pitStops;
    }

    public Track getTrack() {
        return this.track;
    }
}
