package com.danspizza.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
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
	
	@Autowired
	private MessageSource errorMessageSource;
	
	@Autowired
	private PizzaService pizzaService;

	// InitBinder to trim input strings
	// remove leading and trailing whitespaces
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/load")
	public ModelAndView loadPizza(ModelAndView modelAndView) {

		PizzaOrderBean pizzaOrderBean = new PizzaOrderBean();
		modelAndView.addObject("pizzaOrderBean", pizzaOrderBean);
		modelAndView.setViewName("PizzaOrder");
		//System.out.println("\nIn Controller Method loadPizza(): " + pizzaOrderBean);
		return modelAndView;
	}

	@PostMapping("/saveCustomer")
	public ModelAndView saveCustomer(@ModelAttribute("pizzaOrderBean") @Valid PizzaOrderBean pizzaOrderBean,
			BindingResult theBindingResult, ModelAndView modelAndView) {

		//System.out.println("BindingResult: " + theBindingResult);
		//System.out.println("PizzaOrderBean: Customer Name = |" + pizzaOrderBean.getCustomerName() + "|");

		// check whether any form validation failed or not.
		// if failed, populate ModelAndView object with PizzaOrder logical view name
		// to display the validation error message

		if (theBindingResult.hasErrors()) {
			//System.out.println("In saveCustomer checking binding result: " + theBindingResult);
			modelAndView.setViewName("PizzaOrder");
			modelAndView.addObject("pizzaOrderBean", pizzaOrderBean);

		} else {

			// On successful form validation
			//System.out.println("\nIn Controller method saveCustomer(): " + pizzaOrderBean);
			PizzaOrderBean thePizzaOrderBean = pizzaService.addPizzaOrderDetails(pizzaOrderBean);

			modelAndView.addObject("pizzaOrderBean", thePizzaOrderBean);
			//System.out.println("orderID: " + thePizzaOrderBean.getOrderId());
			modelAndView.setViewName("PizzaOrderSuccess");

		}

		return modelAndView;
	}

	@ModelAttribute("pizzaName")
	public Map<Integer, String> populatePizzaNames() {
		
		Map<Integer, String> pizzaName = pizzaService.findAllPizzaDetails();
		
		pizzaName.put(null, "Select");
		
		return pizzaName;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllExceptions(Exception exception) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", exception.getMessage());
		modelAndView.setViewName("GeneralizedExceptionHandlerPage");
		return modelAndView;
	}

}
