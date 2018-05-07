package com.kkwrite.gallery;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * SpringBoot Bootstrap
 */
@SpringBootApplication
@MapperScan("com.kkwrite.gallery.mapper")
@EnableTransactionManagement
public class GalleryWebApp {
	
	public static void main(String[] args) {
		SpringApplication.run(GalleryWebApp.class, args);
	}
	
}
