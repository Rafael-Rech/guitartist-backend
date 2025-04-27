package com.tcc.tcc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories
public class TccApplication 
{
	public static void main(String[] args) {
		SpringApplication.run(TccApplication.class, args);
	}
}
