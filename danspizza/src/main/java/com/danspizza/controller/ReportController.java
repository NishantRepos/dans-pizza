package com.danspizza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.danspizza.bean.BillRangeBean;
import com.danspizza.bean.PizzaOrderBean;
import com.danspizza.service.PizzaService;

@Controller
@RequestMapping("report")
public class ReportController {

	// Injecting PizzaService Bean
	@Autowired
	private PizzaService pizzaService;
	
	// GET Request - loads OrderReport view with empty BillRangeBean object
	@GetMapping("/load")
	public ModelAndView loadDateRangeReportPage(ModelAndView mv) {
		
		BillRangeBean billRangeBean = new BillRangeBean();
		
		mv.addObject("billRangeBean", billRangeBean);
		mv.setViewName("OrderReport");
		
		System.out.println("In loadDateRangeReportPageMethod: " + billRangeBean);
		
		return mv;
	}
	
	// POST Request - gets the details of orders within the specified bill range
	@PostMapping("/getdetails")
	public ModelAndView getOrderDetails(@ModelAttribute("billRangeBean") BillRangeBean billRangeBean) throws Exception {
		
		List<PizzaOrderBean> listPizzaOrderBean =
			 pizzaService.getOrderDetails(billRangeBean.getFromPrice(), billRangeBean.getToPrice());
		
		if (listPizzaOrderBean.isEmpty()) {
			throw new Exception("No records were found for the entered Bill Range");
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("listPizzaOrderBean", listPizzaOrderBean);
		mv.setViewName("OrderReport");
		
		return mv;
		
	}
	
	// Common exception handler for this controller
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllExceptions(Exception exception) {

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("GeneralizedExceptionHandlerPage");
		modelAndView.addObject("message", exception.getMessage());

		return modelAndView;
	}
	
}






