package com.su.ist.service;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

@Service
public class MessageService {
	ApplicationContext applicationContext;
	QueueConnectionFactory factory;
	Queue queue;
	QueueConnection connection;
	QueueSession session;
	public MessageService() throws JMSException {
		this.applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
		this.factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
		this.queue = (Queue) applicationContext.getBean("queueJpa");
		this.connection = factory.createQueueConnection();
		session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
	}
	
	public void send(String message) throws JMSException {
		connection.start();
		QueueSender queueSender = session.createSender(queue);
		TextMessage textMessage = session.createTextMessage();
		textMessage.setText(message);
		queueSender.send(textMessage);
		session.close();
		connection.close();
	}
}
