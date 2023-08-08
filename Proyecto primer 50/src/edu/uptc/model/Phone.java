package edu.uptc.model;

import java.time.LocalDate;

public class Phone {
	String imei;
	String number;
	LocalDate manufacturingDate;
	
	private CellPlan cellplan;

	public Phone(String imei, String number, LocalDate manufacturingDate, CellPlan cellplan) {
		super();
		this.imei = imei;
		this.number = number;
		this.manufacturingDate = manufacturingDate;
		this.cellplan = cellplan;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public LocalDate getManufacturingDate() {
		return manufacturingDate;
	}

	public void setManufacturingDate(LocalDate manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	public CellPlan getCellplan() {
		return cellplan;
	}

	public void setCellplan(CellPlan cellplan) {
		this.cellplan = cellplan;
	}

	@Override
	public String toString() {
		return "Phone [imei=" + imei + ", number=" + number + ", manufacturingDate=" + manufacturingDate + ", cellplan="
				+ cellplan + "]";
	}
	
	
}
