package fmi.snippets;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        NonAtomicInteger nonAtomicInteger = new NonAtomicInteger();

        Runnable r = () -> {
            for (int i = 0; i < 100_000; i++) {
                atomicInteger.incrementAndGet();
                nonAtomicInteger.getAndIncrement();
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        Thread t4 = new Thread(r);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Atomic " + atomicInteger.get());
        System.out.println("Non-Atomic " + nonAtomicInteger.get());

    }
}

class NonAtomicInteger {
    private int i = 0;

    public NonAtomicInteger() {
        i = 0;
    }

    public int getAndIncrement() {
        return i++;
    }

    public int get() {
        return i;
    }
}
