package com.budgetbuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan("com.budgetbuddy.category")
@ComponentScan(basePackages={"com.budgetbuddy.categoryrepository","com.budgetbuddy.category","com.budgetbuddy.categoryservice","com.budgetbuddy.categorycontroller","com.budgetbuddy.dao"})
@EnableJpaRepositories("com.budgetbuddy.categoryrepository")
@EnableDiscoveryClient
public class BudgetbuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetbuddyApplication.class, args);
	}

}
