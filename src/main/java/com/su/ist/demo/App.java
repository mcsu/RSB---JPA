package com.su.ist.demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.su.ist.domain.Person;
import com.su.ist.domain.Vehicule;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
   
    		EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");

		EntityManager entityManager = emf.createEntityManager();
		
		EntityTransaction tx = entityManager.getTransaction();
		
    	try{
    		
			tx.begin();
			
			Person p = new Person();
			p.setName("Tintin");
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date beginDate = dateFormat.parse("23/09/2015");
			p.setDate(beginDate);
			
		
			Vehicule v3 = new Vehicule();
			v3.setPlateNumber("京NH3M78");
			v3.setModel("q7");
			Vehicule v4 = new Vehicule();
			v4.setPlateNumber("京NNA917");
			v4.setModel("qin");
			Vehicule v5 = new Vehicule();
			v5.setModel("model x");
			v5.setPlateNumber("AA11AA");
			
			
			
			
			
		
		
			
			entityManager.persist(v3);
			entityManager.persist(v4);
			entityManager.persist(v5);
			tx.commit();			
		
		}catch(Exception e){
			tx.rollback();
		}
		
	}
}
