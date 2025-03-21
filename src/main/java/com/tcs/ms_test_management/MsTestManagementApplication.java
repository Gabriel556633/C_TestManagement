package com.tcs.ms_test_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.tcs.ms_test_management"})
public class MsTestManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTestManagementApplication.class, args);
	}

}
