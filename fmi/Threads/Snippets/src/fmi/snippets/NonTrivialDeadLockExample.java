package fmi.snippets;

import fmi.snippets.models.BankAccount;

public class NonTrivialDeadLockExample {
    public static void main(String[] args) throws InterruptedException {
        BankAccount ivan = new BankAccount("Ivan", 1000);
        BankAccount ivan2 = new BankAccount("Ivan", 1000);
        BankAccount pesho = new BankAccount("Pesho", 1000);

        Thread t1 = new Thread(() -> smartTransfer(ivan, pesho, 100));
        Thread t2 = new Thread(() -> smartTransfer(pesho, ivan, 100));
        Thread t3 = new Thread(() -> smartTransfer(ivan, ivan2, 100));
        Thread t4 = new Thread(() -> smartTransfer(ivan2, ivan, 100));
        Thread t5 = new Thread(() -> smartTransfer(pesho, pesho, 100));
        Thread t6 = new Thread(() -> smartTransfer(pesho, pesho, 100));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();

        System.out.println(ivan);
        System.out.println(ivan2);
        System.out.println(pesho);



    }

    public static void transfer(BankAccount source, BankAccount destination, double amount){
        synchronized (source) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (destination) {
                source.withdraw(amount);
                destination.deposit(amount);
            }
        }
    }

    public static void smartTransfer(BankAccount source, BankAccount destination, double amount){
        BankAccount first;
        BankAccount second;

        if (source.hashCode() - destination.hashCode() < 0) {
            first = source;
            second = destination;
        } else {
            first = destination;
            second = source;
        }

        synchronized (first){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (second) {
                source.withdraw(amount);
                destination.deposit(amount);
            }
        }
    }
}
