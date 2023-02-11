package com.fmi.unit_testing;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductInfoTest {
	private ProductInfo productInfo;
	
	@After
	public void tearDown() {
		productInfo = null;
	}

	@Test
	public void testMakingProductInfoWithNullArgumentsShouldNotFail() {
		productInfo = new ProductInfo(null, null, 0);
		
		assertEquals(null, productInfo.getName());
		assertEquals(null, productInfo.getDescription());
		assertEquals(0, productInfo.getPrice());
	}
	
	@Test
	public void testMakingProductInfo() {
		productInfo = new ProductInfo("testName", "testName", 1);
		
		assertEquals("testName", productInfo.getName());
		assertEquals("testName", productInfo.getDescription());
		assertEquals(1, productInfo.getPrice());
	}
	
	@Test
	public void testMakingProductInfoWithNegativePriceShouldNotFail() {
		productInfo = new ProductInfo("testName", "testName", -1);
		
		assertEquals("testName", productInfo.getName());
		assertEquals("testName", productInfo.getDescription());
		assertEquals(-1, productInfo.getPrice());
	}

}
