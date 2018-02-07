package com.su.ist.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Rent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Person user;
	@Temporal(TemporalType.DATE)
	private Date beginRent;
	@Temporal(TemporalType.DATE)
	private Date endRent;
	private Vehicule car;

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne
	public Person getUser() {
		return user;
	}

	public void setUser(Person user) {
		this.user = user;
	}
	
	
	public Date getBeginRent() {
		return beginRent;
	}

	public void setBeginRent(Date beginRent) {
		this.beginRent = beginRent;
	}

	
	public Date getEndRent() {
		return endRent;
	}

	public void setEndRent(Date endRent) {
		this.endRent = endRent;
	}
	
	@ManyToOne
	public Vehicule getCar() {
		return car;
	}

	public void setCar(Vehicule car) {
		this.car = car;
	}

	
	public Rent() {}
	
}