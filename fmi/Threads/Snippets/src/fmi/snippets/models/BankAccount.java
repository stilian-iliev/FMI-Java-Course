package fmi.snippets.models;

import java.util.concurrent.atomic.AtomicInteger;

public class BankAccount {

    // You can test removing explicit synchronization and use AtomicInteger here
    private static final AtomicInteger opCount = new AtomicInteger();

    // Holder's name
    protected String name;

    // Balance
    protected double balance = 0;

    public BankAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public BankAccount(String name) {
        this.name = name;
    }

    public synchronized void deposit(double amount) {
        this.balance += amount;
        incrementOpCount();
    }

    public synchronized void withdraw(double amount) {
        this.balance -= amount;
        incrementOpCount();
    }

    public String getName() {
        return name;
    }

    // is this thread-safe?
    public static void incrementOpCount() {
        opCount.incrementAndGet();
    }

    // is this thread-safe?
    public static int getOpCount() {
        return opCount.get();
    }

    @Override
    public String toString() {
        return name + " balance is " + balance;
    }

}