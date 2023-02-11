package com.fmi.unit_testing;

import com.fmi.unit_testing.item.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public abstract class ShoppingCartTest {

	private final String TEST_APPLE_ID = "apple-id";
	private final String TEST_CHOCOLATE_ID = "chocolate-id";
	private final double TEST_APPLE_PRICE = 1;
	private final double TEST_CHOCOLATE_PRICE = 2;
	
	private Item testApple;
	private Item testChocolate;
	
	ProductInfo testAppleProductInfo;
	ProductInfo testChocolateProductInfo;

	private ShoppingCart cart;

	@Mock
	private ProductCatalog catalog;
	
	protected abstract ShoppingCart createCart(ProductCatalog catalog);

	@Before
	public void setUp() {
		//make factory for objects
		//put mocks in the tests
		ItemFactory itemFactory = new ItemFactory();
		
		cart = createCart(catalog);
		//use constants instead of initializing every time
		//idea: make private method for mocking default test mocks
		//make constants for the item types for the factory or make different methods for chocolate and apple
		testApple = itemFactory.getItem("apple", TEST_APPLE_ID);
		testChocolate = itemFactory.getItem("chocolate", TEST_CHOCOLATE_ID);
		
		testAppleProductInfo = itemFactory.getItemInfo("apple", "apple description", TEST_APPLE_PRICE);
		testChocolateProductInfo = itemFactory.getItemInfo("chocolate", "chocolate description", TEST_CHOCOLATE_PRICE);
	}

	@After
	public void tearDown() {

	}
	
	@Test
	public void testGettingUniqueItemsReturnsRightItems() {
		cart.addItem(testApple);
		cart.addItem(testChocolate);
		
		Collection<Item> uniqueItems = cart.getUniqueItems();
		assertEquals(2, uniqueItems.size());
		
		boolean containsRightItems = uniqueItems.contains(testApple) && uniqueItems.contains(testChocolate);
		assertTrue(containsRightItems);
	}
	
	@Test
	public void testGettingUniqueItemsDoesNotIncludeDuplicates() {
		cart.addItem(testApple);
		cart.addItem(testApple);
		
		Collection<Item> uniqueItems = cart.getUniqueItems();
		assertEquals(1, uniqueItems.size());
		
		boolean containsRightItems = uniqueItems.contains(testApple);
		assertTrue(containsRightItems);
	}
	
	@Test
	public void testGettingSortedItemsReturnInRightOrder() {
		cart.addItem(testApple);
		cart.addItem(testApple);
		cart.addItem(testChocolate);
		
		Collection<Item> sortedItems = cart.getSortedItems();
		assertEquals(2, sortedItems.size());
		assertEquals(testApple, sortedItems.toArray()[0]);
		
		cart.addItem(testChocolate);
		cart.addItem(testChocolate);
		
		sortedItems = cart.getSortedItems();
		assertEquals(2, sortedItems.size());
		assertEquals(testChocolate, sortedItems.toArray()[0]);
	}

	@Test
	public void testAddingItemToCart() {
		when(catalog.getProductInfo(TEST_APPLE_ID)).thenReturn(testAppleProductInfo);
		when(catalog.getProductInfo(TEST_CHOCOLATE_ID)).thenReturn(testChocolateProductInfo);
		
		cart.addItem(testApple);
		cart.addItem(testChocolate);
		
		double expectedTotal = TEST_APPLE_PRICE + TEST_CHOCOLATE_PRICE;
		assertEquals(expectedTotal, cart.getTotal());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddingNullItemToCartShouldThrow() {
		cart.addItem(null);
	}
	
	@Test
	public void testAddingIdenticalItemToCartShouldIncreaseItemCount() {
		when(catalog.getProductInfo(TEST_APPLE_ID)).thenReturn(testAppleProductInfo);
		
		cart.addItem(testApple);
		cart.addItem(testApple);
		
		double expectedTotal = TEST_APPLE_PRICE * 2;
		assertEquals(expectedTotal, cart.getTotal());
	}
	
	@Test
	public void testRemovingExistingItemFromCart() {
		cart.addItem(testApple);
		cart.removeItem(testApple);
		
		double expectedTotal = 0;
		assertEquals(expectedTotal, cart.getTotal());
	}
	
	@Test
	public void testRemovingExistingItemFromCartRemovesOnlyOneInstance() {
		when(catalog.getProductInfo(TEST_APPLE_ID)).thenReturn(testAppleProductInfo);
		
		cart.addItem(testApple);
		cart.addItem(testApple);
		cart.removeItem(testApple);
		
		double expectedTotal = TEST_APPLE_PRICE;
		assertEquals(expectedTotal, cart.getTotal());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRemovingNullItemShouldThrow() {
		cart.removeItem(null);
	}
	
	@Test(expected = ItemNotFoundException.class)
	public void testRemovingNonExistingItemShouldThrow() {
		cart.addItem(testApple);
		cart.removeItem(testChocolate);
	}
	
	@Test
	public void testGetTotalShouldReturnTheSumOfAllItemsPrices() {
		when(catalog.getProductInfo(TEST_APPLE_ID)).thenReturn(testAppleProductInfo);
		when(catalog.getProductInfo(TEST_CHOCOLATE_ID)).thenReturn(testChocolateProductInfo);
		
		cart.addItem(testApple);
		cart.addItem(testApple);
		cart.addItem(testChocolate);
		
		double expectedTotal = (TEST_APPLE_PRICE * 2) + TEST_CHOCOLATE_PRICE;
		assertEquals(expectedTotal, cart.getTotal());
	}


}
