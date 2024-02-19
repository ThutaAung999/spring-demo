package com.turingjavaee5batch.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


public class ShoppingCart {
	
	List<String> items;

	public ShoppingCart() {
		this.items = new ArrayList<String>();
	}

	public void addToCart(String item) {
		this.items.add(item);
	}

	public List<String> getItems() {
		return this.items;
	}

}
