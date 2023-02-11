package com.fmi.unit_testing;

import com.fmi.unit_testing.item.Item;

import java.util.*;
import java.util.stream.Collectors;

public class ListShoppingCart implements ShoppingCart {
    private final ArrayList<Item> items;
    private final ProductCatalog catalog;

    public ListShoppingCart(ProductCatalog catalog) {
    	this.catalog = catalog;
        this.items = new ArrayList<>();
    }

    @Override
    public Collection<Item> getUniqueItems() {
        return new HashSet<>(items);
    }

    @Override
    public void addItem(Item item) {
    	if (item == null) {
			throw new IllegalArgumentException("Item cannot be null.");
		}
    	
        items.add(item);
    }

    @Override
    public void removeItem(Item item) {
    	if (item == null) {
			throw new IllegalArgumentException("Item cannot be null.");
		}
    
        boolean wasRemoved = items.remove(item);
        
        if (!wasRemoved) {
			throw new ItemNotFoundException("There is no item with id " + item.getId() + " to remove.");
		}
    }

    @Override
    public double getTotal() {
        double total = 0;
        
        for (Item item : items) {
        	ProductInfo info = catalog.getProductInfo(item.getId());
            total += info.getPrice();
        }
        
        return total;
    }

    @Override
    public Collection<Item> getSortedItems() {
        List<Item> sortedItemsByCount = createItemsToQuantityMap().entrySet()
        		.stream()
        		.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
        		.map(e -> e.getKey())
        		.collect(Collectors.toList());
        
        return sortedItemsByCount;
    }

    private Map<Item, Integer> createItemsToQuantityMap() {
        Map<Item, Integer> itemToQuantity = new HashMap<>();
        
        for (Item item : items) {
        	itemToQuantity.putIfAbsent(item, 0);
        	int newCount = (itemToQuantity.get(item) + 1);
            itemToQuantity.put(item, newCount);
        }
        
        return itemToQuantity;
    }
}

