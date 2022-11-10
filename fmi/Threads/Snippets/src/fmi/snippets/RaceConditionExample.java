package fmi.snippets;

import fmi.snippets.models.BankAccount;
import fmi.snippets.models.Depositor;

public class RaceConditionExample {
    public static void main(String[] args) throws InterruptedException {
        BankAccount acc = new BankAccount("anonymous");

        Depositor[] depositors = new Depositor[5];

        for (int i = 0; i < depositors.length; i++) {
            depositors[i] = new Depositor(acc, 500);
            depositors[i].start();
        }

        for (Depositor d : depositors) {
            d.join();
        }

        System.out.println(acc);
        System.out.println("Operations: " + BankAccount.getOpCount());
    }
}
