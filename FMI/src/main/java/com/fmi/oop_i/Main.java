package com.fmi.oop_i;

import com.fmi.oop_i.account.Account;
import com.fmi.oop_i.account.BGNAccount;
import com.fmi.oop_i.account.EURAccount;
import com.fmi.oop_i.card.Card;
import com.fmi.oop_i.card.VirtualCard;

import java.time.LocalDate;

public class Main {
	public static void main(String[] args) {
		Account acc1 = new BGNAccount("123");
		Account acc2 = new EURAccount("321");

		Card card = new VirtualCard("num", 0000, LocalDate.now().plusYears(3));

		Revolut revolut = new Revolut(new Account[] { acc1, acc2 }, new Card[] { card });

//		card.block();
//		acc1.withdraw(20);

//		revolut.addMoney(acc1, 100);
//		revolut.addMoney(acc2, 100);

//		System.out.println(revolut.payOnline(card, 0, 40, BGNAccount.CURRENCY, ""));
//		System.out.println(revolut.transferMoney(acc1, acc2, 100));
//
//		System.out.println(acc1.getAmount());
//		System.out.println(acc2.getAmount());
//		
//		System.out.println(revolut.getTotalAmount());

//		System.out.println(card.checkPin(1));
//		System.out.println(card.checkPin(0000));
//		
//		System.out.println(card.isBlocked());
//		
//		card.block();
//		System.out.println(card.isBlocked());
		
		
		String a = "lskjfdla.biz/sadfsad"; // true
		String b = "isdjf.com/fsfdasi"; // false
		String c = "lsjfdlsjdfl.isjfijs.biz.slkdfjsl"; //false
		String d = "flksfdls.biz:8080/sfjsd"; // true
		String e = "kbiz.dsfdsh"; //false
		String f = "lsdkfjlsa.biz"; //true
		
		
		System.out.println(a.matches(String.format(".*%s(?!\\.).*", ".biz")));		
		System.out.println(b.matches(String.format(".*%s(?!\\.).*", ".biz")));
		System.out.println(c.matches(String.format(".*%s(?!\\.).*", ".biz")));
		System.out.println(d.matches(String.format(".*%s(?!\\.).*", ".biz")));
		System.out.println(e.matches(String.format(".*%s(?!\\.).*", ".biz")));
		System.out.println(f.matches(String.format(".*%s(?!\\.).*", ".biz")));
		
		
		
		
	}
}
