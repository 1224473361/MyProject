package com.mybatistest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication
public class MybatisPlusDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisPlusDemoApplication.class, args);

	}

}
