package com.danspizza.service;

import java.util.List;
import java.util.Map;

import com.danspizza.bean.PizzaOrderBean;

public interface PizzaService {

	public List<PizzaOrderBean> getOrderDetails(Double fromBill, Double toBill);
	
	public PizzaOrderBean addPizzaOrderDetails(PizzaOrderBean pizzaOrderBean);
	
	public Map<Integer, String> findAllPizzaDetails();
	
}
