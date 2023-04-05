package com.kong.king.spring.exam01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam01")
public class Exam01Controller {
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
}
