package com.learning.rest.webservices.restfulwebservices;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.learning.rest.webservices.restfulwebservices.helloworld.HelloWorldBean;

//Controller 
@RestController
public class HelloWorldController {
	
	//GET 
	//uri - /hello-world
	//method  = "hELLO wORLD 
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping(path ="/hello-world")
	public String helloWorld() {
		return "Hello World ";
	}
	
	//hello-world-bean
	@GetMapping(path ="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean ("Hello World ");
	}
	
	//hello-world/path-variable/learning
	@GetMapping(path ="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name ) {
		return new HelloWorldBean (String.format("Hello World , %s ",name));
	}
	
	//hello-world/path-variable/learning
	@GetMapping(path ="/hello-world-internationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
	}
	



}