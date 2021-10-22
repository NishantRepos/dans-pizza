package com.danspizza.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danspizza.bean.PizzaBean;
import com.danspizza.bean.PizzaOrderBean;
import com.danspizza.dao.PizzaDAOWrapper;

@Service
public class PizzaServiceImpl implements PizzaService {

	@Autowired
	private PizzaDAOWrapper pizzaDAOWrapper;
	
	@Override
	@Transactional
	public List<PizzaOrderBean> getOrderDetails(Double fromBill, Double toBill) {

		return pizzaDAOWrapper.getOrderDetails(fromBill, toBill);

	}

	@Override
	@Transactional
	public PizzaOrderBean addPizzaOrderDetails(PizzaOrderBean pizzaOrderBean) {
		
		Double price = pizzaDAOWrapper.getPizzaPrice(pizzaOrderBean.getPizzaId());
		
		Double bill = price * pizzaOrderBean.getNumberOfPiecesOrdered();
		
		pizzaOrderBean.setBill(bill);
		
		return pizzaDAOWrapper.addPizzaOrderDetails(pizzaOrderBean);
	}

	@Override
	@Transactional
	public Map<Integer, String> findAllPizzaDetails() {
		
		List<PizzaBean> listPizzaBean = pizzaDAOWrapper.findAllPizzaDetails();
		
		Map<Integer, String> mapPizzaBean = new HashMap<Integer, String>();
		
		for (PizzaBean pizzaBean : listPizzaBean) {
			mapPizzaBean.put(pizzaBean.getPizzaId(), pizzaBean.getPizzaName());
		}
		
		return mapPizzaBean;
	}

}
















