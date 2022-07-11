package com.example.currencyaccountapi.util;

import java.time.LocalDate;
import java.time.Period;

public class Pesel {

    private final static int LEGAL_AGE = 18;

    public static boolean isAdultPesel(String pesel) {
        LocalDate localDate = peselToLocalDate(pesel);
        LocalDate now = LocalDate.now();
        final Period period = Period.between(localDate, now);
        return period.getYears() >= LEGAL_AGE;
    }

    public static LocalDate peselToLocalDate(String pesel) {
        //NOTE: the real algorithm is way more complicated

        int secondYearPart = Integer.parseInt(pesel.substring(0, 2));
        int monthPart = Integer.parseInt(pesel.substring(2, 4));
        int dayPart = Integer.parseInt(pesel.substring(4, 6));

        int year = 0;
        int month = 0;
        if (monthPart <= 12) {
            year = 1900 + secondYearPart;
            month = monthPart;
        } else if (monthPart <= 32) {
            year = 2000 + secondYearPart;
            month = monthPart - 20;
        }

        return LocalDate.of(year, month, dayPart);

    }
}
