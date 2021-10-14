package com.danspizza.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.danspizza.entity.PizzaEntity;

@Repository
public interface PizzaDAO extends JpaRepository<PizzaEntity, Integer> {

	@Query(name = "PizzaEntity.findAllPizzaDetails")
	public List<PizzaEntity> findAllPizzaDetails();
	
}
