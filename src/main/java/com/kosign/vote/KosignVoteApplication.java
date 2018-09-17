package com.kosign.vote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.kosign.vote")
public class KosignVoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(KosignVoteApplication.class, args);
	}

}
