package com.pd;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.pd.mapper")
public class RunPdAPP{
	
	public static void main(String[] args) {
		SpringApplication.run(RunPdAPP.class, args);
	}

}
