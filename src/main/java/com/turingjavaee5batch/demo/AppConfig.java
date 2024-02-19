package com.turingjavaee5batch.demo;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.turingjavaee5batch.demo.model.ShoppingCart;
import com.turingjavaee5batch.demo.model.Store;
import com.turingjavaee5batch.demo.services.impl.ExampleBean;
import com.turingjavaee5batch.demo.services.impl.HelloMessageGenerator;
import com.turingjavaee5batch.demo.services.impl.HelloMessageGeneratorCopy;
import com.turingjavaee5batch.demo.services.impl.PrototypeBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class AppConfig implements WebMvcConfigurer {

	@Bean
	@Scope("prototype")
	public PrototypeBean getPrototypeBean() {
		log.info("Prototype Scope  created:");
		return new PrototypeBean();
	}

	@Bean
	// @RequestScope
	@Scope("request")
	public HelloMessageGenerator requestScopedBean() {
		log.info("RequestScopeBean created");
		return new HelloMessageGenerator();
	}

	@Bean
	@SessionScope
	public ShoppingCart shoppingCart() {
		log.info("Shopping cart created ");
		return new ShoppingCart();
	}

	@Bean
	@ApplicationScope
	public HelloMessageGeneratorCopy appScopeBean() {
		log.info("ApplicatonScopeBean created");
		return new HelloMessageGeneratorCopy();
	}

	@Bean
	public ExampleBean exampleBean2() {
		log.info("Inovke custom bean via factory method");
		return new ExampleBean();
	}

	@Bean
	public Store<String> stringStore() {
		return new Store<String>();
	}

	@Bean
	public Store<Integer> integerStore() {
		log.info("Integer Store created");
		return new Store<Integer>();
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}

	/*
	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource
	      = new ReloadableResourceBundleMessageSource();
	    
	    messageSource.setBasename("classpath:messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
	*/
	
}
