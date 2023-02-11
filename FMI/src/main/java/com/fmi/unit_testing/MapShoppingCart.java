package com.fmi.unit_testing;

import com.fmi.unit_testing.item.Item;

import java.util.*;
import java.util.stream.Collectors;

public class MapShoppingCart implements ShoppingCart {
	private final Map<Item, Integer> items;
	private final ProductCatalog catalog;

	public MapShoppingCart(ProductCatalog catalog) {
		this.items = new HashMap<>();
		this.catalog = catalog;
	}

	public Collection<Item> getUniqueItems() {
		Set<Item> copyOfItemKeys = new HashSet<>(items.keySet());
		
		return copyOfItemKeys;
	}

	@Override
	public void addItem(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("Item cannot be null.");
		}
		
		items.putIfAbsent(item, 0);
		int newCount = (items.get(item) + 1);
		items.put(item, newCount);
	}

	@Override
	public void removeItem(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("Item cannot be null.");
		}

		if (!items.containsKey(item)) {
			throw new ItemNotFoundException("There is no item with id " + item.getId() + " to remove.");
		}

		int newOccurrences = items.get(item) - 1;

		if (newOccurrences == 0) {
			items.remove(item);
		} else {
			items.put(item, newOccurrences);
		}
	}

	@Override
	public double getTotal() {
		double total = 0;

		for (Map.Entry<Item, Integer> entry : items.entrySet()) {
			ProductInfo info = catalog.getProductInfo(entry.getKey().getId());
			total += info.getPrice() * entry.getValue();
		}

		return total;
	}

	@Override
	public Collection<Item> getSortedItems() {
		List<Item> sortedItems = items.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.map(e -> e.getKey())
				.collect(Collectors.toList());

		return sortedItems;
	}

}
