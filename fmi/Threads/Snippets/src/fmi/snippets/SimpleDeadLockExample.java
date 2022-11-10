package fmi.snippets;

public class SimpleDeadLockExample {
    public static void main(String[] args) {
        Object oil = new Object();
        Object vinegar = new Object();

        Thread friend1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (oil){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (vinegar){
                        System.out.println("friend1 made his salad");
                    }
                }
            }
        });

        Thread friend2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (vinegar){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (oil){
                        System.out.println("friend1 made his salad");
                    }
                }
            }
        });

        friend1.start();
        friend1.start();
    }
}
