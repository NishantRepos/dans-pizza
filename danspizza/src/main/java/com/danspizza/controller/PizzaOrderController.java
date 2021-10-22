package com.danspizza.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.danspizza.bean.PizzaOrderBean;
import com.danspizza.service.PizzaService;

@Controller
@RequestMapping("order")
public class PizzaOrderController {
	
	// Injecting PizzaService Bean
	@Autowired
	private PizzaService pizzaService;

	// InitBinder to trim input strings
	// remove leading and trailing whitespaces
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	// GET Request - loads PizzaOrder view with empty PizzaOrderBean object
	@GetMapping("/load")
	public ModelAndView loadPizza(ModelAndView modelAndView) {

		PizzaOrderBean pizzaOrderBean = new PizzaOrderBean();
		modelAndView.addObject("pizzaOrderBean", pizzaOrderBean);
		modelAndView.setViewName("PizzaOrder");
		return modelAndView;
	}

	// POST Request - saves the valid customer
	@PostMapping("/saveCustomer")
	public ModelAndView saveCustomer(@ModelAttribute("pizzaOrderBean") @Valid PizzaOrderBean pizzaOrderBean,
			BindingResult theBindingResult, ModelAndView modelAndView) {

		// check whether any form validation failed or not.
		if (theBindingResult.hasErrors()) {
			
			modelAndView.setViewName("PizzaOrder");
			modelAndView.addObject("pizzaOrderBean", pizzaOrderBean);

		} else {

			// On successful form validation
			PizzaOrderBean thePizzaOrderBean = pizzaService.addPizzaOrderDetails(pizzaOrderBean);

			modelAndView.addObject("pizzaOrderBean", thePizzaOrderBean);
			modelAndView.setViewName("PizzaOrderSuccess");

		}

		return modelAndView;
	}

	// get all pizza names
	@ModelAttribute("pizzaName")
	public Map<Integer, String> populatePizzaNames() {
		
		Map<Integer, String> pizzaName = pizzaService.findAllPizzaDetails();
		
		pizzaName.put(null, "Select");
		
		return pizzaName;
	}

	// common exception handler for this controller
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllExceptions(Exception exception) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", exception.getMessage());
		modelAndView.setViewName("GeneralizedExceptionHandlerPage");
		return modelAndView;
	}

}
