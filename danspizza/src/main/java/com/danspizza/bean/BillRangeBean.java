package com.danspizza.bean;


public class BillRangeBean {
	
	private Double fromPrice;
	
	private Double toPrice;
	
	public BillRangeBean() {
		
	}

	public Double getFromPrice() {
		return fromPrice;
	}

	public void setFromPrice(Double fromPrice) {
		this.fromPrice = fromPrice;
	}

	public Double getToPrice() {
		return toPrice;
	}

	public void setToPrice(Double toPrice) {
		this.toPrice = toPrice;
	}

	@Override
	public String toString() {
		return "BillRangeBean [fromPrice=" + fromPrice + ", toPrice=" + toPrice + "]";
	}
	
}