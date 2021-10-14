package com.danspizza.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("greet")
public class TestController {
	
	@GetMapping("/hello")
	public ModelAndView hello(ModelAndView mv) {
		
		//ModelAndView mv = new ModelAndView();
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		String dateTimeFormatted = formatter.format(now);
		mv.addObject("dateTime", dateTimeFormatted);
		mv.setViewName("hello");
		
		return mv;
	}
	
	@PostMapping("/show")
	public ModelAndView show(@ModelAttribute("dateTime") ModelAndView theModel) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("dateTime", theModel);
		mv.setViewName("hello-confirmation");
		
		return mv;		
	}
}















