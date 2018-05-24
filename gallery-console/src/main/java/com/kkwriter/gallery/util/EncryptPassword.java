package com.kkwriter.gallery.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lisha
 */
public class EncryptPassword {
	private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
	
	public static String encrypt(String password) {
		return PASSWORD_ENCODER.encode(password);
	}
	
	public static boolean matches(String rawPass, String encodedPass) {
		return PASSWORD_ENCODER.matches(rawPass, encodedPass);
	}
	
}
