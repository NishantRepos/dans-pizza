package com.danspizza.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.danspizza.entity.PizzaOrderEntity;

@Repository
public interface PizzaOrderDAO extends JpaRepository<PizzaOrderEntity, Integer> {
	
	public PizzaOrderEntity save(PizzaOrderEntity pizzaOrderEntity);
	
}
