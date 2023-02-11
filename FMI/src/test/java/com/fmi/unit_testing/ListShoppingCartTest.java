package com.fmi.unit_testing;

public class ListShoppingCartTest extends ShoppingCartTest{

	@Override
	protected ShoppingCart createCart(ProductCatalog catalog) {
		return new ListShoppingCart(catalog);
	}

}
