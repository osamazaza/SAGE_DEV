package com.sage.testdata.generators;




import java.math.BigInteger;
import java.util.*;

import com.sage.base.TestBase;
import com.sage.model.LocalBeneficiary;








public class LocalBeneficiaryGenerator {
	static ArrayList  <String> arabicNamesList = new ArrayList<String> ();
	static ArrayList<String> arabicLastNamesList =  new ArrayList<String> ();
	static ArrayList  <String> englishNamesList = new ArrayList<String> ();
	static ArrayList<String> englishLastNamesList =  new ArrayList<String> ();
	// Saudi Arabia Bank Codes
	public static final int NATIONAL_BANK = 10;
	public static final int AGRICULTURAL_TURKISH_BANK = 84;
	public static final int SABB = 45;
	public static final int SAUDI_FRANSI = 55;
	public static final int SAUDI_HOLLANDI = 50;
	public static final int SAUDI_INVESTMENT = 65;
	public static final int ARAB_NATIONAL_BANK = 30;
	public static final int ARAB_NATIONAL_TEST1 = 99;
	public static final int ARAB_NATIONAL_TEST2 = 99;
	public static final int ARAB_NATIONAL_TEST3 = 99;
	public static final int INDUSTRIAL_CHINA = 87;
	public static final int ABU_DHABI_BANK = 73;
	public static final int EMIRATES_BANK = 95;
	public static final int NATIONAL_BANK_PAKISTAN = 82;
	public static final int BAHRAIN_BANK = 71;
	public static final int BILAD = 15;
	public static final int JAZIRA = 60;
	public static final int GULF_BANK = 90;
	public static final int STATE_BANK_OF_INDIA = 83;
	public static final int RIYAD_BANK = 20;
	public static final int KUWAIT_BANK = 75;
	public static final int MUSCAT_BANK = 76;
	public static final int BNP_PARIBAS = 85;
	public static final int JP_MORGAN = 86;
	public static final int DEUTSCHE_BANK = 81;
	public static final int SAMBA = 40;
	public static final int ALINMA = 5;
	public static final int ALRAJHI = 80;


	public static void main(String [] args){




		LocalBeneficiary localBeneficiary  = generateLocalBeneficiary(ALRAJHI, 1, 1);


		System.out.println("Local beneficiary generated data is: " + localBeneficiary.toString());

		LocalBeneficiary localBeneficiary2  = generateRandomLocalBeneficiary();
		System.out.println("random Local beneficiary generated data is: " + localBeneficiary2.toString());




	}



	public LocalBeneficiaryGenerator (){

	}






	private static final List<Integer> BANK_TYPE_CODES = Arrays.asList(
			NATIONAL_BANK,
			AGRICULTURAL_TURKISH_BANK,
			SABB,
			SAUDI_FRANSI,
			SAUDI_HOLLANDI,
			SAUDI_INVESTMENT,
			ARAB_NATIONAL_BANK,
			INDUSTRIAL_CHINA,
			ABU_DHABI_BANK,
			EMIRATES_BANK,
			NATIONAL_BANK_PAKISTAN,
			BAHRAIN_BANK,
			BILAD,
			JAZIRA,
			GULF_BANK,
			STATE_BANK_OF_INDIA,
			RIYAD_BANK,
			KUWAIT_BANK,
			MUSCAT_BANK,
			BNP_PARIBAS,
			JP_MORGAN,
			DEUTSCHE_BANK,
			SAMBA,
			ALINMA,
			ALRAJHI
			// Excluded: ARAB_NATIONAL_TEST1/2/3 (duplicate 99)
	);

	private static final List<Integer> BANK_CODES = Arrays.asList(
			NATIONAL_BANK,
			AGRICULTURAL_TURKISH_BANK,
			SABB,
			SAUDI_FRANSI,
			SAUDI_HOLLANDI,
			SAUDI_INVESTMENT,
			ARAB_NATIONAL_BANK,
			ARAB_NATIONAL_TEST1,
			ARAB_NATIONAL_TEST2,
			ARAB_NATIONAL_TEST3,
			INDUSTRIAL_CHINA,
			ABU_DHABI_BANK,
			EMIRATES_BANK,
			NATIONAL_BANK_PAKISTAN,
			BAHRAIN_BANK,
			BILAD,
			JAZIRA,
			GULF_BANK,
			STATE_BANK_OF_INDIA,
			RIYAD_BANK,
			KUWAIT_BANK,
			MUSCAT_BANK,
			BNP_PARIBAS,
			JP_MORGAN,
			DEUTSCHE_BANK,
			SAMBA,
			ALINMA,
			ALRAJHI
	);

	public static String getBankNameByCode(int code) {
		switch (code) {
			case NATIONAL_BANK: return "البنك الأهلي";
			case AGRICULTURAL_TURKISH_BANK: return "البنك الزراعي الجمهوري التركي";
			case SABB: return "البنك السعودي البريطاني";
			case SAUDI_FRANSI: return "البنك السعودي الفرنسي";
			case SAUDI_HOLLANDI: return "البنك السعودي الهولندي";
			case SAUDI_INVESTMENT: return "البنك السعودي للاستثمار";
			case ARAB_NATIONAL_BANK: return "البنك العربي الوطني";
			case INDUSTRIAL_CHINA: return "اندستريال أند كوميرشال بانك اوف شاينا (فرع الرياض)";
			case ABU_DHABI_BANK: return "بنك أبوظبي الأول";
			case EMIRATES_BANK: return "بنك الإمارات";
			case NATIONAL_BANK_PAKISTAN: return "بنك الباكستان الوطنى";
			case BAHRAIN_BANK: return "بنك البحرين";
			case BILAD: return "بنك البلاد";
			case JAZIRA: return "بنك الجزيرة";
			case GULF_BANK: return "بنك الخليج";
			case STATE_BANK_OF_INDIA: return "بنك الدوله الهندي";
			case RIYAD_BANK: return "بنك الرياض";
			case KUWAIT_BANK: return "بنك الكويت";
			case MUSCAT_BANK: return "بنك مسقط";
			case BNP_PARIBAS: return "بي إن بي باريبا";
			case JP_MORGAN: return "جي بي مورجان تشيز بنك،أن.أيه(فرع الرياض)";
			case DEUTSCHE_BANK: return "دوتش بنك";
			case SAMBA: return "مجموعة سامبا المالية (سامبا)";
			case ALINMA: return "مصرف الإنماء";
			case ALRAJHI: return "مصرف الراجحي";
			default: return "بنك غير معروف";
		}
	}

	public static String generateSaudiIBAN(int localBankType) {
		String countryCode = "SA";
		String bankIdentifier;
		switch (localBankType) {
			case NATIONAL_BANK:
				bankIdentifier = "10";
				break;
			case AGRICULTURAL_TURKISH_BANK:
				bankIdentifier = "84";
				break;
			case SABB:
				bankIdentifier = "45";
				break;
			case SAUDI_FRANSI:
				bankIdentifier = "55";
				break;
			case SAUDI_HOLLANDI:
				bankIdentifier = "50";
				break;
			case SAUDI_INVESTMENT:
				bankIdentifier = "65";
				break;
			case ARAB_NATIONAL_BANK:
				bankIdentifier = "30";
				break;
			case INDUSTRIAL_CHINA:
				bankIdentifier = "87";
				break;
			case ABU_DHABI_BANK:
				bankIdentifier = "73";
				break;
			case EMIRATES_BANK:
				bankIdentifier = "95";
				break;
			case NATIONAL_BANK_PAKISTAN:
				bankIdentifier = "82";
				break;
			case BAHRAIN_BANK:
				bankIdentifier = "71";
				break;
			case BILAD:
				bankIdentifier = "15";
				break;
			case JAZIRA:
				bankIdentifier = "60";
				break;
			case GULF_BANK:
				bankIdentifier = "90";
				break;
			case STATE_BANK_OF_INDIA:
				bankIdentifier = "83";
				break;
			case RIYAD_BANK:
				bankIdentifier = "20";
				break;
			case KUWAIT_BANK:
				bankIdentifier = "75";
				break;
			case MUSCAT_BANK:
				bankIdentifier = "76";
				break;
			case BNP_PARIBAS:
				bankIdentifier = "85";
				break;
			case JP_MORGAN:
				bankIdentifier = "86";
				break;
			case DEUTSCHE_BANK:
				bankIdentifier = "81";
				break;
			case SAMBA:
				bankIdentifier = "40";
				break;
			case ALINMA:
				bankIdentifier = "05";
				break;
			case ALRAJHI:
				bankIdentifier = "80";
				break;
			default:
				throw new IllegalArgumentException("Invalid or unsupported bank code: " + localBankType);
		}
		String basicBankAccountNumber = generateRandomBasicBankAccountNumber();
		String checkDigits = generateCheckDigits(countryCode + "00" + bankIdentifier + "00" + basicBankAccountNumber);
		return countryCode + checkDigits + bankIdentifier + "00" + basicBankAccountNumber;
	}



	public static String generateRandomSaudiIBAN() {
		int randomBankCode = BANK_TYPE_CODES.get(new Random().nextInt(BANK_TYPE_CODES.size()));
		String iban = generateSaudiIBAN(randomBankCode);

		System.out.println("Generated IBAN: " + iban);
		System.out.println("Bank Code: " + randomBankCode);
		System.out.println("Bank Name: " + getBankNameByCode(randomBankCode));

		return iban;
	}








//  helper functions for genereate iban functions
	private static String generateCheckDigits(String iban) {
		// Step 1: Move the four initial characters to the end of the string
		String modifiedIBAN = iban.substring(4) + iban.substring(0, 4);

		// Step 2: Replace the letters in the string with digits
		StringBuilder numericIBAN = new StringBuilder();
		for (char charAt : modifiedIBAN.toCharArray()) {
			if (Character.isLetter(charAt)) {
				numericIBAN.append((int) Character.toUpperCase(charAt) - 55);
			} else {
				numericIBAN.append(charAt);
			}
		}

		// Step 3: Convert the string to a BigInteger (ignore leading zeroes)
		BigInteger ibanBigInt = new java.math.BigInteger(numericIBAN.toString());

		// Step 4: Calculate mod-97
		BigInteger remainder = ibanBigInt.mod(java.math.BigInteger.valueOf(97));
		Locale.setDefault(Locale.US);
		BigInteger checkDigits = BigInteger.valueOf(98).subtract(remainder);


		return String.format("%02d", checkDigits.intValue());
	}


	private static String generateRandomBasicBankAccountNumber() {
		StringBuilder basicBankAccountNumber = new StringBuilder();
		for (int j = 0; j < 16; j++) {
			basicBankAccountNumber.append(generateRandomDigit());
		}
		return basicBankAccountNumber.toString();
	}


	private static int generateRandomDigit() {
		Random random = new Random();
		return random.nextInt(10);
	}






	private static ArrayList<String> getArabicLastNamesList() {

				// Add example last names (feel free to expand this list)
				ArrayList<String> arabicLastNames = new ArrayList<>();
				arabicLastNames.add("عبدالعزيز");
				arabicLastNames.add("المنصوري");
				arabicLastNames.add("حسن");
				arabicLastNames.add("خليفة");
				arabicLastNames.add("محمود");
				arabicLastNames.add("ناصر");
				arabicLastNames.add("سالم");
				arabicLastNames.add("طه");
				arabicLastNames.add("يوسف");
				arabicLastNames.add("زاهر");

				// Additional 90 last names
				arabicLastNames.add("علي");
				arabicLastNames.add("سعيد");
				arabicLastNames.add("فاروق");
				arabicLastNames.add("خالد");
				arabicLastNames.add("عبدالله");
				arabicLastNames.add("رزق");
				arabicLastNames.add("حامد");
				arabicLastNames.add("عزيز");
				arabicLastNames.add("شريف");
				arabicLastNames.add("جابر");
				arabicLastNames.add("نجار");
				arabicLastNames.add("مكي");
				arabicLastNames.add("فاضل");
				arabicLastNames.add("هاشم");
				arabicLastNames.add("عدنان");
				arabicLastNames.add("عمر");
				arabicLastNames.add("رحمان");
				arabicLastNames.add("زغبي");
				arabicLastNames.add("خوري");
				arabicLastNames.add("سليمان");
				arabicLastNames.add("شلهوب");
				arabicLastNames.add("بارودي");
				arabicLastNames.add("سعد");
				arabicLastNames.add("عبادي");
				arabicLastNames.add("عنتر");
				arabicLastNames.add("داود");
				arabicLastNames.add("داغر");
				arabicLastNames.add("أسد");
				arabicLastNames.add("خوري");
				arabicLastNames.add("زيدان");
				arabicLastNames.add("حلبي");
				arabicLastNames.add("شماس");
				arabicLastNames.add("صليبا");
				arabicLastNames.add("زيات");
				arabicLastNames.add("رحال");
				arabicLastNames.add("كرم");
				arabicLastNames.add("قاسم");
				arabicLastNames.add("نصار");
				arabicLastNames.add("خوري");
				arabicLastNames.add("صباغ");
				arabicLastNames.add("حرب");
				arabicLastNames.add("سلامة");
				arabicLastNames.add("حداد");
				arabicLastNames.add("ضاهر");
				arabicLastNames.add("بشارة");
				arabicLastNames.add("معلوف");
				arabicLastNames.add("عيسى");
				arabicLastNames.add("فاخوري");
				arabicLastNames.add("حنا");
				arabicLastNames.add("جرجس");
				arabicLastNames.add("حبيب");
				arabicLastNames.add("سماحة");
				arabicLastNames.add("طناوس");
				arabicLastNames.add("سركس");
				arabicLastNames.add("أبي نصيف");
				arabicLastNames.add("خوري");
				arabicLastNames.add("بداوي");
				arabicLastNames.add("ضاهر");
				arabicLastNames.add("سليمان");
				arabicLastNames.add("فاضل");
				arabicLastNames.add("اسمر");
				arabicLastNames.add("حلبي");
				arabicLastNames.add("صباغ");
				arabicLastNames.add("حداد");
				arabicLastNames.add("خوري");
				arabicLastNames.add("نصار");
				arabicLastNames.add("قاسم");
				arabicLastNames.add("رحال");
				arabicLastNames.add("كرم");
				arabicLastNames.add("معلوف");
				arabicLastNames.add("عيسى");
				arabicLastNames.add("حنا");
				arabicLastNames.add("جرجس");
				arabicLastNames.add("حبيب");
				arabicLastNames.add("سماحة");
				arabicLastNames.add("طناوس");
				arabicLastNames.add("سركس");
				arabicLastNames.add("أبي نصيف");
				arabicLastNames.add("خوري");
				arabicLastNames.add("بداوي");
				arabicLastNames.add("ضاهر");
				arabicLastNames.add("سليمان");
				arabicLastNames.add("فاضل");
				arabicLastNames.add("اسمر");
				arabicLastNames.add("حلبي");
				arabicLastNames.add("صباغ");
				arabicLastNames.add("حداد");
				arabicLastNames.add("خوري");
				arabicLastNames.add("نصار");
				arabicLastNames.add("قاسم");
				arabicLastNames.add("رحال");
				arabicLastNames.add("كرم");
				arabicLastNames.add("معلوف");
				arabicLastNames.add("عيسى");
				arabicLastNames.add("حنا");
				arabicLastNames.add("جرجس");
				arabicLastNames.add("حبيب");
				arabicLastNames.add("سماحة");
				arabicLastNames.add("طناوس");
				arabicLastNames.add("سركس");
				arabicLastNames.add("أبي نصيف");
				arabicLastNames.add("خوري");
				arabicLastNames.add("بداوي");

				return arabicLastNames;
			}




	private static ArrayList<String> getArabicNamesList() {

				ArrayList<String> arabicNames = new ArrayList<>();
				arabicNames.add("أحمد");
				arabicNames.add("فاطمة");
				arabicNames.add("علي");
				arabicNames.add("عائشة");
				arabicNames.add("عمر");
				arabicNames.add("ليلى");
				arabicNames.add("حسن");
				arabicNames.add("زينب");
				arabicNames.add("محمد");
				arabicNames.add("سارة");

				// Additional 100 names
				arabicNames.add("أمير");
				arabicNames.add("نور");
				arabicNames.add("خالد");
				arabicNames.add("ليلى");
				arabicNames.add("يوسف");
				arabicNames.add("هالة");
				arabicNames.add("كريم");
				arabicNames.add("نادية");
				arabicNames.add("إبراهيم");
				arabicNames.add("رانيا");
				arabicNames.add("محمود");
				arabicNames.add("مايا");
				arabicNames.add("مصطفى");
				arabicNames.add("هدى");
				arabicNames.add("طارق");
				arabicNames.add("آمال");
				arabicNames.add("سامي");
				arabicNames.add("لين");
				arabicNames.add("عدنان");
				arabicNames.add("لينا");
				arabicNames.add("حمزة");
				arabicNames.add("سعيدة");
				arabicNames.add("فادي");
				arabicNames.add("ريما");
				arabicNames.add("خالد");
				arabicNames.add("سلمى");
				arabicNames.add("جميل");
				arabicNames.add("سميرة");
				arabicNames.add("عمر");
				arabicNames.add("ياسمين");
				arabicNames.add("فهد");
				arabicNames.add("ندى");
				arabicNames.add("حسين");
				arabicNames.add("دينا");
				arabicNames.add("عبدالله");
				arabicNames.add("نجوى");
				arabicNames.add("طارق");
				arabicNames.add("مها");
				arabicNames.add("وليد");
				arabicNames.add("رشا");
				arabicNames.add("فيصل");
				arabicNames.add("سمر");
				arabicNames.add("بلال");
				arabicNames.add("جميلة");
				arabicNames.add("كريم");
				arabicNames.add("ليلى");
				arabicNames.add("كمال");
				arabicNames.add("نسرين");
				arabicNames.add("هاني");
				arabicNames.add("لبنى");
				arabicNames.add("فادي");
				arabicNames.add("أمينة");
				arabicNames.add("مازن");
				arabicNames.add("نور");
				arabicNames.add("رائد");
				arabicNames.add("رولا");
				arabicNames.add("وليد");
				arabicNames.add("فريدة");
				arabicNames.add("زياد");
				arabicNames.add("يارا");
				arabicNames.add("تامر");
				arabicNames.add("لطيفة");
				arabicNames.add("سمير");
				arabicNames.add("ريهام");
				arabicNames.add("نبيل");
				arabicNames.add("داليا");
				arabicNames.add("محسن");
				arabicNames.add("فرح");
				arabicNames.add("جواد");
				arabicNames.add("حنان");
				arabicNames.add("خالدة");
				arabicNames.add("بسام");
				arabicNames.add("نورة");
				arabicNames.add("رامي");
				arabicNames.add("شيماء");
				arabicNames.add("أسامة");
				arabicNames.add("ربيعة");
				arabicNames.add("سعيد");
				arabicNames.add("حبيبة");
				arabicNames.add("عماد");
				arabicNames.add("نهال");
				arabicNames.add("وفاء");
				arabicNames.add("كريمة");
				arabicNames.add("فؤاد");
				arabicNames.add("رقية");
				arabicNames.add("مهند");
				arabicNames.add("نوال");
				arabicNames.add("سفيان");
				arabicNames.add("زهراء");
				arabicNames.add("عبدالرحمن");
				arabicNames.add("هاجر");
				arabicNames.add("ماجد");
				arabicNames.add("ليث");
				arabicNames.add("نجيب");
				arabicNames.add("رشا");
				arabicNames.add("طلال");
				arabicNames.add("مرام");
				arabicNames.add("زاكي");
				arabicNames.add("سعيدة");
				arabicNames.add("إسماعيل");
				arabicNames.add("عبير");
				arabicNames.add("عادل");
				arabicNames.add("روان");
				arabicNames.add("فهيم");
				arabicNames.add("أمينة");
				arabicNames.add("رياض");
				arabicNames.add("شذى");
				arabicNames.add("رفيق");
				arabicNames.add("نورهان");
				arabicNames.add("أميرة");
				arabicNames.add("غازي");
				arabicNames.add("نايلة");
				arabicNames.add("كمال");
				arabicNames.add("لبنى");
				arabicNames.add("وائل");
				arabicNames.add("ياسمينا");
				arabicNames.add("طارق");
				arabicNames.add("مها");
				arabicNames.add("وليد");
				arabicNames.add("هند");
				arabicNames.add("سيف");
				arabicNames.add("فريدة");
				arabicNames.add("زياد");
				arabicNames.add("يارا");
				arabicNames.add("تامر");
				arabicNames.add("لطيفة");
				arabicNames.add("سمير");
				arabicNames.add("ريهام");
				arabicNames.add("نبيل");
				arabicNames.add("داليا");
				arabicNames.add("محسن");
				arabicNames.add("فرح");
				arabicNames.add("جواد");
				arabicNames.add("حنان");
				arabicNames.add("خالدة");
				arabicNames.add("بسام");
				arabicNames.add("نورة");
				arabicNames.add("رامي");
				arabicNames.add("شيماء");
				arabicNames.add("أسامة");
				arabicNames.add("ربيعة");
				arabicNames.add("سعيد");
				arabicNames.add("حبيبة");
				arabicNames.add("عماد");
				arabicNames.add("نهال");

				return arabicNames;
			}




	private static ArrayList<String> getEnglishNamesList() {
		ArrayList<String> englishNames = new ArrayList<>();
		englishNames.add("Ahmed");
		englishNames.add("Fatima");
		englishNames.add("Ali");
		englishNames.add("Aisha");
		englishNames.add("Omar");
		englishNames.add("Layla");
		englishNames.add("Hassan");
		englishNames.add("Zainab");
		englishNames.add("Mohammed");
		englishNames.add("Sara");
		// Additional 100 names
		englishNames.add("Amir");
		englishNames.add("Nour");
		englishNames.add("Khaled");
		englishNames.add("Leila");
		englishNames.add("Youssef");
		englishNames.add("Hala");
		englishNames.add("Karim");
		englishNames.add("Nadia");
		englishNames.add("Ibrahim");
		englishNames.add("Rania");
		englishNames.add("Mahmoud");
		englishNames.add("Maya");
		englishNames.add("Mustafa");
		englishNames.add("Hoda");
		englishNames.add("Tarek");
		englishNames.add("Amal");
		englishNames.add("Sami");
		englishNames.add("Leen");
		englishNames.add("Adnan");
		englishNames.add("Lina");
		englishNames.add("Hamza");
		englishNames.add("Saida");
		englishNames.add("Fadi");
		englishNames.add("Rima");
		englishNames.add("Khalid");
		englishNames.add("Salma");
		englishNames.add("Jamil");
		englishNames.add("Samira");
		englishNames.add("Omar");
		englishNames.add("Yasmin");
		englishNames.add("Fahad");
		englishNames.add("Nada");
		englishNames.add("Hussein");
		englishNames.add("Dina");
		englishNames.add("Abdullah");
		englishNames.add("Najwa");
		englishNames.add("Tariq");
		englishNames.add("Maha");
		englishNames.add("Walid");
		englishNames.add("Rasha");
		englishNames.add("Faisal");
		englishNames.add("Samar");
		englishNames.add("Bilal");
		englishNames.add("Jamila");
		englishNames.add("Kareem");
		englishNames.add("Layla");
		englishNames.add("Kamal");
		englishNames.add("Nisreen");
		englishNames.add("Hani");
		englishNames.add("Lubna");
		englishNames.add("Fadi");
		englishNames.add("Amina");
		englishNames.add("Mazen");
		englishNames.add("Noor");
		englishNames.add("Raed");
		englishNames.add("Rola");
		englishNames.add("Waleed");
		englishNames.add("Farida");
		englishNames.add("Ziad");
		englishNames.add("Yara");
		englishNames.add("Tamer");
		englishNames.add("Latifa");
		englishNames.add("Sameer");
		englishNames.add("Riham");
		englishNames.add("Nabil");
		englishNames.add("Dalia");
		englishNames.add("Mohsen");
		englishNames.add("Farah");
		englishNames.add("Jawad");
		englishNames.add("Hanan");
		englishNames.add("Khalida");
		englishNames.add("Bassam");
		englishNames.add("Noura");
		englishNames.add("Rami");
		englishNames.add("Shaima");
		englishNames.add("Osama");
		englishNames.add("Rabia");
		englishNames.add("Saeed");
		englishNames.add("Habiba");
		englishNames.add("Imad");
		englishNames.add("Nihal");
		englishNames.add("Wafaa");
		englishNames.add("Karima");
		englishNames.add("Fouad");
		englishNames.add("Rukaya");
		englishNames.add("Mohannad");
		englishNames.add("Nawal");
		englishNames.add("Sufyan");
		englishNames.add("Zahra");
		englishNames.add("Abdelrahman");
		englishNames.add("Hajar");
		englishNames.add("Majid");
		englishNames.add("Layth");
		englishNames.add("Najib");
		englishNames.add("Rasha");
		englishNames.add("Talal");
		englishNames.add("Maram");
		englishNames.add("Zaki");
		englishNames.add("Saida");
		englishNames.add("Ismail");
		englishNames.add("Abeer");
		englishNames.add("Adel");
		englishNames.add("Rawan");
		englishNames.add("Fahim");
		englishNames.add("Amina");
		englishNames.add("Riad");
		englishNames.add("Shaden");
		englishNames.add("Rafiq");
		englishNames.add("Nourhan");
		englishNames.add("Amira");
		englishNames.add("Ghazi");
		englishNames.add("Nayla");
		englishNames.add("Kamal");
		englishNames.add("Lubna");
		englishNames.add("Wael");
		englishNames.add("Yasmina");
		englishNames.add("Tariq");
		englishNames.add("Maha");
		englishNames.add("Walid");
		englishNames.add("Hind");
		englishNames.add("Saif");
		englishNames.add("Farida");
		englishNames.add("Ziad");
		englishNames.add("Yara");
		englishNames.add("Tamer");
		englishNames.add("Latifa");
		englishNames.add("Sameer");
		englishNames.add("Riham");
		englishNames.add("Nabil");
		englishNames.add("Dalia");
		englishNames.add("Mohsen");
		englishNames.add("Farah");
		englishNames.add("Jawad");
		englishNames.add("Hanan");
		englishNames.add("Khalida");
		englishNames.add("Bassam");
		englishNames.add("Noura");
		englishNames.add("Rami");
		englishNames.add("Shaima");
		englishNames.add("Osama");
		englishNames.add("Rabia");
		englishNames.add("Saeed");
		englishNames.add("Habiba");
		englishNames.add("Imad");
		englishNames.add("Nihal");
		return englishNames;
	}

	private static ArrayList<String> getEnglishLastNamesList() {
		ArrayList<String> englishLastNames = new ArrayList<>();
		englishLastNames.add("Abdelaziz");
		englishLastNames.add("AlMansoori");
		englishLastNames.add("Hassan");
		englishLastNames.add("Khalifa");
		englishLastNames.add("Mahmoud");
		englishLastNames.add("Nasser");
		englishLastNames.add("Salem");
		englishLastNames.add("Taha");
		englishLastNames.add("Yousef");
		englishLastNames.add("Zaher");
		// Additional 90 last names
		englishLastNames.add("Ali");
		englishLastNames.add("Said");
		englishLastNames.add("Farouk");
		englishLastNames.add("Khalid");
		englishLastNames.add("Abdullah");
		englishLastNames.add("Rizk");
		englishLastNames.add("Hamid");
		englishLastNames.add("Aziz");
		englishLastNames.add("Sharif");
		englishLastNames.add("Jaber");
		englishLastNames.add("Najjar");
		englishLastNames.add("Makki");
		englishLastNames.add("Fadel");
		englishLastNames.add("Hashem");
		englishLastNames.add("Adnan");
		englishLastNames.add("Omar");
		englishLastNames.add("Rahman");
		englishLastNames.add("Zogby");
		englishLastNames.add("Khouri");
		englishLastNames.add("Sleiman");
		englishLastNames.add("Shalhoub");
		englishLastNames.add("Baroudi");
		englishLastNames.add("Saad");
		englishLastNames.add("Abadi");
		englishLastNames.add("Antar");
		englishLastNames.add("Daoud");
		englishLastNames.add("Dagher");
		englishLastNames.add("Asad");
		englishLastNames.add("Khoury");
		englishLastNames.add("Zidan");
		englishLastNames.add("Halabi");
		englishLastNames.add("Shammas");
		englishLastNames.add("Saliba");
		englishLastNames.add("Zayyat");
		englishLastNames.add("Rahal");
		englishLastNames.add("Karam");
		englishLastNames.add("Qassem");
		englishLastNames.add("Nassar");
		englishLastNames.add("Khouri");
		englishLastNames.add("Sabbagh");
		englishLastNames.add("Harb");
		englishLastNames.add("Salameh");
		englishLastNames.add("Haddad");
		englishLastNames.add("Daher");
		englishLastNames.add("Bishara");
		englishLastNames.add("Malouf");
		englishLastNames.add("Issa");
		englishLastNames.add("Fakhoury");
		englishLastNames.add("Hanna");
		englishLastNames.add("Gerges");
		englishLastNames.add("Habib");
		englishLastNames.add("Samaha");
		englishLastNames.add("Tannous");
		englishLastNames.add("Sarkis");
		englishLastNames.add("AbiNassif");
		englishLastNames.add("Khoury");
		englishLastNames.add("Badawi");
		englishLastNames.add("Daher");
		englishLastNames.add("Sleiman");
		englishLastNames.add("Fadel");
		englishLastNames.add("Asmar");
		englishLastNames.add("Halabi");
		englishLastNames.add("Sabbagh");
		englishLastNames.add("Haddad");
		englishLastNames.add("Khouri");
		englishLastNames.add("Nassar");
		englishLastNames.add("Qassem");
		englishLastNames.add("Rahal");
		englishLastNames.add("Karam");
		englishLastNames.add("Malouf");
		englishLastNames.add("Issa");
		englishLastNames.add("Hanna");
		englishLastNames.add("Gerges");
		englishLastNames.add("Habib");
		englishLastNames.add("Samaha");
		englishLastNames.add("Tannous");
		englishLastNames.add("Sarkis");
		englishLastNames.add("AbiNassif");
		englishLastNames.add("Khoury");
		englishLastNames.add("Badawi");
		englishLastNames.add("Daher");
		englishLastNames.add("Sleiman");
		englishLastNames.add("Fadel");
		englishLastNames.add("Asmar");
		englishLastNames.add("Halabi");
		englishLastNames.add("Sabbagh");
		englishLastNames.add("Haddad");
		englishLastNames.add("Khouri");
		englishLastNames.add("Nassar");
		englishLastNames.add("Qassem");
		englishLastNames.add("Rahal");
		englishLastNames.add("Karam");
		englishLastNames.add("Malouf");
		englishLastNames.add("Issa");
		englishLastNames.add("Hanna");
		englishLastNames.add("Gerges");
		englishLastNames.add("Habib");
		englishLastNames.add("Samaha");
		englishLastNames.add("Tannous");
		englishLastNames.add("Sarkis");
		englishLastNames.add("AbiNassif");
		englishLastNames.add("Khoury");
		englishLastNames.add("Badawi");
		return englishLastNames;
	}
	
	



	
	
	public static LocalBeneficiary generateLocalBeneficiary(int localBankType, int indexOfFirstName, int indexOfLastName) {



				System.out.println("I am here");


				if(arabicNamesList.isEmpty()) {

					arabicNamesList = getArabicNamesList();
				}

				if(arabicLastNamesList.isEmpty()) {
					arabicLastNamesList = getArabicLastNamesList();
				}

				if(englishNamesList.isEmpty()) {
					englishNamesList = getEnglishNamesList();
				}

				if(englishLastNamesList.isEmpty()) {
					englishLastNamesList = getEnglishLastNamesList();
				}
				
			
				
				LocalBeneficiary localBeneficiary = new LocalBeneficiary();
		String firstNameAR = arabicNamesList.get(indexOfFirstName);
		String lastNameAR = arabicLastNamesList.get(indexOfLastName);
		String firstNameEN = englishNamesList.get(indexOfFirstName);
		String lastNameEN = englishLastNamesList.get(indexOfLastName);

				
				String selectedIBAN = generateSaudiIBAN(localBankType);


				localBeneficiary.setFirstNameAR(firstNameAR);
				localBeneficiary.setLastNameAR(lastNameAR);
				localBeneficiary.setFirstNameEN(firstNameEN);
				localBeneficiary.setLastNameEN(lastNameEN);
				localBeneficiary.setIBAN(selectedIBAN);

				return localBeneficiary;



		//return null;
	}


	// New function to get a random name based on a specified locale
	public static String getRandomPersonName(Locale locale) {


				Random random = new Random();
				ArrayList<String> namesToUse;

				if (locale.getLanguage().equalsIgnoreCase("ar")) {
					namesToUse = arabicNamesList;
				} else {
					namesToUse = englishNamesList;
				}

				if (namesToUse.isEmpty()) {
					throw new IllegalStateException("Name list is empty. Please call fillLocalBeneficiaryNamesList() first.");
				}

				int randomIndex = random.nextInt(namesToUse.size());
				return namesToUse.get(randomIndex);


	}

	// New function to get a random last name based on a specified locale
	public static String getRandomPersonLastName(Locale locale) {

			try {
				Random random = new Random();
				ArrayList<String> namesToUse;

				if (locale.getLanguage().equals("ar")) {
					namesToUse = arabicLastNamesList;
				} else {
					namesToUse = englishLastNamesList;
				}

				if (namesToUse.isEmpty()) {
					throw new IllegalStateException("Last name list is empty. Please call fillLocalBeneficiaryNamesList() first.");
				}

				int randomIndex = random.nextInt(namesToUse.size());
				return namesToUse.get(randomIndex);
			} catch (Exception e) {
				System.err.println("Error while getting random person last name: " + e.getMessage());
				return null;
			}


	}
	
	
	
	public static LocalBeneficiary generateRandomLocalBeneficiary() {



				if(arabicNamesList.isEmpty()) {
					arabicNamesList = getArabicNamesList();
				}

				if(arabicLastNamesList.isEmpty()) {
					arabicLastNamesList = getArabicLastNamesList();
				}

				if(englishNamesList.isEmpty()) {
					englishNamesList = getEnglishNamesList();
				}

				if(englishLastNamesList.isEmpty()) {
					englishLastNamesList = getEnglishLastNamesList();
				}

				Random randomForFirstName = new Random();
				int randomIndexForFirstName = randomForFirstName.nextInt(arabicNamesList.size());


				Random randomIndexForLastName = new Random();
				int randomIndexForLastNames = randomIndexForLastName.nextInt(arabicLastNamesList.size());


				LocalBeneficiary localBeneficiary = new LocalBeneficiary();
				String randomFirstNameAR = arabicNamesList.get(randomIndexForFirstName);
				String randomLastNameAR = arabicLastNamesList.get(randomIndexForLastNames);
				String randomFirstNameEN = englishNamesList.get(randomIndexForFirstName);
				String randomLastNameEN = englishLastNamesList.get(randomIndexForLastNames);


				String randomIBAN = generateRandomSaudiIBAN();

				localBeneficiary.setFirstNameAR(randomFirstNameAR);
				localBeneficiary.setLastNameAR(randomLastNameAR);
				localBeneficiary.setFirstNameEN(randomFirstNameEN);
				localBeneficiary.setLastNameEN(randomLastNameEN);
				localBeneficiary.setIBAN(randomIBAN);

				return localBeneficiary;




	}






















	






	
	private static int generateRandomNumber(int startNum, int endNum) {
		if (!TestBase.getReportAttributes().isSkipCurrentTestCase()) {
			try {
				// Adjust endNum to include it in the range
				int result = new Random().nextInt(endNum - startNum + 1) + startNum;
				System.out.println("Generated Random Number is: " + result);
				return result;
			} catch (Exception e) {
				//SmartUIValidator.addStepFailure(FailureHandling.STOP_ON_FAILURE, "Automation Error: while generate random number", e.getMessage())

				System.err.println("Error while generating random number: " + e.getMessage());
				return -1; // Return a default error value in case of failure
			}
		}

		else {

			if(TestBase.getReportAttributes().getTestCaseErrorsSummary().isEmpty()) {
				// this is mean that we are in new test case (not in sub test case), so we need to stop it's execution + attach blocker issue
				//SmartUIValidator.addStepFailure(FailureHandling.STOP_ON_FAILURE, "", ReportAttributes.blockerIssue)
			}

			else {
				// this means we are in sub test case, and we just need to stop execution since blocker issue has been raisen
				//SmartUIValidator.addStepFailure(FailureHandling.STOP_ON_FAILURE, "", "")
			}
		}


		return -1;
	}
}



