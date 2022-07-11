package com.example.currencyaccountapi.model;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Account {
    String pesel;
    String firstName;
    String lastName;
    Map<Currency, BigDecimal> currencies;

    public Account(String pesel, String firstName, String lastName, BigDecimal plnAmount) {
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        currencies = new LinkedHashMap<>();
        currencies.put(Currency.PLN, plnAmount);
        currencies.put(Currency.USD, new BigDecimal(0));
    }

    public Map<Currency, BigDecimal> getCurrencies() {
        return currencies;
    }

    @Override
    public String toString() {
        return "Account{" +
                "pesel='" + pesel + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", currencies=" + currencies +
                '}';
    }
}
