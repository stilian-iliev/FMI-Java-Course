package fmi.snippets;

public class DaemonThreads {
    public static void main(String[] args) {
        Thread backgroundTask = new BackgroundTask();

        backgroundTask.start();


        System.out.println("Main thread terminated");
    }
}

class BackgroundTask extends Thread {
    public BackgroundTask() {
        setDaemon(true);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
        System.out.printf("%s thread terminated%n", isDaemon() ? "daemon" : "non-daemon");
    }
}
