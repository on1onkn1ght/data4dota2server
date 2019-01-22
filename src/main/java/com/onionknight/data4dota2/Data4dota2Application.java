package com.onionknight.data4dota2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.onionknight.data4dota2.mapper")
public class Data4dota2Application {

	public static void main(String[] args) {
		SpringApplication.run(Data4dota2Application.class, args);
	}

}

