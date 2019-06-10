package com.globant.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.globant.ecommerce.paymentdao.PaymentDAO;
import com.globant.ecommerce.paymentfacade.PaymentFacade;
import com.globant.ecommerce.paymentmodel.Payment;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
	ApplicationContext context = SpringApplication.run(EcommerceApplication.class, args);
	}
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
