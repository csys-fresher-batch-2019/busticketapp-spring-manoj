package com.chainsys.ebus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ServletComponentScan("com.chainsys.ebus.servlet")

public class EbusAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbusAppApplication.class, args);
	}

}

