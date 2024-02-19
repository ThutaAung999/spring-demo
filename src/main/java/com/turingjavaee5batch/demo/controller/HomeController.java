package com.turingjavaee5batch.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.turingjavaee5batch.demo.model.ShoppingCart;
import com.turingjavaee5batch.demo.model.Store;
import com.turingjavaee5batch.demo.services.ArithmeticService;
import com.turingjavaee5batch.demo.services.PrototypeService;
import com.turingjavaee5batch.demo.services.impl.ExampleBean;
import com.turingjavaee5batch.demo.services.impl.HelloMessageGeneratorCopy;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

	@Value("${catalog.name}")
	String catalog;

	int a;

	@Autowired
	PrototypeService prototypeService;

	@Autowired
	Store<Integer> integerStore;

	@Autowired
	// @Qualifier("arithTwo")
	ArithmeticService arithmeticService;

	/*
	 * setter injection
	 * 
	 * private ArithmeticService arithmeticService; public void
	 * setArithmeticService(ArithmeticService arithmeticService) {
	 * log.info("setter-based DI"); this.arithmeticService = arithmeticService; }
	 */
	/*
	 * @Autowired ExampleBean exampleBean;
	 */
	HomeController(ExampleBean exampleBean) {// consturctor injection
		log.info("HomeController  created");
	}

	@GetMapping("/")
	String home() {

		try {
			log.info("Catalog :" + catalog);
			log.info("Handle by Thread  :" + Thread.currentThread().getName());
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * log.info("Home controller :" + this.a); log.info("Get /");
		 * log.info("Service :" + arithmeticService); log.info(" add(1+2) " +
		 * arithmeticService.add(1, 2));
		 */

		a++;

		return "home";
	}

	@GetMapping("/prototype")
	public String prototypeScopeTest() {
		log.info("Prototype SCOPE:" + this.prototypeService);

		return "prototype";
	}

	// @Resource(name = "requestScopedBean")
	// HelloMessageGenerator requestScopedBean;

	/*
	 * @GetMapping("/scopes/request") public String getRequestScopeMessage(final
	 * Model model) { log.info("Request bean scop Example :" +
	 * this.requestScopedBean); model.addAttribute("previousMessage",
	 * requestScopedBean.getMessage());
	 * requestScopedBean.setMessage("Good morning!");
	 * model.addAttribute("currentMessage", requestScopedBean.getMessage()); return
	 * "scopesExample"; }
	 */

	@GetMapping("/search")
	public String search(@RequestParam Integer query) {
		log.info("Query :" + query);
		return "search";
	}

	@Resource(name = "appScopeBean")
	HelloMessageGeneratorCopy appScopeBean;

	@GetMapping("/scopes/application")
	public String getAppScopeMessage(final Model model) {
		log.info("Application bean scop Example :" + this.appScopeBean);
		model.addAttribute("previousMessage", appScopeBean.getMessage());
		// requestScopedBean.setMessage("Good morning!");
		model.addAttribute("currentMessage", appScopeBean.getMessage());

		return "appScopesExample";
	}

	@GetMapping("/cart/json")
	@ResponseBody
	ShoppingCart getShoppingCart() {
		ShoppingCart cart = new ShoppingCart();
		cart.addToCart("One");
		cart.addToCart("Item Two");
		return cart;
	}
}
