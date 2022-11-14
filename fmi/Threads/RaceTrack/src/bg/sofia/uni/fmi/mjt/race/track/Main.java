package bg.sofia.uni.fmi.mjt.race.track;

import bg.sofia.uni.fmi.mjt.race.track.pit.PitTeam;

import java.util.Optional;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Track track = new RaceTrack(1);

        Car car1 = new Car(1, 3, track);
        Car car2 = new Car(2, 3, track);
        Car car3 = new Car(3, 3, track);

        car1.start();
        car2.start();
        car3.start();

        car1.join();
        car2.join();
        car3.join();
        track.getPit().finishRace();
        track.getPit().getPitTeams().forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        System.out.println("race finished");

        System.out.println(car1.getNPitStops());
        System.out.println(car2.getNPitStops());
        System.out.println(car3.getNPitStops());

        track.getFinishedCarsIds().forEach(System.out::println);

        System.out.println(track.getNumberOfFinishedCars());

        System.out.println(track.getPit().getPitStopsCount());

        Optional<Integer> first = track.getPit().getPitTeams().stream().map(PitTeam::getPitStoppedCars).findFirst();

        System.out.println(first.get());

    }
}
