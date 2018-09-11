package com.starisgeek.examples.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/examples/springboot")
public class ExampleController {

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
}
