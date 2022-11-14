package bg.sofia.uni.fmi.mjt.race.track.pit;

import bg.sofia.uni.fmi.mjt.race.track.Car;

import java.util.concurrent.atomic.AtomicInteger;

public class PitTeam extends Thread{
    private final int id;
    private final Pit pit;
    private final AtomicInteger stoppedCars;
    public PitTeam(int id, Pit pitStop) {
        this.stoppedCars = new AtomicInteger();
        this.id = id;
        this.pit = pitStop;
    }

    @Override
    public void run() {
        Car car;

        while ((car = pit.getCar()) != null) {
            try {
                Thread.sleep(200);
                System.out.printf("Team %s fixing car %s%n", this.id, car.getCarId());

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            stoppedCars.incrementAndGet();
        }
    }

    public int getPitStoppedCars() {
        return stoppedCars.get();
    }
}
