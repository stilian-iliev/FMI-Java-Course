package com.fmi.generics;

import com.fmi.generics.exceptions.CapacityExceededException;
import com.fmi.generics.exceptions.ParcelNotFoundException;
import com.fmi.generics.services.impl.MJTExpressWarehouse;

import java.time.LocalDateTime;

public class Main {
	public static void main(String[] args) throws CapacityExceededException, InterruptedException, ParcelNotFoundException {
		MJTExpressWarehouse<Integer, String> wh = new MJTExpressWarehouse<>(10, 0);
		
		wh.submitParcel(1, "a", LocalDateTime.now());
		wh.submitParcel(2, "b", LocalDateTime.now());
		System.out.println(wh.getWarehouseSpaceLeft());
		wh.submitParcel(3, "v", LocalDateTime.now());
		System.out.println(wh.getWarehouseSpaceLeft());
//		wh.submitParcel(4, "a", LocalDateTime.now().plusHours(1));
	
		
		System.out.println(wh.deliverParcel(1));
		System.out.println(wh.getWarehouseSpaceLeft());
		System.out.println();
	}
}
