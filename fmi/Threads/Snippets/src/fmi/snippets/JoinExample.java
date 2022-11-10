package fmi.snippets;

public class JoinExample {
    public static void main(String[] args) {
        Thread bombTimer = new TimerBomb();
        bombTimer.start();
        try {
            bombTimer.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Boom!");
    }
}

class TimerBomb extends Thread {
    private final String[] count = new String[]{"five", "four", "three", "two", "one"};

    @Override
    public void run() {
        for (String s : count) {
            System.out.println(s);
        }
    }
}
