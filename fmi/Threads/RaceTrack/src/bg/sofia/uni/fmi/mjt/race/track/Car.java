package bg.sofia.uni.fmi.mjt.race.track;

public class Car extends Thread{
    private final int id;
    private int pitStops;
    private final Track track;

    public Car(int id, int nPitStops, Track track) {
        this.id = id;
        this.pitStops = nPitStops;
        this.track = track;
    }

    @Override
    public void run() {
        while (pitStops != 0) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            track.enterPit(this);
            pitStops--;
            System.out.printf("Car %s enters the pit.%n", id);
        }
        System.out.printf("Car %s finished the race.%n", id);
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
