package com.turingjavaee5batch.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turingjavaee5batch.demo.model.ShoppingCart;

@RestController
@RequestMapping("/api")
public class ApiController {

	/*ဒါက အဆင်ပြေတယ်
	@GetMapping
	 List<String> getShoppingCart() {
		ShoppingCart cart= new ShoppingCart();
		cart.addToCart("One");
		cart.addToCart("Item Two");
		return cart.getItems();
	}
	*/
	
	@GetMapping
	ShoppingCart getShoppingCart() {
		ShoppingCart cart= new ShoppingCart();
		cart.addToCart("One");
		cart.addToCart("Item Two");
		return cart;
	}
	
}
 