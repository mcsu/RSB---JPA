package com.su.ist.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="VEHICULE")
public class Vehicule{


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	@Column(name = "PLATENUMBER")
	private String plateNumber;
	@Column(name = "MOEDL")
	private String model;
	@Column(name = "PRICE")
	private double price;
	@Column(name = "NUMBER_OF_DAYS")
	private int numberOfDays;
	@Column(name = "IS_RENTED")
	private boolean isRented = false;
	
	
	


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public boolean isRented() {
		return isRented;
	}
	public void setRented(boolean isRented) {
		this.isRented = isRented;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	@Override
	public String toString() {
		return "Vehicule [id=" + id + ", plateNumber=" + plateNumber + ", model=" + model + ", price=" + price
				+ ", numberOfDays=" + numberOfDays + ", isRented=" + isRented + "]";
	}
	
	
}