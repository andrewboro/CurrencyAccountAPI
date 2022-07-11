package com.example.currencyaccountapi.service;

import com.example.currencyaccountapi.model.Account;
import com.example.currencyaccountapi.model.Currency;
import com.example.currencyaccountapi.nbp.NBPService;
import com.example.currencyaccountapi.util.Pesel;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;


public class AccountService {

    private final String DOES_NOT_EXIST = "Account doesn't exist";

    Map<String, Account> accounts = new LinkedHashMap<>();
    NBPService nbpService = new NBPService();

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public boolean upsertAccount(String pesel, String firstName, String lastName, BigDecimal plnAmount) {
        if (Pesel.isAdultPesel(pesel)) {
            accounts.put(pesel, new Account(pesel, firstName, lastName, plnAmount));
            return true;
        }
        return false;
    }

    public String getAccountInfo(String pesel) {
        Account account = accounts.get(pesel);
        return (account != null) ? account.toString() : DOES_NOT_EXIST;
    }

    public void changeCurrencyFromPlnToUsd(String pesel, BigDecimal amount) throws InterruptedException, IOException, URISyntaxException {
        //Very naive:
        //1) no commission
        //2) method should allow to freely choose currencies
        Account account = accounts.get(pesel);
        if (amount.compareTo(account.getCurrencies().get(Currency.PLN)) > 0) return;

        BigDecimal exchangeRate = nbpService.getExchangeRate();

        account.getCurrencies().put(Currency.USD, account.getCurrencies().get(Currency.USD).add(amount.divide(exchangeRate, 2, RoundingMode.HALF_UP)));
        account.getCurrencies().put(Currency.PLN, account.getCurrencies().get(Currency.PLN).subtract(amount));
    }

    public void changeCurrencyFromUsdToPln(String pesel, BigDecimal amount) throws InterruptedException, IOException, URISyntaxException {
        Account account = accounts.get(pesel);
        if (amount.compareTo(account.getCurrencies().get(Currency.USD)) > 0) return;

        BigDecimal exchangeRate = nbpService.getExchangeRate();

        account.getCurrencies().put(Currency.PLN, account.getCurrencies().get(Currency.PLN).add(amount.multiply(exchangeRate)));
        account.getCurrencies().put(Currency.USD, account.getCurrencies().get(Currency.USD).subtract(amount));
    }
}
