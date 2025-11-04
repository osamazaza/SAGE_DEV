package com.sage.testdata.generators;

import java.util.Random;

public class GenericGenerator {


    public   String generateRandomDigit(int totalNumberLength) {
        // if you enter length 10 java will represent only 9 digits(single number scope)
        // so increase that by 1
        // do not use the function for real numbers as this function could generate 0 for first digit
        Random random = new Random();
        String number = "";
      for (int i = 1; i<=totalNumberLength + 1; i++){

          number = number + random.nextInt(totalNumberLength + 1);
      }

      return number;

    }

}
