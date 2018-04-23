package com.kkwriter.gallery.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncryptPassword {
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public static String encrypt(String password) {
		return passwordEncoder.encode(password);
	}
	
	public static boolean matches(String rawPass, String encodedPass) {
		return passwordEncoder.matches(rawPass, encodedPass);
	}
	
}
