package com.fmi.unit_testing;

import com.fmi.unit_testing.item.Apple;
import com.fmi.unit_testing.item.Chocolate;
import com.fmi.unit_testing.item.Item;

public class ItemFactory {
	//create instead of get
	public Item getItem(String itemType, String itemId) {
		switch (itemType) {
		case "apple":
			return new Apple(itemId);
		case "chocolate":
			return new Chocolate(itemId);

		default:
			throw new IllegalArgumentException();
		}
	}
	
	public ProductInfo getItemInfo(String name, String description, double price) {
		return new ProductInfo(name, description, price);
	}

}
