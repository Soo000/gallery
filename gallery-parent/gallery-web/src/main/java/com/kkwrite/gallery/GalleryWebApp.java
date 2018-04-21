package com.kkwrite.gallery;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot Bootstrap
 */
@SpringBootApplication
@MapperScan({
	"com.kkwrite.gallery.mapper",
	"com.kkwrite.gallery.mapper.address",
	"com.kkwrite.gallery.mapper.cart",
	"com.kkwrite.gallery.mapper.evaluate",
	"com.kkwrite.gallery.mapper.module",
	"com.kkwrite.gallery.mapper.order",
	"com.kkwrite.gallery.mapper.param",
	"com.kkwrite.gallery.mapper.product",
	"com.kkwrite.gallery.mapper.reservation",
	"com.kkwrite.gallery.mapper.user"
})
public class GalleryWebApp {
	
	public static void main(String[] args) {
		SpringApplication.run(GalleryWebApp.class, args);
	}
	
}
