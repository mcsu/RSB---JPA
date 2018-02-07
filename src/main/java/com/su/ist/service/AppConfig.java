package com.su.ist.service;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.sound.midi.Receiver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.su.ist.service.Listener;

@Configuration
public class AppConfig {

	
	public AppConfig() throws JMSException{
		System.out.println("spring容器启动初始化。。。");
		System.out.println("JPA监听器启动");
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
		QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
		Queue queue = (Queue) applicationContext.getBean("queue");
		QueueConnection connection = factory.createQueueConnection();
		QueueSession session = connection.createQueueSession(true, 1);
		connection.start();
		MessageConsumer messageConsumer = session.createConsumer(queue);
		messageConsumer.setMessageListener(new Listener());
	}
	
}
