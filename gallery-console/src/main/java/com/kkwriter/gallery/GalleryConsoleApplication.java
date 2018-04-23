package com.kkwriter.gallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class GalleryConsoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalleryConsoleApplication.class, args);
	}
}
