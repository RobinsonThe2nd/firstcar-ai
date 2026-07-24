package com.firstcar.backend.util;

import com.firstcar.backend.entity.Gender;
import java.time.DateTimeException;
import java.time.LocalDate;

public class SAIdNumberUtil {
    
    public static LocalDate extractDateOfBirth(String idNumber){

        String yy = idNumber.substring(0, 2);
        String mm = idNumber.substring(2, 4);
        String dd = idNumber.substring(4, 6);

        //Get the year and local year and determine the century (19 or 20)
        int year = Integer.parseInt(yy);
        int currentYearShort = LocalDate.now().getYear() % 100;

        //determine the full year
        int century = ( year > currentYearShort ) ? 1900 : 2000;
        int fullYear = century + year;

        try {
            return LocalDate.of(fullYear, Integer.parseInt(mm), Integer.parseInt(dd));
        } catch (DateTimeException e) {
            return null;
        }
        

    }

    public static Gender extractGender(String idNumber){

        int genderDigits = Integer.parseInt(idNumber.substring(6, 10));
        if (genderDigits < 5000) {
            return Gender.FEMALE;
        } else {
            return Gender.MALE;
        }

    }

    private static boolean isChecksumValid(String idNumber){

        int sum = 0;
        boolean alternate = false;

        for (int i = idNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(idNumber.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        return sum % 10 == 0;
    }

    public static boolean isValid(String idNumber){

        if (idNumber == null || !idNumber.matches("\\d{13}")) {
            return false;
        }
        if (extractDateOfBirth(idNumber) == null) {
            return false;
        }

        return isChecksumValid(idNumber);

    }


}
