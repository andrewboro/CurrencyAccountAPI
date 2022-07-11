package com.example.currencyaccountapi.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PeselTest {
    String peselAdult = "80010212345";
    String peselChild = "19231712345";

    @org.junit.jupiter.api.Test
    void peselToLocalDate() {
        LocalDate localDate = Pesel.peselToLocalDate(peselAdult);
        LocalDate localDate2 = Pesel.peselToLocalDate(peselChild);
        assertEquals(LocalDate.of(1980, 1, 2), localDate);
        assertEquals(LocalDate.of(2019, 3, 17), localDate2);
    }

    @Test
    void isAdultPesel() {
        assertTrue(Pesel.isAdultPesel(peselAdult));
        assertFalse(Pesel.isAdultPesel(peselChild));
    }
}