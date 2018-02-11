package com.su.ist.demo;
//后端 三层结构 端口默认8080
import javax.jms.JMSException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import com.su.ist.service.AppConfig;

@SpringBootApplication

@ComponentScan(basePackages = {
		"com.su.ist.service",
		"com.su.ist.web",
		"com.su.ist.domain"
		}) 
public class DemoApplication {
	public static void main(String[] args) throws JMSException {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		SpringApplication.run(DemoApplication.class, args);
	}
}
