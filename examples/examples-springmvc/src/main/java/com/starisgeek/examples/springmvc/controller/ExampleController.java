package com.starisgeek.examples.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/examples/springmvc")
public class ExampleController {

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@GetMapping("/simpleResponse")
	@ResponseBody
	public String simpleResponse() {
		return "simple response";
	}

	@GetMapping("/simpleJsonResponse")
	@ResponseBody
	public Example simpleJsonResponse() {
		Example e = new Example();
		e.setV(79);
		return e;
	}

	public static class Example {
		private int v;

		public int getV() {
			return v;
		}

		public void setV(int v) {
			this.v = v;
		}

	}
}
