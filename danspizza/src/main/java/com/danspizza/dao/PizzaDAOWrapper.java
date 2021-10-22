package com.danspizza.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.danspizza.bean.PizzaBean;
import com.danspizza.bean.PizzaOrderBean;
import com.danspizza.entity.PizzaEntity;
import com.danspizza.entity.PizzaOrderEntity;

@Repository
public class PizzaDAOWrapper {

	// Injecting PizzaDAO bean
	@Autowired
	private PizzaDAO pizzaDAO;

	// Injecting PizzaOrderDAO bean
	@Autowired
	private PizzaOrderDAO pizzaOrderDAO;

	// Injecting EntityManager bean
	@Autowired
	private EntityManager entityManager;

	// Get all pizza details in PizzaEntity object
	// and copy that in PizzaBean DTO using BeanUtils and return
	public List<PizzaBean> findAllPizzaDetails() {

		List<PizzaBean> listPizzaBean = new ArrayList<>();

		List<PizzaEntity> listPizzaEntity = pizzaDAO.findAllPizzaDetails();

		for (PizzaEntity pizzaEntity : listPizzaEntity) {

			PizzaBean pizzaBean = new PizzaBean();
			
			BeanUtils.copyProperties(pizzaEntity, pizzaBean);

			listPizzaBean.add(pizzaBean);		
		}
		
		return listPizzaBean;
	}

	// Copy PizzaOrderBean DTO in PizzaEntity object
	// then saves the PizzaEntity object and
	// now copy the returned PizzaEntity object in PizzaOrderBean DTO and return
	public PizzaOrderBean addPizzaOrderDetails(PizzaOrderBean pizzaOrderBean) {

		PizzaOrderEntity pizzaOrderEntity = new PizzaOrderEntity();

		BeanUtils.copyProperties(pizzaOrderBean, pizzaOrderEntity);
		
		PizzaOrderEntity thePizzaOrderEntity = pizzaOrderDAO.save(pizzaOrderEntity);
		
		BeanUtils.copyProperties(thePizzaOrderEntity, pizzaOrderBean);

		return pizzaOrderBean;

	}

	// Get the pizza price based on pizza id
	public Double getPizzaPrice(Integer pizzaId) {

		PizzaEntity pizzaEntity = entityManager.find(PizzaEntity.class, pizzaId);

		return pizzaEntity.getPrice();
	}

	// Get the order details within the specified bill range
	public List<PizzaOrderBean> getOrderDetails(Double fromBill, Double toBill) {

		List<PizzaOrderBean> listPizzaOrderBean = new ArrayList<>();

		Query theQuery = entityManager
				.createQuery("SELECT p FROM PizzaOrderEntity p " + "WHERE p.bill BETWEEN :fromBill AND :toBill");

		theQuery.setParameter("fromBill", fromBill);
		theQuery.setParameter("toBill", toBill);

		List<PizzaOrderEntity> listPizzaOrderEntity = theQuery.getResultList();

		for (PizzaOrderEntity pizzaOrderEntity : listPizzaOrderEntity) {
			
			PizzaOrderBean pizzaOrderBean = new PizzaOrderBean();
			
			BeanUtils.copyProperties(pizzaOrderEntity, pizzaOrderBean);

			listPizzaOrderBean.add(pizzaOrderBean);
		}

		return listPizzaOrderBean;

	}

}