package com.fmi.unit_testing;

public class MapShoppingCartTest extends ShoppingCartTest{

	@Override
	protected ShoppingCart createCart(ProductCatalog catalog) {
		return new MapShoppingCart(catalog);
	}

}
