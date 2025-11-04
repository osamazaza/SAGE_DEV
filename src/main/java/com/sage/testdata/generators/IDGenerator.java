package com.sage.testdata.generators;

import java.util.Random;

import com.github.javafaker.Faker;
import com.sage.model.UserIdentity;

public class IDGenerator {

	

	
	public UserIdentity  generateUserIdentity(boolean isCitizen) {

	 UserIdentity userIdentity = new UserIdentity();
		
		
		if (isCitizen) {
			userIdentity.setCitizen(true);
			userIdentity.setId(IDGenerator.getIdNumber(1));

		}

		else {
			userIdentity.setCitizen(false);
			userIdentity.setId(IDGenerator.getIdNumber(2));
		}

		userIdentity.setMobilePhone(IDGenerator.getSaudiPhoneNumberStartWith52());


		return userIdentity;
		
	}
	
	public static String getIdNumber(int type) {
		// 2 iqama
		// 1 NATIONAL ID

		Random random = new Random();

		StringBuilder idBuilder = new StringBuilder(Integer.toString(type));



		for (int i = 1; i <= 8; i++) {
			int randomDigit = random.nextInt(10);
			idBuilder.append(randomDigit);
		}



		String id = idBuilder.toString();

		int sum = 0;
		for (int i = 0; i < 9; i++) {
			if (i % 2 == 0) {
				int doubledDigit = Character.getNumericValue(id.charAt(i)) * 2;
				sum += doubledDigit / 10 + doubledDigit % 10;
			} else {
				sum += Character.getNumericValue(id.charAt(i));
			}
		}



		int checkDigit = (10 - (sum % 10)) % 10;
		id += checkDigit;


		return id;
	} // end of getPoiNumber

	
	public static String getSaudiPhoneNumberStartWith52() {
		Faker faker = new Faker();
		return "+966520" + faker.number().digits(6);
	}
	
	
}
	
	
	
	
