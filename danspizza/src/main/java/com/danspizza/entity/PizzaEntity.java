package com.danspizza.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pizza")
public class PizzaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pizzaId;
	
	private String pizzaName;
	
	private Double price;
	
	public PizzaEntity() {
		
	}

	public int getPizzaId() {
		return pizzaId;
	}

	public void setPizzaId(int pizzaId) {
		this.pizzaId = pizzaId;
	}

	public String getPizzaName() {
		return pizzaName;
	}

	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "PizzaEntity [pizzaId=" + pizzaId + ", pizzaName=" + pizzaName + ", price=" + price + "]";
	}

	
}
