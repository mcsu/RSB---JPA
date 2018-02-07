package com.su.ist.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.su.ist.domain.Vehicule;

import com.su.ist.service.Listener;


@Service
public class RentService {
	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction tx;
	
	public RentService() throws JMSException {
		
		this.emf = Persistence.createEntityManagerFactory("manager1");
		this.em = emf.createEntityManager();
		this.tx = em.getTransaction();
//		rece();
		
		
	}
	
	
	
	public List<Vehicule> getVehicules(){
		
		List<Vehicule> list = em.createNativeQuery("SELECT * FROM vehicule where isrented = FALSE",Vehicule.class).getResultList();
		
		List<Vehicule> listVehicule = new ArrayList<Vehicule>();
		for (Iterator<Vehicule> it = list.iterator(); it.hasNext();){
			listVehicule.add(it.next());
		}
		return list;
	}
	
	public Vehicule getCar(String plateNumber) {	
		//boolean re = false;
		Vehicule result = new Vehicule();
		
		List list1 = em.createNativeQuery("SELECT id FROM vehicule WHERE plateNumber = ?1").setParameter(1, plateNumber).getResultList();
		
		long id = (long) list1.iterator().next();
	
		
		result = em.find(Vehicule.class, id);
		
		return  result;
	}
	

	public  void rent(String plateNumber) {
		tx.begin();
		Vehicule result = new Vehicule();
		List list1 = em.createNativeQuery("SELECT id FROM vehicule WHERE plateNumber = ?1").setParameter(1, plateNumber).getResultList();		
		long id = (long) list1.iterator().next();
		result = em.find(Vehicule.class, id);
		result.setRented(true);
		em.flush();
		tx.commit();
		
	}
	
	public void getBack(String plateNumber){
		tx.begin();
		Vehicule result = new Vehicule();
		List list1 = em.createNativeQuery("SELECT id FROM vehicule WHERE plateNumber = ?1").setParameter(1, plateNumber).getResultList();		
		long id = (long) list1.iterator().next();
		result = em.find(Vehicule.class, id);
		result.setRented(false);
		em.flush();
		tx.commit();
	}
public void recei() throws JMSException {
	System.out.println("自制监听器启动");
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
	QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
	
	Queue queue = (Queue) applicationContext.getBean("queue");
	
	// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html	
	QueueConnection connection = factory.createQueueConnection();
	QueueSession session = connection.createQueueSession(true, 1);
	// Open a session		
	
	// start the connection
	connection.start();
	// Create a receive	
	//QueueReceiver receiver = session.createReceiver(queue);
	//receiver.setMessageListener(new Listener());
	// Receive the message
	MessageConsumer messageConsumer = session.createConsumer(queue);
	messageConsumer.setMessageListener(new Listener());
}
	

	
	
}
