package com.su.ist.service;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.su.ist.service.Listener;

import net.sf.json.JSONException;

@Configuration
public class AppConfig {

	
	public AppConfig() throws JMSException, JSONException,ClassCastException{
		System.out.println("spring容器启动初始化。。。");
		System.out.println("JPA监听器启动");
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
		QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
		Queue queue = (Queue) applicationContext.getBean("queue");
		QueueConnection connection = factory.createQueueConnection();
		QueueSession session = connection.createQueueSession(false, 1);
		connection.start();
		MessageConsumer messageConsumer = session.createConsumer(queue);
		messageConsumer.setMessageListener(new Listener());
		
		System.out.println("JPA获取数据");
		RentService rService = new RentService();
		MessageService messageService = new MessageService();
		RentService rs = new RentService();
		//QT
//		System.out.println(rs.buildMessage(rs.getUnrentedVehicules()));
		messageService.send(rs.buildMessage(rs.getUnrentedVehicules()));
		System.out.println("JPA获取数据完毕");
	}
	
}
