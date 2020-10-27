package org.fms.rs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("I can! RS is running!!!");
	}
}
