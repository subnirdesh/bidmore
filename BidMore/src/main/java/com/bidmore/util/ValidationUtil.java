package com.bidmore.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

import jakarta.servlet.http.Part;

public class ValidationUtil {

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNullOrEmpty(String value) {
		return value == null || value.trim().isEmpty();
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public boolean isAlphabetic(String value) {
		// use of Regular Expression
		// ^ --> begins with [a-zA-Z] && $ $ --> ends with [a-zA-Z] && + --> one or more
		// times
		return value != null && value.matches("^[a-zA-Z]+$");

	}

	// 3. Method to validate if a string starts with alphabets and contains both
	// alphabets and numbers
	public static boolean isAlphaNumericStartingWithLetters(String value) {
		// ^^[a-zA-Z] --> must begin with a alphabet
		// [a-zA-Z0-9]* --> remaining char can occur zero or more times
		return value != null && value.trim().matches("^[a-zA-Z][a-zA-Z0-9]*$");

	}

	// 5. Method to validate if the string is a valid email address
	public static boolean isValidEmail(String email) {
		String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		return email != null && Pattern.matches(emailRegex, email);

	}

	// 5.Method to validate if a phone number starts with 98 and has 10 digits
	public static boolean isValidPhoneNumber(String number) {
		return number != null && number.matches("98\\d{8}$");

	}

	// 6. Method to validate if the password is composed of least 1 capital letter,
	// 1 number and 1 symbol
	public static boolean isValidPassword(String password) {
		// ?= --> ensures existence
		String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		return password != null && password.matches(passwordRegex);

	}

	// 7. Method to validate if the retyped password matches with pre-entered
	// password
	public static boolean doPasswordMatch(String password, String retypedPassword) {
		return password != null && retypedPassword.equals(password);

	}

	// 8. Method to validate if the age is at least 16 years before today
	public static boolean isAgeAtLeast16(LocalDate dob) {
		if (dob == null) {
			return false;
		}

		LocalDate today = LocalDate.now();
		return Period.between(dob, today).getYears() >= 16;

	}

	// 9. Method to validate if a part's file extension match with image file
	// extensions (jpeg, jpg, png , gif)
	public static boolean isValidImageExtension(Part imagePart) {
		if (imagePart == null || isNullOrEmpty(imagePart.getSubmittedFileName())) {
			return false;
		}
		String fileName = imagePart.getSubmittedFileName().toLowerCase();
		return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
				|| fileName.endsWith(".gif");
	}
	
	// 9. Method to validate float
	public static boolean isValidFloat(String input) {
		if (input == null || input.trim().isEmpty()) {
			return false;
		}

		try {
			Float.parseFloat(input.trim());
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
