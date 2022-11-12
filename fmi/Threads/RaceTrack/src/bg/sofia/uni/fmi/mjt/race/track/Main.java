package bg.sofia.uni.fmi.mjt.race.track;

public class Main {
    public static void main(String[] args) {
        Track track = new RaceTrack(1);

        Car car1 = new Car(1, 2, track);
        Car car2 = new Car(2, 2, track);
        Car car3 = new Car(3, 2, track);



    }
}
