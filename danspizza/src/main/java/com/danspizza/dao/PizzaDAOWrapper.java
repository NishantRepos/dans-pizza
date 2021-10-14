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

	@Autowired
	private PizzaDAO pizzaDAO;

	@Autowired
	private PizzaOrderDAO pizzaOrderDAO;

	@Autowired
	private EntityManager entityManager;

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

	public PizzaOrderBean addPizzaOrderDetails(PizzaOrderBean pizzaOrderBean) {

		System.out.println("\nIn DAO Layer PizzaDAOWrapper Class: " + pizzaOrderBean);
		
		PizzaOrderEntity pizzaOrderEntity = new PizzaOrderEntity();

		BeanUtils.copyProperties(pizzaOrderBean, pizzaOrderEntity);
		
		System.out.println("PizzaOrderEntity: " + pizzaOrderEntity);

		PizzaOrderEntity thePizzaOrderEntity = pizzaOrderDAO.save(pizzaOrderEntity);
		
		BeanUtils.copyProperties(thePizzaOrderEntity, pizzaOrderBean);

		return pizzaOrderBean;

	}

	public Double getPizzaPrice(Integer pizzaId) {

		PizzaEntity pizzaEntity = entityManager.find(PizzaEntity.class, pizzaId);

		return pizzaEntity.getPrice();
	}

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