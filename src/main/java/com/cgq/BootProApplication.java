package com.cgq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan(value = "com.cgq.mapper")
@EnableSwagger2

@SpringBootApplication
public class BootProApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootProApplication.class, args);
	}
}
