package com.sage.model;





public class LocalBeneficiary {

	private String firstNameAR;
	private String LastNameAR;
	private String firstNameEN;
	private String LastNameEN;
	private String IBAN;

	@Override
	public String toString() {
		return "LocalBeneficiary{" +
				"firstNameAR='" + firstNameAR + '\'' +
				", LastNameAR='" + LastNameAR + '\'' +
				", firstNameEN='" + firstNameEN + '\'' +
				", LastNameEN='" + LastNameEN + '\'' +
				", IBAN='" + IBAN + '\'' +
				'}';
	}

	public void setFirstNameAR(String firstNameAR) {
		this.firstNameAR = firstNameAR;
	}

	public void setLastNameAR(String lastNameAR) {
		LastNameAR = lastNameAR;
	}

	public void setFirstNameEN(String firstNameEN) {
		this.firstNameEN = firstNameEN;
	}

	public void setLastNameEN(String lastNameEN) {
		LastNameEN = lastNameEN;
	}

	public void setIBAN(String IBAN) {
		this.IBAN = IBAN;
	}

	public String getFirstNameAR() {
		return firstNameAR;
	}

	public String getLastNameAR() {
		return LastNameAR;
	}

	public String getFirstNameEN() {
		return firstNameEN;
	}

	public String getLastNameEN() {
		return LastNameEN;
	}

	public String getIBAN() {
		return IBAN;
	}

	public LocalBeneficiary(String firstNameAR, String lastNameAR, String firstNameEN, String lastNameEN, String IBAN) {
		this.firstNameAR = firstNameAR;
		LastNameAR = lastNameAR;
		this.firstNameEN = firstNameEN;
		LastNameEN = lastNameEN;
		this.IBAN = IBAN;
	}

	public LocalBeneficiary() {
	}


}