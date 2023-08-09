package edu.uptc.model;

import java.time.LocalDate;
import java.time.Period;

public class Phone {
	private CellPlan cellPlan;
    private String imei;
    private String number;
    private LocalDate manufacturingDate;
    
    public Phone(CellPlan cellPlan, String imei, String number, LocalDate manufacturingDate) {
        this.cellPlan = cellPlan;
        this.imei = imei;
        this.number = number;
        this.manufacturingDate = manufacturingDate;
    }

    public void setCellPlan(CellPlan cellPlan) {
        this.cellPlan = cellPlan;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public CellPlan getCellPlan() {
        return cellPlan;
    }

    public String getImei() {
        return imei;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }
    
    public int getAge() {
        LocalDate today = LocalDate.now();
        Period period = Period.between(manufacturingDate, today);
        return period.getYears();
    }

    @Override
    public String toString() {
        return "Phone cellPlan: " + cellPlan + "\n Imei: " + imei + "\n Number: " + number + "\n Manufacturing age: "
                + getAge();
    }

	
}
